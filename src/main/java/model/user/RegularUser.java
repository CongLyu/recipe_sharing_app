package main.java.model.user;

/**
 * Regular user entity.
 * Regular user can be banned or soft deleted by admin user, they also have access to the recipe menu.
 * RegularUser inherits User.
 */
public class RegularUser extends User {

    private boolean isBanned;

    private boolean isDeleted;

    /**
     * Constructs a new RegularUser with the given username and password.
     * @param username the username for the new regular user
     * @param password the password for the new regular user
     */
    public RegularUser(String username, String password) {
        super(username, password);
        this.isBanned = false;
        this.isDeleted = false;
    }

    /**
     * Gets the string representation of this RegularUser.
     * @return the string format of the regular user
     */
    @Override
    public String toString() {
        return String.format("RegularUser{username=%s, isBanned=%b, lastLogin=%s}",
                this.getUsername(), this.isBanned(), this.getLastLoginStr());
    }

    /**
     * Returns true if and only if the user is banned
     * @return true if and only if the user is banned
     */
    public boolean isBanned() {
        return this.isBanned;
    }

    /**
     * Sets the isBanned attribute to be true.
     */
    public void ban() {
        this.isBanned = true;
    }

    /**
     * Return true if and only if the user is deleted.
     * @return true if and only if the user is deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the isDeleted attribute to be true.
     */
    public void delete() {
        this.isDeleted = true;
    }
}