package proto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proto.model.SingleFood;

public class SingleFoodTest {

    @Test
    void testSingleFoodCreation() {
        SingleFood apple = new SingleFood("Apple", 15);
        assertEquals("Apple", apple.getName());
        assertEquals(15, apple.getCarbsPer100g());

        assertThrows(IllegalArgumentException.class, () -> {
            new SingleFood("Orange", -5);
        });
    }

    @Test
    void testSingleFoodSetters() {
        SingleFood banana = new SingleFood("Apple", 15);
        banana.setName("Banana");
        assertEquals("Banana", banana.getName());
        banana.setCarbsPer100g(20);
        assertEquals(20, banana.getCarbsPer100g());

        assertThrows(IllegalArgumentException.class, () -> {
            banana.setCarbsPer100g(-5);
        });
    }
    
}
