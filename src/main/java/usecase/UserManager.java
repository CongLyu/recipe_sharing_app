package main.java.usecase;

import main.java.model.user.AdminUser;
import main.java.model.user.RegularUser;
import main.java.model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserManager extends EntityManager {

    private final List<AdminUser> adminUserList;

    private final List<RegularUser> regularUserList;

    /**
     * Constructs a new UserManager and initialize an empty adminUserList and a regularUserList.
     */
    public UserManager() {
        this.adminUserList = new ArrayList<>();
        this.regularUserList = new ArrayList<>();
    }

    /**
     * Returns regularUserList.
     * @return regularUserList
     */
    public List<RegularUser> getRegularUserList() {
        return regularUserList;
    }

    /**
     * Returns adminUserList.
     * @return adminUserList
     */
    public List<AdminUser> getAdminUserList() {
        return adminUserList;
    }


    /**
     * Adds new admin user to adminUserList, and return that admin user.
     * @param username username of the new AdminUser
     * @param password password of the new AdminUser
     * @return new AdminUser
     */
    public AdminUser addAdminUser(String username, String password) {
        AdminUser newAdmin = new AdminUser(username, password);
        this.adminUserList.add(newAdmin);
        return newAdmin;
    }


    /**
     * Adds a new regular user to regularUserList, and return that regular user.
     * @param username username of the RegularUser
     * @param password password of the RegularUser
     * @return new RegularUser
     */
    public RegularUser addRegularUser(String username, String password) {
        RegularUser newRegular = new RegularUser(username, password);
        this.regularUserList.add(newRegular);
        return newRegular;
    }

    /**
     * Return true if and only if the username exists in either adminUserList or regularUserList.
     * @param username username of the user needs to be checked the existence
     * @return true if and only if the username exists in either adminUserList or regularUserList
     */
    public boolean isExistingUser(String username) {
        return isAdmin(username) || isRegular(username);
    }

    /**
     * Gets a user by uid.
     * @param uid uid of the user
     * @return user with the corresponding uid
     */
    public User getById(String uid) {
        for (RegularUser regularUser: this.regularUserList) {
            if (regularUser.getUid().equals(uid)) {
                return regularUser;
            }
        }
        for (AdminUser adminUser: this.adminUserList) {
            if (adminUser.getUid().equals(uid)) {
                return adminUser;
            }
        }
        return null;
    }

    /**
     * Returns true if and only if the user exists in the adminUserList.
     * @param user user needs to be checked
     * @return true if and only if the user exists in the adminUserList
     */
    public boolean isAdmin(User user) {
        return user instanceof AdminUser
                && this.adminUserList.contains((AdminUser) user);
    }

    /**
     * Returns true if and only if the username exists in the adminUserList.
     * @param username username of the user needs to be checked
     * @return true if and only if the username exists in the adminUserList
     */
    private boolean isAdmin(String username) {
        for (AdminUser adminUser: this.adminUserList) {
            if (adminUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the admin user with username in the adminUserList
     * @param username username of the AdminUser
     * @return the AdminUser
     */
    public AdminUser getAdmin(String username) {
        for (AdminUser adminUser: adminUserList) {
            if (username.equals(adminUser.getUsername())) {
                return adminUser;
            }
        }
        return null;
    }

    /**
     * Returns true if and only if the regular user exists in the regularUserList.
     * @param user username of user needs to be checked
     * @return true if and only if the regular user exists in the regularUserList
     */
    private boolean isRegular(User user) {
        return user instanceof RegularUser
                && this.regularUserList.contains((RegularUser) user);
    }

    /**
     * Returns true if and only if the username exists in the regularUserList.
     * @param username username of the user needs to be checked
     * @return true if and only if the username exists in the regularUserList
     */
    private boolean isRegular(String username) {
        for (RegularUser regularUser: this.regularUserList) {
            if (regularUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the regular user with username in the adminUserList.
     * @param username username of the RegularUser
     * @return the regular user with username in the adminUserList
     */
    public RegularUser getRegular(String username) {
        for (RegularUser regularUser: regularUserList) {
            if (username.equals(regularUser.getUsername())) {
                return regularUser;
            }
        }
        return null;
    }

    /**
     * Adds current local date to the login history of user.
     * @param user the user who logs in
     */
    public void recordCurrLogin(User user) {
        user.recordLoginTime(new Date());
    }


    /**
     * Returns the login history of the user.
     * @param user user
     * @return the login history of the user
     */
    public List<Date> getAllLoginRecords(User user) {
        return new ArrayList<>(user.getLoginHistory());
    }


    /**
     * Returns true if and only if the regular user with username is neither banned nor deleted, and false otherwise.
     * @param username username of the user needs to be checked
     * @return true if and only if the regular user with username is neither banned nor deleted, and false otherwise
     */
    private boolean isValidRegular(String username) {
        for (RegularUser regularUser: this.regularUserList) {
            if (regularUser.getUsername().equals(username)
                    && !regularUser.isBanned() && !regularUser.isDeleted()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns true if the user with username is valid.
     * @param username username of user needs to be checked
     * @return true if the user with username is valid
     */
    public boolean isValidUser(String username) {
        return isAdmin(username) || isValidRegular(username);
    }


    /**
     * Returns true if the user exists in the regularUserList and not deleted, and set that regular user as banned,
     * return false otherwise.
     * @param user user needs to be banned
     * @return true if the user exists in the regularUserList and not deleted, and set that regular user as banned,
     * return false otherwise.
     */
    public boolean banRegular(User user) {
        if (isRegular(user) && !((RegularUser) user).isDeleted()) {
            ((RegularUser) user).ban();
            return true;
        }
        return false;
    }

    /**
     * Returns true if the regular user is deleted.
     * @param user user needs to be deleted
     * @return true if the regular user is deleted
     */
    public boolean isDeleted(User user) {
        if (isRegular(user)) {
            return ((RegularUser) user).isDeleted();
        }
        return false;
    }

    /**
     * Returns true if the user with username exists in regularUserList, and set that user as deleted, return false
     * otherwise.
     * @param user user needs to be checked
     * @return true if the user with username exists in regularUserList, and set that user as deleted, return false
     * otherwise
     */
    public boolean deleteRegular(User user) {
        if (isRegular(user)) {
            ((RegularUser) user).delete();
            return true;
        }
        return false;
    }
}
