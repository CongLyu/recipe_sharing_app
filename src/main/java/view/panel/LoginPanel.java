package main.java.view.panel;

import main.java.text.AccountText;
import main.java.text.CommonText;
import main.java.text.LoginText;
import main.java.utility.TextChecker;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * panel for login.
 */
public class LoginPanel extends SwingPanel {

    private final int minUsernameLength = 5;

    private final int maxUsernameLength = 15;

    private final int minPasswordLength = 8;

    private final int maxPasswordLength = 20;

    private final JTextField usernameField, passwordField;

    private final JTextArea usernameErrorText, passwordErrorText;

    /**
     * @param actionHandlers list of handlers
     * @param registerAdmin whether we need to register a new admin user.
     */
    public LoginPanel(ActionHandler[] actionHandlers, boolean registerAdmin) {
        super(actionHandlers);

        LoginText loginText = new LoginText();
        JTextArea headerText;
        if (registerAdmin) {
            headerText = new JTextArea(new AccountText().getCreateAdmin());
        } else {
            headerText = new JTextArea(loginText.getWelcome());
        }
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        usernameErrorText = new JTextArea(loginText.getInvalidUsername());
        passwordErrorText = new JTextArea(loginText.getInvalidPassword());

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText(loginText.getUsernameLabel());
        usernameField = new JTextField(22);
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText(loginText.getPasswordLabel());
        passwordField = new JPasswordField(22);
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel inputPanel = new JPanel();
        BoxLayout inputLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
        inputPanel.setLayout(inputLayout);
        inputPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        inputPanel.add(usernamePanel);
        inputPanel.add(usernameErrorText);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        inputPanel.add(passwordPanel);
        inputPanel.add(passwordErrorText);

        JTextArea usernameInstruction = new JTextArea(
                loginText.getUserNameInstruction(minUsernameLength, maxUsernameLength));

        JTextArea passwordInstruction = new JTextArea(
                loginText.getPasswordInstruction(minPasswordLength, maxPasswordLength));

        JPanel instructionPanel = new JPanel();
        BoxLayout instructionLayout = new BoxLayout(instructionPanel, BoxLayout.Y_AXIS);
        instructionPanel.setLayout(instructionLayout);
        instructionPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        instructionPanel.add(usernameInstruction);
        instructionPanel.add(passwordInstruction);

        JPanel infoPanel = new JPanel();
        infoPanel.add(inputPanel);
        infoPanel.add(instructionPanel);

        JButton signInButton = new JButton(loginText.getSignIn());
        JButton signUpButton = new JButton(loginText.getSignUp());
        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        if (!registerAdmin) {
            buttonPanel.add(signInButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        }
        buttonPanel.add(signUpButton);

        JPanel loginPanel = new JPanel();
        BoxLayout loginLayout = new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS);
        loginPanel.setLayout(loginLayout);
        loginPanel.setBorder(new EmptyBorder(new Insets(200, 10, 300, 10)));
        loginPanel.add(headerPanel);
        loginPanel.add(infoPanel);
        loginPanel.add(buttonPanel);

        add(loginPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        signInButton.addActionListener(event -> checkAndHandle(actionHandlers[0], event));
        signUpButton.addActionListener(event -> checkAndHandle(actionHandlers[1], event));
        setErrorTextKeyListener(usernameField, usernameErrorText);
        setErrorTextKeyListener(passwordField, passwordErrorText);

        setHeader(headerText);
        setErrorText(usernameErrorText);
        setErrorText(passwordErrorText);
        setDisabled(usernameInstruction);
        setDisabled(passwordInstruction);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    private void checkAndHandle(ActionHandler actionHandler, ActionEvent event) {
        boolean isValidUsername = isValidUsername();
        boolean isValidPassword = isValidPassword();
        if (isValidUsername && isValidPassword) {
            actionHandler.actionPerformed(event);
        } else {
            if (!isValidUsername) {
                usernameErrorText.setVisible(true);
            }
            if (!isValidPassword) {
                passwordErrorText.setVisible(true);
            }
            CommonText commonText = new CommonText();
            JOptionPane.showMessageDialog(
                    SwingUtilities.getRoot(this),
                    commonText.getInvalidInput(),
                    commonText.getError(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidUsername() {
        String strIn = usernameField.getText();
        TextChecker textChecker = new TextChecker();
        return (textChecker.matchLength(strIn, minUsernameLength, maxUsernameLength)
                && textChecker.containOnlyAlphaNumeric(strIn));
    }

    private boolean isValidPassword() {
        String strIn = passwordField.getText();
        TextChecker textChecker = new TextChecker();
        return (textChecker.matchLength(strIn, minPasswordLength, maxPasswordLength)
                && textChecker.containBothCases(strIn)
                && textChecker.containDigit(strIn));
    }
}
