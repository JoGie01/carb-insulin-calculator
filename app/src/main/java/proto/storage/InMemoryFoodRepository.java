package proto.storage;

import proto.model.Food;
import java.util.ArrayList;
import java.util.List;

public class InMemoryFoodRepository implements FoodRepository{
    
    List<Food> foods;

    public InMemoryFoodRepository() {
        this.foods = new ArrayList<>();
    }

    @Override
    public void save(Food food) {
        if(food == null){
            throw new IllegalArgumentException("Food cannot be null");
        } 

        for(Food f : this.foods){
            if(f.getName().equals(food.getName())){
                throw new IllegalArgumentException("Food with the same name already exists");
            }
        }

        this.foods.add(food);
    }

    @Override
    public Food findByName(String name) {
        if(name==null){
            throw new IllegalArgumentException("Name cannot be null");
        }

        for(Food f : this.foods){
            if(f.getName().equals(name)){
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

}
