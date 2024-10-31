package main.java.view.menu;

import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * super class for all the menus.
 */
public class SwingMenu extends JMenu {

    protected ActionHandler[] actionHandlers;

    /**
     * Instantiate a new menu with title and corresponding actionHandlers.
     * @param title the function of the menu
     * @param actionHandlers the handlers that will cope with the input.
     */
    public SwingMenu(String title, ActionHandler[] actionHandlers) {
        super(title);
        this.actionHandlers = actionHandlers.clone();
    }
}
