package main.java.view.panel;

import main.java.text.CommonText;
import main.java.text.MessageText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * panel for admin user to notify all users.
 */
public class NotifyAllPanel extends SwingPanel {

    private final JTextField subjectField, contentField;

    private final MessageText messageText = new MessageText();

    /**
     * @param actionHandlers list of handlers.
     */
    public NotifyAllPanel(ActionHandler[] actionHandlers) {
        super(actionHandlers);

        JTextArea headerText = new JTextArea(messageText.getNotifyAll());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JTextArea titleText = new JTextArea(messageText.getNotifyAllTitle());
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        JLabel subjectLabel = new JLabel();
        subjectLabel.setText(messageText.getSubjectLabel());
        subjectField = new JTextField(75);
        JPanel subjectPanel = new JPanel();
        subjectPanel.add(subjectLabel);
        subjectPanel.add(subjectField);

        JLabel contentLabel = new JLabel();
        contentLabel.setText(messageText.getContentLabel());
        contentField = new JTextField(75);
        JPanel contentPanel = new JPanel();
        contentPanel.add(contentLabel);
        contentPanel.add(contentField);

        JButton sendButton = new JButton(messageText.getSend());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        mainPanel.add(headerPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(subjectPanel);
        mainPanel.add(contentPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        sendButton.addActionListener(event -> checkAndHandle(actionHandlers[0], event));

        setHeader(headerText);
        setDisabled(titleText);
    }

    /**
     * @return the string in the subject field.
     */
    public String getSubjectFieldText() {
        return subjectField.getText();
    }

    /**
     * @return the string in the content field.
     */
    public String getContentFieldText() {
        return contentField.getText();
    }

    private void checkAndHandle(ActionHandler actionHandler, ActionEvent event) {
        CommonText commonText = new CommonText();
        if (getSubjectFieldText().isBlank() || getContentFieldText().isBlank()) {
            JOptionPane.showMessageDialog(
                    SwingUtilities.getRoot(this),
                    commonText.getInvalidInput(),
                    commonText.getError(), JOptionPane.ERROR_MESSAGE);
        } else {
            Object[] options = {commonText.getYes(), commonText.getNo()};
            int option = JOptionPane.showOptionDialog(
                    SwingUtilities.getRoot(this),
                    messageText.getConfirmNotifyAllQuestion(),
                    messageText.getSend(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (option == JOptionPane.YES_OPTION) {
                actionHandler.actionPerformed(event);
            }
        }
    }
}
