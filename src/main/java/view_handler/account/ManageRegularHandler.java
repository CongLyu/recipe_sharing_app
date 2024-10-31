package main.java.view_handler.account;

import main.java.controller.AppController;
import main.java.controller.UserController;
import main.java.model.user.RegularUser;
import main.java.model.user.User;
import main.java.text.AccountText;
import main.java.text.CommonText;
import main.java.view.panel.ManageRegularPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ManageRegularHandler extends ActionHandler {

    private final UserController userController;

    private final AccountText accountText = new AccountText();

    public ManageRegularHandler(AppController[] controllers, User user) {
        super(controllers, user);
        this.userController = (UserController) this.controllers[0];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        List<RegularUser> selectedRegularUsers = ((ManageRegularPanel) appFrame.getContentPane()).getSelectedUsers();
        if (((JButton) event.getSource()).getText().equals(accountText.getBanSelected())) {
            handleBan(appFrame, selectedRegularUsers);
        } else {
            handleDelete(appFrame, selectedRegularUsers);
        }
        for (AppController controller: this.controllers) {
            controller.save();
        }
        ManageRegularPanel manageRegularPanel = new ManageRegularPanel(
                new ActionHandler[]{new ManageRegularHandler(this.controllers, this.currUser)},
                this.userController.getAllRegular());
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(manageRegularPanel);
        appFrame.setVisible(true);
    }

    private void handleBan(JFrame appFrame, List<RegularUser> userList) {
        List<String> successUsernames = new ArrayList<>();
        List<String> failureUsernames = new ArrayList<>();
        for (RegularUser regularUser: userList) {
            String username = regularUser.getUsername();
            if (this.userController.banUser(username)) {
                successUsernames.add(username);
            } else {
                failureUsernames.add(username);
            }
        }
        CommonText commonText = new CommonText();
        if (!successUsernames.isEmpty()) {
            JOptionPane.showMessageDialog(
                    appFrame,
                    accountText.getBanSuccessNotification(
                            String.join(commonText.getCommaSeparator(), successUsernames)),
                    commonText.getNotification(), JOptionPane.INFORMATION_MESSAGE);
        }
        if (!failureUsernames.isEmpty()) {
            JOptionPane.showMessageDialog(
                    appFrame,
                    accountText.getBanFailureNotification(
                            String.join(commonText.getCommaSeparator(), failureUsernames)),
                    commonText.getNotification(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleDelete(JFrame appFrame, List<RegularUser> userList) {
        List<String> successUsernames = new ArrayList<>();
        List<String> failureUsernames = new ArrayList<>();
        for (RegularUser regularUser: userList) {
            String username = regularUser.getUsername();
            if (this.userController.deleteUser(username)) {
                successUsernames.add(username);
            } else {
                failureUsernames.add(username);
            }
        }
        CommonText commonText = new CommonText();
        if (!successUsernames.isEmpty()) {
            JOptionPane.showMessageDialog(
                    appFrame,
                    accountText.getDeleteSuccessNotification(
                            String.join(commonText.getCommaSeparator(), successUsernames)),
                    commonText.getNotification(), JOptionPane.INFORMATION_MESSAGE);
        }
        if (!failureUsernames.isEmpty()) {
            JOptionPane.showMessageDialog(
                    appFrame,
                    accountText.getDeleteFailureNotification(
                            String.join(commonText.getCommaSeparator(), failureUsernames)),
                    commonText.getNotification(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
