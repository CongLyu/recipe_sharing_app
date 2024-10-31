package main.java.view.menu;

import main.java.text.AccountText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * admin menu that allows admin user to create new admin user, manage regular user, or check their login history.
 */
public class AdminAccountMenu extends SwingMenu {

    public AdminAccountMenu(ActionHandler[] actionHandlers) {
        super(new AccountText().getAccountText(), actionHandlers);

        AccountText accountText = new AccountText();

        JMenuItem createAdminMenuItem = new JMenuItem(accountText.getCreateAdmin());
        createAdminMenuItem.setActionCommand(accountText.getCreateAdmin());
        createAdminMenuItem.addActionListener(actionHandlers[0]);

        JMenuItem manageRegularMenuItem = new JMenuItem(accountText.getManageNonAdmin());
        manageRegularMenuItem.setActionCommand(accountText.getManageNonAdmin());
        manageRegularMenuItem.addActionListener(actionHandlers[1]);

        JMenuItem loginHistoryMenuItem = new JMenuItem(accountText.getLoginHistory());
        loginHistoryMenuItem.setActionCommand(accountText.getLoginHistory());
        loginHistoryMenuItem.addActionListener(actionHandlers[2]);

        this.add(createAdminMenuItem);
        this.add(manageRegularMenuItem);
        this.add(loginHistoryMenuItem);
    }
}
