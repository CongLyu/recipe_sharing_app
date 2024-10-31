package main.java.view.menu;

import main.java.text.MessageText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * message menu for admin user to check their inbox or send notification to all users.
 */
public class AdminMessageMenu extends SwingMenu {

    public AdminMessageMenu(ActionHandler[] actionHandlers) {
        super(new MessageText().getMessage(), actionHandlers);

        MessageText messageText = new MessageText();

        JMenuItem inboxMenuItem = new JMenuItem(messageText.getInbox());
        inboxMenuItem.setActionCommand(messageText.getInbox());
        inboxMenuItem.addActionListener(actionHandlers[0]);

        JMenuItem sendMenuItem = new JMenuItem(messageText.getNotifyAll());
        sendMenuItem.setActionCommand(messageText.getNotifyAll());
        sendMenuItem.addActionListener(actionHandlers[1]);

        this.add(inboxMenuItem);
        this.add(sendMenuItem);
    }
}
