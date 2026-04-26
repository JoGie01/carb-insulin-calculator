package proto.model;
import java.util.List;

public class Recipe implements Food {

    private String name;
    private float carbsPer100g;
    private List<Ingredient> ingredients;
    

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.carbsPer100g = calculateCarbsPer100g(ingredients);
    }

    private float calculateCarbsPer100g(List<Ingredient> ingredients) {
        float totalCarbs = 0;
        float totalWeight = 0;

        for(Ingredient ingredient : ingredients) {
            totalCarbs += ingredient.getFood().getCarbsPer100g() * (ingredient.getWeight() / 100f);
            totalWeight +=  ingredient.getWeight();
        }

        if(totalWeight == 0) {
            return 0f;
        }

        return (totalCarbs/totalWeight) * 100f;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Recipe)) return false;

        Recipe recipe = (Recipe) other;
        
        return name.equals(recipe.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCarbsPer100g() {
        return carbsPer100g;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.carbsPer100g = calculateCarbsPer100g(ingredients);
    }

    public boolean contains(Food ingredient) {
        for (Ingredient i : ingredients) {
            if (i.getFood().equals(ingredient)) {
                return true;
            }
        }
        return false;
    }
}
