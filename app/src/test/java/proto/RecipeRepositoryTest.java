package proto;

import proto.storage.InMemoryRecipeRepository;
import proto.storage.RecipeRepository;
import proto.model.Ingredient;
import proto.model.Recipe;
import proto.model.SingleFood;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class RecipeRepositoryTest {
    
    RecipeRepository recipeRepo;

    SingleFood banana;
    SingleFood milk;
    SingleFood peanutButter;
    SingleFood apple;
    SingleFood flour;

    Ingredient bananaI;
    Ingredient milkI;
    Ingredient peanutButterI;
    Ingredient appleI;
    Ingredient appleITwo;
    Ingredient flourI;

    Recipe bananaShake;
    Recipe appleShake;
    Recipe applePie;


    @BeforeEach
    void setup() {
        recipeRepo = new InMemoryRecipeRepository();

        banana = new SingleFood("Banana", 20f);
        milk = new SingleFood("Milk", 5f);
        peanutButter = new SingleFood("Peanut Butter", 10f);
        apple = new SingleFood("Apple", 15f);
        flour = new SingleFood("Flour", 25);

        bananaI = new Ingredient(banana, 150);
        milkI = new Ingredient(milk, 200);
        peanutButterI = new Ingredient(peanutButter, 100);
        appleI = new Ingredient(apple, 150);
        appleITwo = new Ingredient(apple, 300);
        flourI = new Ingredient(flour, 250);

        bananaShake = new Recipe("Banana Shake", List.of(bananaI, milkI, peanutButterI));
        appleShake = new Recipe("Apple Shake", List.of(appleI, milkI, peanutButterI));
        applePie = new Recipe("Apple Pie", List.of(appleITwo, flourI));
    }

    @Test
    public void testFindByIngredient() {
        recipeRepo.save(applePie);
        recipeRepo.save(bananaShake);
        recipeRepo.save(appleShake);

        List<Recipe> appleRecipes = recipeRepo.findByIngredient(apple);
        assertEquals(2, appleRecipes.size());
        assertTrue(appleRecipes.contains(applePie) && appleRecipes.contains(appleShake));
    }

}
