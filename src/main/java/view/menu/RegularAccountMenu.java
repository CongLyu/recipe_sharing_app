package main.java.view.menu;

import main.java.text.AccountText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * regular account menu that let user check their login history.
 */
public class RegularAccountMenu extends SwingMenu {

    public RegularAccountMenu(ActionHandler[] actionHandlers) {
        super(new AccountText().getAccountText(), actionHandlers);

        AccountText accountText = new AccountText();

        JMenuItem loginHistoryMenuItem = new JMenuItem(accountText.getLoginHistory());
        loginHistoryMenuItem.setActionCommand(accountText.getLoginHistory());
        loginHistoryMenuItem.addActionListener(actionHandlers[0]);

        this.add(loginHistoryMenuItem);
    }
}
