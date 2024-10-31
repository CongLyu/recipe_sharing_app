package main.java.view_handler.account;

import main.java.controller.AppController;
import main.java.controller.UserController;
import main.java.model.user.User;
import main.java.view.panel.LoginHistoryPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginHistoryHandler extends ActionHandler {

    public LoginHistoryHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int listAllThreshold = -1;
        LoginHistoryPanel loginHistoryPanel = new LoginHistoryPanel(
                new ActionHandler[0],
                this.currUser.getUsername(),
                ((UserController) controllers[0]).getLoginRecords(this.currUser, listAllThreshold));

        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(loginHistoryPanel);
        appFrame.setVisible(true);
    }
}
