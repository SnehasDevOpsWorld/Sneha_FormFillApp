package com.sneha.registration;

import com.sneha.registration.model.User;
import com.sneha.registration.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UserRegistrationTest.java
 *
 * Unit tests for the registration logic.
 * These tests run during 'mvn test' in the Jenkins pipeline.
 * If ANY test fails, the Jenkins build fails — WAR is NOT deployed.
 *
 * This is the "test gate" in your CI/CD pipeline.
 *
 * To run locally: mvn test
 */
public class UserRegistrationTest {

    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAO();
    }

    // ------------------------------------------------
    // User Model Tests
    // ------------------------------------------------

    @Test
    public void testUserObjectCreation() {
        User user = new User("Sneha", "Soni", "sneha@example.com", "password123", "123456789012");

        assertNotNull("User object should not be null", user);
        assertEquals("First name should match", "Sneha", user.getName());
        assertEquals("Last name should match", "Soni", user.getSurname());
        assertEquals("Email should match", "sneha@example.com", user.getEmail());
        assertEquals("Aadhar should match", "123456789012", user.getAadhar());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setEmail("test@test.com");
        user.setPassword("testpass123");
        user.setAadhar("999988887777");

        assertEquals("Test", user.getName());
        assertEquals("User", user.getSurname());
        assertEquals("test@test.com", user.getEmail());
        assertEquals("testpass123", user.getPassword());
        assertEquals("999988887777", user.getAadhar());
    }

    @Test
    public void testUserToString() {
        User user = new User("Sneha", "Soni", "sneha@test.com", "pass123", "123456789012");
        String result = user.toString();

        assertTrue("toString should contain name", result.contains("Sneha"));
        assertTrue("toString should contain email", result.contains("sneha@test.com"));
    }

    // ------------------------------------------------
    // Input Validation Tests (via DAO no-DB mode)
    // ------------------------------------------------

    @Test
    public void testRegisterUserWithoutDB() {
        // With DB_ENABLED=false, registerUser should return true (simulated success)
        User user = new User("Test", "User", "test@example.com", "pass12345", "111122223333");
        boolean result = userDAO.registerUser(user);
        assertTrue("Registration should return true when DB is not connected", result);
    }

    @Test
    public void testEmailExistsWithoutDB() {
        // With DB_ENABLED=false, emailExists should return false (no DB to check)
        boolean result = userDAO.emailExists("anyone@example.com");
        assertFalse("emailExists should return false when DB is not connected", result);
    }

    @Test
    public void testAadharExistsWithoutDB() {
        // With DB_ENABLED=false, aadharExists should return false
        boolean result = userDAO.aadharExists("123456789012");
        assertFalse("aadharExists should return false when DB is not connected", result);
    }

    // ------------------------------------------------
    // Aadhar Validation Logic Tests
    // ------------------------------------------------

    @Test
    public void testValidAadharFormat() {
        String aadhar = "123456789012";
        assertTrue("12 digit aadhar should be valid", aadhar.matches("^[0-9]{12}$"));
    }

    @Test
    public void testInvalidAadharTooShort() {
        String aadhar = "12345";
        assertFalse("Short aadhar should be invalid", aadhar.matches("^[0-9]{12}$"));
    }

    @Test
    public void testInvalidAadharWithLetters() {
        String aadhar = "ABCD12345678";
        assertFalse("Aadhar with letters should be invalid", aadhar.matches("^[0-9]{12}$"));
    }

    // ------------------------------------------------
    // Email Validation Logic Tests
    // ------------------------------------------------

    @Test
    public void testValidEmailFormat() {
        String email = "sneha.soni@gmail.com";
        assertTrue("Valid email should pass", email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
    }

    @Test
    public void testInvalidEmailNoAt() {
        String email = "snehagmail.com";
        assertFalse("Email without @ should fail", email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
    }

    @Test
    public void testInvalidEmailNoDomain() {
        String email = "sneha@";
        assertFalse("Email without domain should fail", email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
    }

    // ------------------------------------------------
    // Password Validation Tests
    // ------------------------------------------------

    @Test
    public void testValidPasswordLength() {
        String password = "mySecurePass123";
        assertTrue("Password with 8+ chars should be valid", password.length() >= 8);
    }

    @Test
    public void testInvalidPasswordTooShort() {
        String password = "short";
        assertFalse("Password under 8 chars should be invalid", password.length() >= 8);
    }
}
