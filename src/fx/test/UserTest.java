package fx.test;


import fx.classes.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User("rawan fraihat", "rawanfr", "rawan@gmail.com", "1234567890", "rawan@@R123", "student");

        assertEquals("rawan fraihat", user.getFullName(), "Full name should match.");
        assertEquals("rawanfr", user.getUsername(), "Username should match.");
        assertEquals("rawan@gmail.com", user.getEmailaddress(), "Email address should match.");
        assertEquals("1234567890", user.getNumber(), "Phone number should match.");
        assertEquals("student", user.getRole(), "Role should match.");
        assertEquals("rawan@@R123", user.getPassword(), "Password should match.");
    }


    @Test
    public void testSetters() {
        User user = new User();

        user.setFullName("anwar ali");
        user.setUsername("anwarrali");
        user.setEmailAddress("anwarr@gamil.com");
        user.setNumber("1236547895");
        user.setPassword("passAnwar30@");
        user.setRole("student");

        assertEquals("anwar ali", user.getFullName(), "Full name should match after update.");
        assertEquals("anwarrali", user.getUsername(), "Username should match after update.");
        assertEquals("anwarr@gamil.com", user.getEmailaddress(), "Email address should match after update.");
        assertEquals("1236547895", user.getNumber(), "Phone number should match after update.");
        assertEquals("student", user.getRole(), "Role should match after update.");
        assertEquals("passAnwar30@", user.getPassword(), "Password should match after update.");
    }

    @Test
    public void testToString() {
        User user = new User("anwar ali", "anwarrali", "anwarr@gamil.com", "1236547895", "passAnwar30@", "student");

        String expectedString = "User{firstName='anwar ali', username='anwarrali', emailAddress='anwarr@gamil.com', number='1236547895', role='student'}";
        assertEquals(expectedString, user.toString(), "The toString() method should return the expected string.");
    }
}
