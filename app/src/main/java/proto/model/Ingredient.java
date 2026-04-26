package proto.model;

public class Ingredient {

    private Food food;
    private float weight;

    public Ingredient(Food food, float weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }

        this.food = food;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Ingredient)) return false;

        Ingredient ingredient = (Ingredient) other;

        return Float.compare(this.weight, ingredient.weight) == 0 && food.equals(ingredient.food);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }

        this.weight = weight;
    }
}
