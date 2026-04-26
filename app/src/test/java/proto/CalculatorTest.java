package proto;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proto.model.Ingredient;
import proto.model.SingleFood;
import proto.model.User;
import proto.model.Meal;
import proto.service.Calculator;

public class CalculatorTest {

    @Test
    void calculateTotalCarbsReturnsZeroWhenNoFoods() {
        User user = new User("Britta", 30);
        Meal meal = new Meal(List.of());
        Calculator calculator = new Calculator(user, meal);

        assertEquals(0f, calculator.calculateInsulinDose());
    }

    @Test
    void calculateTotalCarbsSumsCarbsCorrectly() {
        User user = new User("Britta", 30);
        Ingredient banana = new Ingredient(new SingleFood("Banana", 20f), 150f);
        Ingredient milk = new Ingredient(new SingleFood("Milk", 5f), 200f);
        Meal meal = new Meal(List.of(banana, milk));

        Calculator calculator = new Calculator(user, meal);

        float expected = ((20f/100f)*150f + (5f/100f)*200f)/10f * user.getFactor();
        assertEquals(expected, calculator.calculateInsulinDose());
    }

    @Test
    void calculateInsulinDoseUsesUserFactorAndRoundsCorrectly() {
        User user = new User("Britta", 30, 1.5f);
        Ingredient peanutButter = new Ingredient(new SingleFood("Peanut Butter", 10f), 50f);
        Ingredient apple = new Ingredient(new SingleFood("Apple", 15f), 150f);
        Meal meal = new Meal(List.of(peanutButter, apple));

        Calculator calculator = new Calculator(user, meal);

        float totalCarbs = ((10f/100f)*50f + (15f/100f)*150f);
        int expectedDose = Math.round((totalCarbs / 10f) * 1.5f);

        assertEquals(expectedDose, calculator.calculateInsulinDose());
    }

}

