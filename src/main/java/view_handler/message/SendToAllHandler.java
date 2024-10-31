package main.java.view_handler.message;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.controller.UserController;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.MessageText;
import main.java.view.panel.NotifyAllPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SendToAllHandler extends ActionHandler {

    private final UserController userController;

    private final MessageController messageController;

    public SendToAllHandler(AppController[] controllers, User user) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
        this.messageController = (MessageController) controllers[2];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        NotifyAllPanel sourcePanel = (NotifyAllPanel) appFrame.getContentPane();
        String subject = sourcePanel.getSubjectFieldText();
        String content = sourcePanel.getContentFieldText();
        this.messageController.sendToAll(
                this.userController.getAllUser(), this.currUser.getUid(), subject, content);
        for (AppController controller: this.controllers) {
            controller.save();
        }

        JOptionPane.showMessageDialog(
                appFrame,
                new MessageText().getSendSuccess(),
                new CommonText().getNotification(), JOptionPane.INFORMATION_MESSAGE);

        NotifyAllPanel notifyAllPanel = new NotifyAllPanel(
                new ActionHandler[]{new SendToAllHandler(this.controllers, this.currUser)});
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(notifyAllPanel);
        appFrame.setVisible(true);
    }
}
