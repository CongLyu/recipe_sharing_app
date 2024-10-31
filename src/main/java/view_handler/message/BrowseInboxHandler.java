package main.java.view_handler.message;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.controller.UserController;
import main.java.model.message.Message;
import main.java.model.user.User;
import main.java.text.MessageText;
import main.java.view.panel.InboxPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class BrowseInboxHandler extends ActionHandler {

    protected final UserController userController;

    protected final MessageController messageController;

    public BrowseInboxHandler(AppController[] controllers, User user) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
        this.messageController = (MessageController) controllers[2];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        List<Message> messageList = this.messageController.getInboxMessages(this.currUser);
        List<String> senderNameList = new ArrayList<>();
        for (Message message: messageList) {
            String senderName = this.userController.getUsernameById(message.getSenderId());
            if (senderName == null) {
                senderName = new MessageText().getSystem();
            }
            senderNameList.add(senderName);
        }
        InboxPanel inboxPanel = new InboxPanel(
                new ActionHandler[0],
                messageList, senderNameList);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(inboxPanel);
        appFrame.setVisible(true);
    }
}
