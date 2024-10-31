package main.java.view.menu;

import main.java.text.SearchText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * menu that let user search for content in recipe, user or message.
 */
public class SearchMenu extends SwingMenu {

    public SearchMenu(ActionHandler[] actionHandlers) {
        super(new SearchText().getSearchStr(), actionHandlers);

        String performSearchStr = new SearchText().getPerformSearchStr();
        JMenuItem searchMenuItem = new JMenuItem(performSearchStr);
        searchMenuItem.setActionCommand(performSearchStr);
        searchMenuItem.addActionListener(actionHandlers[0]);

        this.add(searchMenuItem);
    }
}
