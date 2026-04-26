package proto.storage;

import proto.model.Recipe;
import proto.model.Food;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONRecipeRepository implements RecipeRepository{
        
    private List<Recipe> recipes;
    private ObjectMapper mapper;
    private File file;

    public JSONRecipeRepository(String filename) {
        this.mapper = new ObjectMapper();
        this.file = new File(filename);
        this.recipes = load();
    }

    private List<Recipe> load() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, new TypeReference<List<Recipe>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            mapper.writeValue(file, recipes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }

        for (Recipe f : recipes) {
            if (f.getName().equals(recipe.getName())) {
                throw new IllegalArgumentException("Recipe already exists");
            }
        }

        recipes.add(recipe);
        saveToFile();
    }

    @Override
    public Recipe findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        for (Recipe f : recipes) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Recipe> findByIngredient(Food ingredient) {
        List<Recipe> targetRecipes = new ArrayList<>();
        for(Recipe r : recipes) {
            if (r.contains(ingredient)) {
                targetRecipes.add(r);
            }
        }
        return targetRecipes;
    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(recipes);
    }
}
