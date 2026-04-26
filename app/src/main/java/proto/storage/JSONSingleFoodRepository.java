package proto.storage;

import proto.model.SingleFood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONSingleFoodRepository implements SingleFoodRepository {
    
    private List<SingleFood> singleFoods;
    private ObjectMapper mapper;
    private File file;

    public JSONSingleFoodRepository(String filename) {
        this.mapper = new ObjectMapper();
        this.file = new File(filename);
        this.singleFoods = load();
    }

    private List<SingleFood> load() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, new TypeReference<List<SingleFood>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            mapper.writeValue(file, singleFoods);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SingleFood food) {
        if (food == null) {
            throw new IllegalArgumentException("Food cannot be null");
        }

        for (SingleFood f : singleFoods) {
            if (f.getName().equals(food.getName())) {
                throw new IllegalArgumentException("Food already exists");
            }
        }

        singleFoods.add(food);
        saveToFile();
    }

    @Override
    public SingleFood findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        for (SingleFood f : singleFoods) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<SingleFood> findAll() {
        return new ArrayList<>(singleFoods);
    }

}


