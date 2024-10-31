package main.java.view_handler.account;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.view.panel.LoginPanel;
import main.java.view_handler.ActionHandler;
import main.java.view_handler.login.SignInHandler;
import main.java.view_handler.login.SignUpHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateAdminHandler extends ActionHandler {

    public CreateAdminHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (AppController controller: controllers) {
            controller.save();
        }

        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        LoginPanel loginPanel = new LoginPanel(new ActionHandler[]{
                new SignInHandler(controllers, this.currUser),
                new SignUpHandler(controllers, this.currUser, true)},
                true);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(loginPanel);
        appFrame.setVisible(true);
    }
}
