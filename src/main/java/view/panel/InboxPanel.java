package main.java.view.panel;

import main.java.model.message.Message;
import main.java.text.MessageText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * the panel that shows all the messages inbox.
 */
public class InboxPanel extends SwingPanel {

    private final List<Message> messageList;

    private final List<String> senderNameList;

    private final JPanel messagePanel;

    private final List<JTextArea> textAreaList;

    private final MessageText messageText = new MessageText();

    /**
     * @param actionHandlers list of handlers the panel requires.
     * @param messageList all messages stored in the inbox.
     * @param senderNameList list of the uid of the senders.
     */
    public InboxPanel(ActionHandler[] actionHandlers,
                      List<Message> messageList, List<String> senderNameList) {
        super(actionHandlers);

        this.messageList = new ArrayList<>(messageList);
        this.senderNameList = new ArrayList<>(senderNameList);

        JTextArea headerText = new JTextArea(messageText.getInbox());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JTextArea titleText = new JTextArea(messageText.getInboxTitle(this.messageList.size()));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        messagePanel = new JPanel();
        BoxLayout messageLayout = new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS);
        messagePanel.setLayout(messageLayout);
        messagePanel.setBorder(new EmptyBorder(new Insets(30, 10, 50, 10)));

        textAreaList = new ArrayList<>();
        addRows();

        JScrollPane messageScrollPane = new JScrollPane();
        messageScrollPane.setViewportView(messagePanel);
        if (this.messageList.isEmpty()) {
            messageScrollPane.setVisible(false);
        } else {
            messageScrollPane.setPreferredSize(new Dimension(850, 600));
        }

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        mainPanel.add(headerPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(messageScrollPane);

        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(headerText);
        setDisabled(titleText);
        for (JTextArea textArea: textAreaList) {
            setDisabled(textArea);
        }
    }

    private void addRows() {
        for (int i = 0; i < this.messageList.size(); i ++) {
            Message message = this.messageList.get(i);
            JTextArea titleText = new JTextArea(String.format(
                    messageText.getInboxMessageTitleTemplate(),
                    i + 1,
                    new SimpleDateFormat("EEE MMM yyyy HH:mm:ss.SSS Z", Locale.ENGLISH)
                            .format(message.getCreatedTime()),
                    this.senderNameList.get(i)));
            textAreaList.add(titleText);
            JTextArea messageTextArea = new JTextArea(String.format(
                    messageText.getMessageTemplate(),
                    message.getSubject(),
                    message.getContent()));
            textAreaList.add(messageTextArea);
            JPanel panel = new JPanel();
            BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
            panel.setLayout(layout);
            panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            panel.add(titleText);
            panel.add(messageTextArea);
            messagePanel.add(panel);
        }
    }
}
