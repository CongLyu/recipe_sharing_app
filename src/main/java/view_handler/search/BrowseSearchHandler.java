package main.java.view_handler.search;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.view.panel.SearchPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class BrowseSearchHandler extends ActionHandler {

    /**
     * @param controllers
     * @param user
     */
    public BrowseSearchHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    /**
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        SearchPanel searchPanel = new SearchPanel(
                new ActionHandler[]{new SearchHandler(this.controllers, this.currUser)},
                null, null);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(searchPanel);
        appFrame.setVisible(true);
    }
}
