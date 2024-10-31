package main.java.view_handler.login;

import main.java.controller.AppController;
import main.java.controller.UserController;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.LoginText;
import main.java.view.menu.SwingMenuBarBuilder;
import main.java.view.panel.IdlePanel;
import main.java.view.panel.LoginPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignInHandler extends ActionHandler {

    private final UserController userController;

    public SignInHandler(AppController[] controllers, User user) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LoginPanel loginPanel =
                (LoginPanel) ((Component) event.getSource()).getParent().getParent().getParent();
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        if (username != null && !username.isBlank()
                && password != null && !password.isBlank()) {
            User user = this.userController.logIn(username, password);
            if (user != null) {
                handleSuccess(event, user);
            } else {
                handleFailure(event);
            }
        } else {
            handleFailure(event);
        }
    }

    private void handleSuccess(ActionEvent event, User user) {
        for (AppController controller: this.controllers) {
            controller.save();
        }

        SwingMenuBarBuilder builder =
                new SwingMenuBarBuilder(this.controllers, user);
        if (userController.isAdmin(user)) {
            builder = builder.adminAccountMenu().recipeMenu().adminMessageMenu();
        } else {
            builder = builder.regularAccountMenu().recipeMenu().regularMessageMenu();
        }
        JMenuBar menuBar = builder.searchMenu().exitMenu().build();

        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        appFrame.setJMenuBar(menuBar);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(
                new IdlePanel(new ActionHandler[0],
                        user.getUsername(),
                        this.userController.getLoginRecords(user, 1).get(0).toString()));
        appFrame.setVisible(true);
    }

    private void handleFailure(ActionEvent event) {
        JOptionPane.showMessageDialog(
                SwingUtilities.getRoot((Component) event.getSource()),
                new LoginText().getSignInFailure(),
                new CommonText().getError(), JOptionPane.ERROR_MESSAGE);
    }
}
