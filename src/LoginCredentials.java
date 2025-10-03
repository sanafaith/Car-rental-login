/**
 LoginCredentials.java
 Contains hardcoded login credentials for the Car Rental System
 */
public class LoginCredentials {

    // Hardcoded valid credentials
    public static final String VALID_USERNAME = "faith";
    public static final String VALID_PASSWORD = "sanaipei";

    public static boolean validateCredentials(String username, String password) {
        // Check if username and password are not null or empty
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }

        // Validate credentials (case-sensitive)
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
}