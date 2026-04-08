package messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessengerTest {
    // 1. Username Validation Tests
    @Test
    void testUsernameCorrectlyFormatted() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertTrue(login.checkUserName("Kyl_1"));
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", login.registerUser());
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        // Message is printed in main, not returned by method, so not checked here
    }

    // 2. Password Validation Tests
    @Test
    void testPasswordMeetsComplexity() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", login.registerUser());
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // 3. Cell Phone Number Tests
    @Test
    void testCellPhoneCorrectlyFormatted() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", login.registerUser());
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // 4. Login Tests
    @Test
    void testLoginSuccessful() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        login.checkUserName("Kyl_1");
        login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(login.loginUser("Kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle, Smith it is great to see you again.\n\n\n", login.returnLoginStatus(true));
    }

    @Test
    void testLoginFailed() {
        Messenger.Login login = new Messenger.Login("Kyle", "Smith");
        login.checkUserName("Kyl_1");
        login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertFalse(login.loginUser("wrong", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("Kyl_1", "wrongpass"));
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(false));
    }

    // 5. Boolean Method Tests (Summary)
    @Test
    void testBooleanMethods() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertTrue(login.checkUserName("Kyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
        login.checkUserName("Kyl_1");
        login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(login.loginUser("Kyl_1", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("wrong", "Ch&&sec@ke99!"));
    }
    @Test
    void testCheckUserName_Valid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertTrue(login.checkUserName("abc_d"));
    }

    @Test
    void testCheckUserName_Invalid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertFalse(login.checkUserName("abcd")); // No underscore
        assertFalse(login.checkUserName("ab_cd12")); // Too long
    }

    @Test
    void testCheckPasswordComplexity_Valid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertTrue(login.checkPasswordComplexity("Abcdef1!"));
    }

    @Test
    void testCheckPasswordComplexity_Invalid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertFalse(login.checkPasswordComplexity("abcdefg1!")); // No uppercase
        assertFalse(login.checkPasswordComplexity("ABCDEFGH!")); // No digit
        assertFalse(login.checkPasswordComplexity("Abcdefgh1")); // No special char
        assertFalse(login.checkPasswordComplexity("Ab1!")); // Too short
    }

    @Test
    void testCheckCellPhoneNumber_Valid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    void testCheckCellPhoneNumber_Invalid() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        assertFalse(login.checkCellPhoneNumber("0831234567")); // Missing +27
        assertFalse(login.checkCellPhoneNumber("+2783123456")); // Too short
        assertFalse(login.checkCellPhoneNumber("+278312345678")); // Too long
    }

    @Test
    void testLoginUser() {
        Messenger.Login login = new Messenger.Login("Test", "User");
        login.checkUserName("abc_d");
        login.checkPasswordComplexity("Abcdef1!");
        assertTrue(login.loginUser("abc_d", "Abcdef1!"));
        assertFalse(login.loginUser("abc_d", "wrongpass"));
        assertFalse(login.loginUser("wrong", "Abcdef1!"));
    }
}
