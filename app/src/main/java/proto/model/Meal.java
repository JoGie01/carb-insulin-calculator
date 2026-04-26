package proto.model;

import java.util.List;

public class Meal {

    private List<Ingredient> ingredients;
    private float totalCarbs;

    public Meal(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.totalCarbs = calculateTotalCarbs();
    }

    public float calculateTotalCarbs() {
        float totalCarbs = 0;

        for(Ingredient ingredient : ingredients) {
            totalCarbs += ingredient.getFood().getCarbsPer100g() * (ingredient.getWeight() / 100f);
        }
        
        return totalCarbs;
    }

    public void addIngredient(Ingredient ingredient) {
        if(ingredient != null) {
        this.ingredients.add(ingredient);
        this.totalCarbs = calculateTotalCarbs();
        }
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }
    
}
