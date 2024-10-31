package main.java.model.user;

/**
 * Admin user entity.
 * Admin user can create a new admin after they log in, and ban or delete any regular user that exists in the system.
 * AdminUser inherits User.
 */
public class AdminUser extends User {
    /**
     * Constructs a new AdminUser object with username and password.
     * @param username the username for the new admin user
     * @param password the password for the new admin user
     */
    public AdminUser(String username, String password) {
        super(username, password);
    }

    /**
     * Return the string representation of the admin user.
     * @return the string representation of the admin user
     */
    @Override
    public String toString() {
        return String.format("AdminUser{username=%s, lastLogin=%s}",
                this.getUsername(), getLastLoginStr());
    }
}
