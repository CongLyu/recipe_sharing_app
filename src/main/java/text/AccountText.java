package main.java.text;

public class AccountText {

    public String getAccountText() {
        return "Account";
    }

    public String getLoginHistory() {
        return "Login History";
    }

    public String getCreateAdmin() {
        return "Create Admin";
    }

    public String getManageNonAdmin() {
        return "Manage Non-admin";
    }

    public String getAllRegularTitle() {
        return "All regular users are listed as follows.";
    }

    public String getUserInfoTemplate() {
        return "Username: %s\nLast login time: %s";
    }

    public String[] getLoginHistoryTableColNames() {
        return new String[]{"Year", "Month", "Day", "Time", "Time Zone"};
    }

    public String[] getRegularUserTableColNames() {
        return new String[]{"Name", "Last Login Time", "# Login Records", "Is Banned"};
    }

    public String getDeleteSelected() {
        return "Delete Selected";
    }

    public String getBanSelected() {
        return "Ban Selected";
    }

    public String getAllHistoryTitle(String username, int totalNum) {
        return String.format("%s's all %d login record(s):", username, totalNum);
    }

    public String getBanSuccessNotification(String usernames) {
        return String.format("Successfully ban %s!", usernames);
    }

    public String getBanFailureNotification(String usernames) {
        return String.format("Sorry. Fail to ban %s.", usernames);
    }

    public String getDeleteSuccessNotification(String usernames) {
        return String.format("Successfully delete %s!", usernames);
    }

    public String getDeleteFailureNotification(String usernames) {
        return String.format("Sorry. Fail to delete %s.", usernames);
    }
}
