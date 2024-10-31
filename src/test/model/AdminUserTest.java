package test.model;

import main.java.model.user.AdminUser;
import main.java.text.CommonText;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertNotEquals;

public class AdminUserTest {

    private String username1, password1, username2, password2;

    @Before
    public void setUp(){
        username1 = "Bob";
        password1 = "Admin666";
        username2 = "Mary";
        password2 = "123HelloWorld";
    }

    @Test
    public void testConstructorUsername() {
        AdminUser user = new AdminUser(username1, password1);
        Assert.assertEquals(user.getUsername(), username1);
    }

    @Test
    public void testConstructorPassword() {
        AdminUser user = new AdminUser(username1, password1);
        Assert.assertEquals(user.getPassword(), password1);
    }

    @Test
    public void testConstructorLoginHistory() {
        AdminUser user = new AdminUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testEqual() {
        AdminUser user = new AdminUser(username1, password1);
        Assert.assertEquals(user, user);
    }

    @Test
    public void testNotEqualSameInfo() {
        AdminUser user1 = new AdminUser(username1, password1);
        AdminUser user2 = new AdminUser(username1, password1);
        assertNotEquals(user1, user2);
    }

    @Test
    public void testEqualDiffInfo() {
        AdminUser user1 = new AdminUser(username1, password1);
        AdminUser user2 = new AdminUser(username2, password2);
        assertNotEquals(user1, user2);
    }

    @Test
    public void testToString() {
        AdminUser user = new AdminUser(username1, password1);
        String expected = "AdminUser{username=Bob, lastLogin=None}";
        Assert.assertEquals(user.toString(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithNoLogin() {
        AdminUser user = new AdminUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithSingleLogin() {
        AdminUser user = new AdminUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Date loginTime = new Date();
        user.recordLoginTime(loginTime);
        expected.add(loginTime);
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithMultipleLogin() {
        AdminUser user = new AdminUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Date loginTime1 = new Date();
        user.recordLoginTime(loginTime1);
        expected.add(loginTime1);
        Date loginTime2 = new Date();
        user.recordLoginTime(loginTime2);
        expected.add(loginTime2);
        Date loginTime3 = new Date();
        user.recordLoginTime(loginTime3);
        expected.add(loginTime3);
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testLastLoginStrWithNoLogin() {
        AdminUser user = new AdminUser(username1, password1);
        String expected = new CommonText().getNone();
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }

    @Test
    public void testLastLoginStrWithSingleLogin() {
        AdminUser user = new AdminUser(username1, password1);
        Date latestLoginTime = new Date();
        user.recordLoginTime(latestLoginTime);
        String expected = new SimpleDateFormat(
                "EEE MMM yyyy HH:mm:ss.SSS Z", Locale.ENGLISH)
                .format(latestLoginTime);
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }

    @Test
    public void testLastLoginStrWithMultipleLogin() {
        AdminUser user = new AdminUser(username1, password1);
        user.recordLoginTime(new Date());
        user.recordLoginTime(new Date());
        Date latestLoginTime = new Date();
        user.recordLoginTime(latestLoginTime);
        String expected = new SimpleDateFormat(
                "EEE MMM yyyy HH:mm:ss.SSS Z", Locale.ENGLISH)
                .format(latestLoginTime);
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }
}
