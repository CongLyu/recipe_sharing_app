package main.java.view.menu;

import main.java.text.MessageText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * menu that let regular user check the messages in their inbox.
 */
public class RegularMessageMenu extends SwingMenu {

    public RegularMessageMenu(ActionHandler[] actionHandlers) {
        super(new MessageText().getMessage(), actionHandlers);

        MessageText messageText = new MessageText();
        JMenuItem inboxMenuItem = new JMenuItem(messageText.getInbox());
        inboxMenuItem.setActionCommand(messageText.getInbox());
        inboxMenuItem.addActionListener(actionHandlers[0]);

        this.add(inboxMenuItem);
    }
}
