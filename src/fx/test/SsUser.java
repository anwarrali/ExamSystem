package fx.test;

import static org.junit.jupiter.api.Assertions.*;

import fx.classes.SessionManager;
import fx.classes.User;
import org.junit.jupiter.api.Test;

public class SsUser {

    @Test
    public void testSessionManagerStoresUserCorrectly() {
        User user = new User(
                "Anwar Ali",
                "anwar99",
                "anwar@example.com",
                "0591234567",
                "password123",
                "Student"
        );
        SessionManager session = SessionManager.getInstance();
        session.setCurrentUser(user);
        User retrievedUser = session.getCurrentUser();

        assertNotNull(retrievedUser);
        assertEquals("Anwar Ali", retrievedUser.getFullName());
        assertEquals("anwar99", retrievedUser.getUsername());
        assertEquals("anwar@example.com", retrievedUser.getEmailaddress());
        assertEquals("0591234567", retrievedUser.getNumber());
        assertEquals("Student", retrievedUser.getRole());
    }
}
