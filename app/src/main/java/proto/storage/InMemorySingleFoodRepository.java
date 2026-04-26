package proto.storage;

import proto.model.SingleFood;
import java.util.ArrayList;
import java.util.List;

public class InMemorySingleFoodRepository implements SingleFoodRepository {

    private List<SingleFood> singleFoods = new ArrayList<>();

    @Override
    public void save(SingleFood singleFood) {
        if(singleFood == null) {
            throw new IllegalArgumentException("SingleFood cannot be null");
        }
        for(SingleFood sf : singleFoods) {
            if(sf.getName().equals(singleFood.getName())) {
                throw new IllegalArgumentException("SingleFood with the same name already exists");
            }
        }
        singleFoods.add(singleFood);
    }

    @Override
    public SingleFood findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        
        for(SingleFood sf : singleFoods) {
            if(sf.getName().equals(name)) {
                return sf;
            }
        }
        return null;
    }

    @Override
    public List<SingleFood> findAll() {
        return new ArrayList<>(singleFoods);
    }
    
}
