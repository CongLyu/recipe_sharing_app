package main.java.view.panel;

import main.java.text.AccountText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * panel for login history
 */
public class LoginHistoryPanel extends SwingPanel {

    /**
     * @param actionHandlers list of handlers.
     * @param username username of the current user.
     * @param loginTimeList list of login times.
     */
    public LoginHistoryPanel(
            ActionHandler[] actionHandlers, String username, List<Date> loginTimeList) {
        super(actionHandlers);

        AccountText accountText = new AccountText();

        JTextArea headerText = new JTextArea(accountText.getLoginHistory());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JTextArea titleText = new JTextArea(
                accountText.getAllHistoryTitle(username, loginTimeList.size()));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        String[] column = accountText.getLoginHistoryTableColNames().clone();
        Object[][] data = new Object[loginTimeList.size()][];
        for (int i = 0; i < loginTimeList.size(); i++) {
            Date time = loginTimeList.get(i);
            data[i] = new Object[]{
                    new SimpleDateFormat("yyyy", Locale.ENGLISH).format(time),
                    new SimpleDateFormat("MMM", Locale.ENGLISH).format(time),
                    new SimpleDateFormat("d (EEE)", Locale.ENGLISH).format(time),
                    new SimpleDateFormat("HH:mm:ss.SSS", Locale.ENGLISH).format(time),
                    new SimpleDateFormat("Z", Locale.ENGLISH).format(time)};
        }
        JTable historyTable = new JTable(data, column);
        historyTable.setEnabled(false);
        historyTable.getTableHeader().setReorderingAllowed(false);
        historyTable.getTableHeader().setResizingAllowed(false);
        JScrollPane tablePane = new JScrollPane(historyTable);

        JPanel historyPanel = new JPanel();
        BoxLayout layout = new BoxLayout(historyPanel, BoxLayout.Y_AXIS);
        historyPanel.setLayout(layout);
        historyPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        historyPanel.add(headerPanel);
        historyPanel.add(titlePanel);
        historyPanel.add(tablePane);

        add(historyPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(headerText);
        setDisabled(titleText);
    }
}
