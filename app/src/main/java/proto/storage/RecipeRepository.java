package proto.storage;

import java.util.List;
import proto.model.Recipe;
import proto.model.Food;

public interface RecipeRepository extends Repository<Recipe>{

public List<Recipe> findByIngredient(Food ingredient);

}
