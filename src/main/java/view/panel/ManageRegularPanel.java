package main.java.view.panel;

import main.java.model.user.RegularUser;
import main.java.text.AccountText;
import main.java.text.CommonText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * panel for admin user to manage regular user
 */
public class ManageRegularPanel extends SwingPanel {

    private final List<RegularUser> regularUserList;

    private final JTable regularUserTable;

    private final AccountText accountText = new AccountText();

    /**
     * @param actionHandlers list of handlers
     * @param userList list of regular users
     */
    public ManageRegularPanel(ActionHandler[] actionHandlers, List<RegularUser> userList) {
        super(actionHandlers);

        regularUserList = new ArrayList<>(userList);

        JTextArea headerText = new JTextArea(accountText.getManageNonAdmin());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JTextArea titleText = new JTextArea(accountText.getAllRegularTitle());
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        regularUserTable = generateTable();
        JScrollPane regularUserTablePane = new JScrollPane(regularUserTable);
        regularUserTablePane.setPreferredSize(new Dimension(800, 450));

        JPanel buttonPanel = generateButtonPanel();

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        mainPanel.add(headerPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(regularUserTablePane);
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(headerText);
        setDisabled(titleText);
    }

    /**
     * @return return the list of selected user.
     */
    public List<RegularUser> getSelectedUsers() {
        List<RegularUser> selectedUsers = new ArrayList<>();
        int[] idxArray = regularUserTable.getSelectedRows();
        for (int idx: idxArray) {
            selectedUsers.add(regularUserList.get(idx));
        }
        return selectedUsers;
    }

    private JTable generateTable() {
        String[] column = accountText.getRegularUserTableColNames();
        Object[][] data = new Object[regularUserList.size()][];
        for (int i = 0; i < regularUserList.size(); i++) {
            RegularUser regularUser = regularUserList.get(i);
            data[i] = new Object[]{
                    regularUser.getUsername(),
                    regularUser.getLastLoginStr(),
                    regularUser.getLoginHistory().size(),
                    regularUser.isBanned()
            };
        }
        JTable table = new JTable(data, column);
//        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        return table;
    }

    private JPanel generateButtonPanel() {
        JButton banButton = new JButton(accountText.getBanSelected());
        banButton.addActionListener(this::handleButton);

        JButton deleteButton = new JButton(accountText.getDeleteSelected());
        deleteButton.addActionListener(this::handleButton);

        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        buttonPanel.add(banButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }

    private void handleButton(ActionEvent event) {
        if (getSelectedUsers().isEmpty()) {
            CommonText commonText = new CommonText();
            JOptionPane.showMessageDialog(
                    SwingUtilities.getRoot((Component) event.getSource()),
                    commonText.getInvalidSelection(),
                    commonText.getError(), JOptionPane.ERROR_MESSAGE);
        } else {
            actionHandlers[0].actionPerformed(event);
        }
    }
}
