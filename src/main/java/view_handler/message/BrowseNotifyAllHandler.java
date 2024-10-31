package main.java.view_handler.message;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.view.panel.NotifyAllPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BrowseNotifyAllHandler extends ActionHandler {

    public BrowseNotifyAllHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        NotifyAllPanel notifyAllPanel = new NotifyAllPanel(
                new ActionHandler[]{new SendToAllHandler(this.controllers, this.currUser)});
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(notifyAllPanel);
        appFrame.setVisible(true);
    }
}
