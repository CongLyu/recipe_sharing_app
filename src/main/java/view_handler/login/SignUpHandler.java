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

public class SignUpHandler extends ActionHandler {

    private final UserController userController;

    private final boolean registerAdmin;

    public SignUpHandler(AppController[] controllers, User user, boolean registerAdmin) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
        this.registerAdmin = registerAdmin;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LoginPanel loginPanel =
                (LoginPanel) ((Component) event.getSource()).getParent().getParent().getParent();
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        if (username != null && !username.isBlank()
                && password != null && !password.isBlank()) {
            User user = this.userController.register(username, password, this.registerAdmin);
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
        for (AppController controller: controllers) {
            controller.save();
        }

        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        CommonText commonText = new CommonText();
        Object[] options = {commonText.getYes(), commonText.getNo()};
        LoginText loginText = new LoginText();
        int option = JOptionPane.showOptionDialog(appFrame,
                String.format(loginText.getConfirmLoginQuestionTemplate(), user.getUsername()),
                loginText.getSignIn(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (option == JOptionPane.YES_OPTION) {
            this.userController.logIn(user.getUsername(), user.getPassword());
            this.userController.save();

            SwingMenuBarBuilder builder =
                    new SwingMenuBarBuilder(this.controllers, user);
            if (userController.isAdmin(user)) {
                builder = builder.adminAccountMenu().recipeMenu().adminMessageMenu();
            } else {
                builder = builder.regularAccountMenu().recipeMenu().regularMessageMenu();
            }
            JMenuBar menuBar = builder.searchMenu().exitMenu().build();

            appFrame.setJMenuBar(menuBar);
            appFrame.getContentPane().removeAll();
            appFrame.setContentPane(
                    new IdlePanel(new ActionHandler[0],
                            user.getUsername(),
                            this.userController.getLoginRecords(user, 1).get(0).toString()));
            appFrame.setVisible(true);
        }
    }

    private void handleFailure(ActionEvent event) {
        JOptionPane.showMessageDialog(
                SwingUtilities.windowForComponent((Component) event.getSource()),
                new LoginText().getSignUpFailure(),
                new CommonText().getError(), JOptionPane.ERROR_MESSAGE);
    }
}
