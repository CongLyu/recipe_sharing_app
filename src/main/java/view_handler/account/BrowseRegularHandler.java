package main.java.view_handler.account;

import main.java.controller.AppController;
import main.java.controller.UserController;
import main.java.model.user.User;
import main.java.view.panel.ManageRegularPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BrowseRegularHandler extends ActionHandler {

    public BrowseRegularHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        ManageRegularPanel manageRegularPanel = new ManageRegularPanel(
                new ActionHandler[]{new ManageRegularHandler(this.controllers, this.currUser)},
                ((UserController) this.controllers[0]).getAllRegular());
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(manageRegularPanel);
        appFrame.setVisible(true);
    }
}
