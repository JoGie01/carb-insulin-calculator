package proto;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import proto.model.Food;
import proto.model.Ingredient;
import proto.model.Recipe;
import proto.model.SingleFood;

public class IngredientAndRecipeTest {

    Food banana;
    Food milk;
    Food peanutButter;
    Food apple;

    Ingredient bananaI;
    Ingredient milkI;
    Ingredient peanutButterI;
    Ingredient appleI;

    Recipe shake;

    List<Ingredient> ingredients;

    @BeforeEach
    void setup() {
        banana = new SingleFood("Banana", 20);
        milk = new SingleFood("Milk", 5);
        peanutButter = new SingleFood("Peanut Butter", 10);
        apple = new SingleFood("Apple", 15);

        bananaI = new Ingredient(banana, 150);
        milkI = new Ingredient(milk, 200);
        peanutButterI = new Ingredient(peanutButter, 50);
        appleI = new Ingredient(apple, 150);

        shake = new Recipe("Banana Shake", List.of(bananaI, milkI, peanutButterI));

        ingredients = List.of(appleI, milkI, peanutButterI);
    }
    
    @Test
    void testIngredientCreationAndSetter() {
        assertEquals(banana, bananaI.getFood());
        assertEquals(150, bananaI.getWeight());

        bananaI.setWeight(200);
        assertEquals(200, bananaI.getWeight());
    }

    @Test 
    void testIngredientNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(banana, -50);
        });
    }

    @Test
    void testIngredientSetNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            bananaI.setWeight(-50);
        });
    }

    @Test
    void testRecipeCreationAndSetters() {
        assertEquals("Banana Shake", shake.getName());
        assertEquals(11.25f, shake.getCarbsPer100g(), 0.01f);
        assertEquals(3, shake.getIngredients().size());

        shake.setName("Apple Shake");
        assertEquals("Apple Shake", shake.getName());

        shake.setIngredients(ingredients);
        assertFalse(shake.getIngredients().contains(bananaI));
        assertEquals(3, shake.getIngredients().size());
        assertEquals(9.375f, shake.getCarbsPer100g(), 0.01f);
    }
}
