package fx.test;


import fx.classes.SessionManager;
import fx.classes.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionManagerTest {

    @Test
    public void testSingletonInstance() {
        SessionManager instance1 = SessionManager.getInstance();
        SessionManager instance2 = SessionManager.getInstance();

        assertSame(instance1, instance2, "Both instances should be the same.");
    }

    @Test
    public void testSetAndGetCurrentUser() {

        User user = new User("rawan fraihat", "rawanfr", "rawan@gmail.com", "1234567890", "rawan@@R123", "student");


        SessionManager.getInstance().setCurrentUser(user);
        User currentUser = SessionManager.getInstance().getCurrentUser();

        assertNotNull(currentUser, "Current user should not be null.");
        assertEquals(user.getFullName(), currentUser.getFullName(), "The current user's full name should match.");
        assertEquals(user.getUsername(), currentUser.getUsername(), "The current user's username should match.");
        assertEquals(user.getEmailaddress(), currentUser.getEmailaddress(), "The current user's email address should match.");
        assertEquals(user.getNumber(), currentUser.getNumber(), "The current user's number should match.");
        assertEquals(user.getRole(), currentUser.getRole(), "The current user's role should match.");
    }
}
