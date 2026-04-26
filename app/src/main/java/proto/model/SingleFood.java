package proto.model;

public class SingleFood implements Food {

    private String name;
    private float carbsPer100g;

    public SingleFood (String name, float carbsPer100g) {
        if(carbsPer100g < 0) {
            throw new IllegalArgumentException("Carbs per 100g cannot be negative");
        }

        this.name = name;
        this.carbsPer100g = carbsPer100g;
    }

    public SingleFood() {}

    @Override
    public String toString() {
        return name + ", carbs per 100g " + carbsPer100g;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof SingleFood)) return false;

        SingleFood food = (SingleFood) other;
        
        return name.equals(food.name);
    }

    public String getName() {
        return name;
    }

    public float getCarbsPer100g() {
        return carbsPer100g;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarbsPer100g(float carbsPer100g) {
        if(carbsPer100g < 0) {
            throw new IllegalArgumentException("Carbs per 100g cannot be negative");
        }

        this.carbsPer100g = carbsPer100g;
    }

}
