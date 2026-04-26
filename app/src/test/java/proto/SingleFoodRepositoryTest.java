package proto;

import proto.storage.InMemorySingleFoodRepository;
import proto.storage.SingleFoodRepository;
import proto.model.SingleFood;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class SingleFoodRepositoryTest {
    
    SingleFoodRepository singleFoodRepo;

    @BeforeEach
    void setup() {
        singleFoodRepo = new InMemorySingleFoodRepository();
    }

    @Test
    public void testSaveAndFindByName() {
        SingleFood banana = new SingleFood("Banana", 20f);
        singleFoodRepo.save(banana);

        assertEquals(1, singleFoodRepo.findAll().size());
        assertEquals("Banana", singleFoodRepo.findByName("Banana").getName());
    }

    @Test
    public void testNoDuplicateNames() {
        SingleFood bananaOne = new SingleFood("Banana", 20f);
        SingleFood bananaTwo = new SingleFood("Banana", 25f);

        singleFoodRepo.save(bananaOne);
        assertThrows(IllegalArgumentException.class, () -> {
            singleFoodRepo.save(bananaTwo);
        });
    }

    @Test
    public void testFindByNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            singleFoodRepo.findByName(null);
        });
    }

}
