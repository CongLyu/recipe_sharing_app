package test.usecase;

import main.java.model.user.AdminUser;
import main.java.model.user.RegularUser;
import main.java.usecase.UserManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserManagerTest {

    private String adminUsername1, adminPassword1, adminUsername2, adminPassword2;

    private String regularUsername1, regularPassword1, regularUsername2, regularPassword2;

    @Before
    public void setUp(){
        adminUsername1 = "Bob";
        adminPassword1 = "Admin666";
        adminUsername2 = "Mary";
        adminPassword2 = "123HelloWorld";
        regularUsername1 = "Kevin";
        regularPassword1 = "Regular789";
        regularUsername2 = "Stuart";
        regularPassword2 = "009Dancing";
    }

    @Test
    public void testConstructorAdminUserList() {
        UserManager userManager = new UserManager();
        List<AdminUser> expected = new ArrayList<>();
        Assert.assertEquals(userManager.getAdminUserList(), expected);
    }

    @Test
    public void testConstructorRegularUserList() {
        UserManager userManager = new UserManager();
        List<RegularUser> expected = new ArrayList<>();
        Assert.assertEquals(userManager.getRegularUserList(), expected);
    }

    @Test
    public void testAddSingleAdminUser() {
        UserManager userManager = new UserManager();
        List<AdminUser> expected = new ArrayList<>();
        AdminUser adminUser = userManager.addAdminUser(adminUsername1, adminPassword1);
        expected.add(adminUser);
        Assert.assertEquals(userManager.getAdminUserList(), expected);
    }

    @Test
    public void testAddMultipleAdminUser() {
        UserManager userManager = new UserManager();
        List<AdminUser> expected = new ArrayList<>();
        AdminUser adminUser1 = userManager.addAdminUser(adminUsername1, adminPassword1);
        expected.add(adminUser1);
        AdminUser adminUser2 = userManager.addAdminUser(adminUsername2, adminPassword2);
        expected.add(adminUser2);
        Assert.assertEquals(userManager.getAdminUserList(), expected);
    }

    @Test
    public void testAddSingleRegularUser() {
        UserManager userManager = new UserManager();
        List<RegularUser> expected = new ArrayList<>();
        RegularUser regularUser = userManager.addRegularUser(regularUsername1, regularPassword1);
        expected.add(regularUser);
        Assert.assertEquals(userManager.getRegularUserList(), expected);
    }

    @Test
    public void testAddMultipleRegularUser() {
        UserManager userManager = new UserManager();
        List<RegularUser> expected = new ArrayList<>();
        RegularUser regularUser1 = userManager.addRegularUser(regularUsername1, regularPassword1);
        expected.add(regularUser1);
        RegularUser regularUser2 = userManager.addRegularUser(regularUsername2, regularPassword2);
        expected.add(regularUser2);
        Assert.assertEquals(userManager.getRegularUserList(), expected);
    }

    @Test
    public void testIsExistingUserForAdminUser() {
        UserManager userManager = new UserManager();
        userManager.addAdminUser(adminUsername1, adminPassword1);
        Assert.assertTrue(userManager.isExistingUser(adminUsername1));
    }

    @Test
    public void testIsNotExistingUserForAdminUser() {
        UserManager userManager = new UserManager();
        userManager.addAdminUser(adminUsername1, adminPassword1);
        Assert.assertFalse(userManager.isExistingUser(adminUsername2));
    }

    @Test
    public void testIsExistingUserForRegularUser() {
        UserManager userManager = new UserManager();
        userManager.addRegularUser(regularUsername1, regularPassword1);
        Assert.assertTrue(userManager.isExistingUser(regularUsername1));
    }

    @Test
    public void testIsNotExistingUserForRegularUser() {
        UserManager userManager = new UserManager();
        userManager.addRegularUser(regularUsername1, regularPassword1);
        Assert.assertFalse(userManager.isExistingUser(regularUsername2));
    }
}
