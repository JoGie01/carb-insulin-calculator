package proto;

import org.junit.jupiter.api.Test;

import proto.model.User;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {
    @Test
    void testUserCreation() {
        User user = new User("Tom", 25);
        assertEquals("Tom", user.getName());
        assertEquals(25, user.getAge());
    }

    @Test
    void testSetters() {
        User user = new User("Tom", 20);
        user.setName("Jerry");
        user.setAge(21);
        assertEquals("Jerry", user.getName());
        assertEquals(21, user.getAge());
    }

}