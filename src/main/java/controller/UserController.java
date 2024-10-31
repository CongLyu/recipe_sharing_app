package main.java.controller;

import main.java.model.user.RegularUser;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.usecase.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.lang.Math.min;

/**
 * A controller for all Users
 */
public class UserController extends AppController {

    private final String[] USER_CONTROLLER_PATH_NAMES =
            {"src", "main", "resource", "user_controller.ser"};

    private final String USER_CONTROLLER_PATH =
            String.join(File.separator, USER_CONTROLLER_PATH_NAMES);

    private final String[] BUILTIN_ADMIN_PATH_NAMES =
            {"src", "main", "resource", "built_in_admin_info.txt"};

    private final String BUILTIN_ADMIN_PATH =
            String.join(File.separator, BUILTIN_ADMIN_PATH_NAMES);

    private final UserManager userManager;

    /**
     * read the existed controller in from the default path, if it does not exist, create a new one and save it.
     */
    public UserController() {
        UserController loadedUserController =
                (UserController) load(USER_CONTROLLER_PATH);
        if (loadedUserController == null) {
            this.userManager = new UserManager();
            addDefaultAdmin();
            save();
        } else {
            this.userManager = loadedUserController.getUserManager();
        }
    }

    @Override
    public void save() {
        this.write(USER_CONTROLLER_PATH);
    }

    /**
     * add default admin user from BUILTIN_ADMIN_PATH to userManager
     */
    private void addDefaultAdmin() {
        try {
            FileInputStream fileIn = new FileInputStream(BUILTIN_ADMIN_PATH);
            InputStreamReader iReader = new InputStreamReader(fileIn);
            BufferedReader bReader = new BufferedReader(iReader);
            String delimiter = new CommonText().getSpaceSeparator();
            String line;
            while ((line = bReader.readLine()) != null) {
                line = line.strip();
                String username = line.split(delimiter)[0];
                String password = line.split(delimiter)[1];
                register(username, password, true);
            }
            iReader.close();
            bReader.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the userManager stored in the controller.
     */
    public UserManager getUserManager() {
        return this.userManager;
    }

    /**
     *  if isAdmin, then register the new user with username and password as an admin user, else, register the new user
     *  as regular user
     */
    public User register(String username, String password, boolean isAdmin) {
        if (!userManager.isExistingUser(username)) {
            if (isAdmin) {
                return this.userManager.addAdminUser(username, password);
            } else {
                return this.userManager.addRegularUser(username, password);
            }
        }
        return null;
    }

    /**
     * if the username and password matches one of the users in userManager, then return that user, else, return null
     */
    public User logIn(String username, String password) {
        if (this.userManager.isValidUser(username)) {
            User user = this.userManager.getAdmin(username);
            if (user == null) {
                user = this.userManager.getRegular(username);
            }
            if (password.equals(user.getPassword())) {
                this.userManager.recordCurrLogin(user);
                return user;
            }
        }
        return null;
    }

    /**
     * return true if the type of the user is admin
     */
    public boolean isAdmin(User user) {
        return this.userManager.isAdmin(user);
    }

    /**
     * return the loginRecords of the user, if the login history exceeded threshold, return the part that doesn't
     * exceed the threshold
     */
    public List<Date> getLoginRecords(User user, int threshold) {
        List<Date> loginRecords = this.userManager.getAllLoginRecords(user);
        if (threshold >= 0) {
            return loginRecords.subList(0, min(threshold, loginRecords.size()));
        } else {
            return loginRecords;
        }
    }

    /**
     * return the list of regular users that ares stored in userManager
     */
    public List<RegularUser> getAllRegular() {
        List<RegularUser> regularUserList = new ArrayList<>();
        for (RegularUser regularUser: this.userManager.getRegularUserList()) {
            if (!this.userManager.isDeleted(regularUser)) {
                regularUserList.add(regularUser);
            }
        }
        regularUserList.sort(Comparator.comparing(User::getUsername));
        return regularUserList;
    }

    /**
     * @return the list of all the regular users in the userManager.
     */
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        for (RegularUser regularUser: this.userManager.getRegularUserList()) {
            if (!this.userManager.isDeleted(regularUser)) {
                userList.add(regularUser);
            }
        }
        userList.addAll(this.userManager.getAdminUserList());
        userList.sort(Comparator.comparing(User::getUsername));
        return userList;
    }

    /**
     * ban the regularUser if they existed in userManager, and return true, else, return false
     */
    public boolean banUser(String username) {
        RegularUser regularUser = this.userManager.getRegular(username);
        if (regularUser != null) {
            return this.userManager.banRegular(regularUser);
        }
        return false;
    }

    /**
     * delete the regular if username existed in the list of regular users in userManager, and return true, else,
     * return false
     */
    public boolean deleteUser(String username) {
        RegularUser regularUser = this.userManager.getRegular(username);
        if (regularUser != null) {
            return this.userManager.deleteRegular(regularUser);
        }
        return false;
    }

    /**
     * @param uid the id of the user that we are searching for.
     * @return the corresponding user.
     */
    public String getUsernameById(String uid) {
        User user = this.userManager.getById(uid);
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }
}
