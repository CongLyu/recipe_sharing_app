package test.model;

import main.java.model.user.RegularUser;
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

public class RegularUserTest {

    private String username1, password1, username2, password2;

    @Before
    public void setUp(){
        username1 = "Kevin";
        password1 = "Regular789";
        username2 = "Stuart";
        password2 = "009Dancing";
    }

    @Test
    public void testConstructorUsername() {
        RegularUser user = new RegularUser(username1, password1);
        Assert.assertEquals(user.getUsername(), username1);
    }

    @Test
    public void testConstructorPassword() {
        RegularUser user = new RegularUser(username1, password1);
        Assert.assertEquals(user.getPassword(), password1);
    }

    @Test
    public void testConstructorLoginHistory() {
        RegularUser user = new RegularUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testEqual() {
        RegularUser user = new RegularUser(username1, password1);
        Assert.assertEquals(user, user);
    }

    @Test
    public void testNotEqualSameInfo() {
        RegularUser user1 = new RegularUser(username1, password1);
        RegularUser user2 = new RegularUser(username1, password1);
        assertNotEquals(user1, user2);
    }

    @Test
    public void testEqualDiffInfo() {
        RegularUser user1 = new RegularUser(username1, password1);
        RegularUser user2 = new RegularUser(username2, password2);
        assertNotEquals(user1, user2);
    }

    @Test
    public void testToString() {
        RegularUser user = new RegularUser(username1, password1);
        String expected = "RegularUser{username=Kevin, isBanned=false, lastLogin=None}";
        Assert.assertEquals(user.toString(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithNoLogin() {
        RegularUser user = new RegularUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithSingleLogin() {
        RegularUser user = new RegularUser(username1, password1);
        List<Date> expected = new ArrayList<>();
        Date loginTime = new Date();
        user.recordLoginTime(loginTime);
        expected.add(loginTime);
        Assert.assertEquals(user.getLoginHistory(), expected);
    }

    @Test
    public void testRecordLoginHistoryWithMultipleLogin() {
        RegularUser user = new RegularUser(username1, password1);
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
        RegularUser user = new RegularUser(username1, password1);
        String expected = new CommonText().getNone();
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }

    @Test
    public void testLastLoginStrWithSingleLogin() {
        RegularUser user = new RegularUser(username1, password1);
        Date latestLoginTime = new Date();
        user.recordLoginTime(latestLoginTime);
        String expected = new SimpleDateFormat(
                "EEE MMM yyyy HH:mm:ss.SSS Z", Locale.ENGLISH)
                .format(latestLoginTime);
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }

    @Test
    public void testLastLoginStrWithMultipleLogin() {
        RegularUser user = new RegularUser(username1, password1);
        user.recordLoginTime(new Date());
        user.recordLoginTime(new Date());
        Date latestLoginTime = new Date();
        user.recordLoginTime(latestLoginTime);
        String expected = new SimpleDateFormat(
                "EEE MMM yyyy HH:mm:ss.SSS Z", Locale.ENGLISH)
                .format(latestLoginTime);
        Assert.assertEquals(user.getLastLoginStr(), expected);
    }

    @Test
    public void testNotBanned() {
        RegularUser user = new RegularUser(username1, password1);
        Assert.assertFalse(user.isBanned());
    }

    @Test
    public void testBanned() {
        RegularUser user = new RegularUser(username1, password1);
        user.ban();
        Assert.assertTrue(user.isBanned());
    }

    @Test
    public void testNotDeleted() {
        RegularUser user = new RegularUser(username1, password1);
        Assert.assertFalse(user.isDeleted());
    }

    @Test
    public void testDeleted() {
        RegularUser user = new RegularUser(username1, password1);
        user.delete();
        Assert.assertTrue(user.isDeleted());
    }
}
