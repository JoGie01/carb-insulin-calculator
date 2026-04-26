package proto.storage;
import java.util.ArrayList;
import java.util.List;
import proto.model.Recipe;
import proto.model.Food;
import proto.model.Ingredient;

public class InMemoryRecipeRepository implements RecipeRepository {
    
    private List<Recipe> recipes;

    public InMemoryRecipeRepository() {
        this.recipes = new ArrayList<Recipe>();
    }

    @Override
    public void save(Recipe recipe) {
        for(Recipe r : recipes) {
            if(r.equals(recipe)) {
                throw new IllegalArgumentException("Recipe with the same name already exists");
            }
        }
        this.recipes.add(recipe);
    }

    @Override
    public List<Recipe> findByIngredient(Food ingredient) {
        if(ingredient==null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }

        List<Recipe> result = new ArrayList<Recipe>();
        for (Recipe r : recipes) {
            for(Ingredient i : r.getIngredients()) {
                if(i.getFood().equals(ingredient)) {
                    result.add(r);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Recipe findByName(String name) {
        if(name==null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        for(Recipe r : recipes) {
            if(r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<Recipe>(recipes);
    }

}
