package es.viewnext.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void testValidEmail() {
        String email = "example@example.com";
        assertTrue(Utils.validateEmail(email));
    }

    @Test
    public void testInvalidEmail() {
        String email = "invalid_email";
        assertFalse(Utils.validateEmail(email));
    }

    @Test
    public void testEmailWithSpaces() {
        String email = "example @example.com";
        assertFalse(Utils.validateEmail(email));
    }

    @Test
    public void testEmailWithSpecialCharacters() {
        String email = "example!@example.com";
        assertFalse(Utils.validateEmail(email));
    }

    @Test
    public void testEmailWithNoDomain() {
        String email = "example@";
        assertFalse(Utils.validateEmail(email));
    }

    @Test
    public void testEmailWithNoUsername() {
        String email = "@example.com";
        assertFalse(Utils.validateEmail(email));
    }
}
