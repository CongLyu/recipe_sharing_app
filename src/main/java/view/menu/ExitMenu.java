package main.java.view.menu;

import main.java.text.CommonText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * menu that let user exit.
 */
public class ExitMenu extends SwingMenu {

    /**
     * @param actionHandlers handlers that let user log out or exit app.
     */
    public ExitMenu(ActionHandler[] actionHandlers) {
        super(new CommonText().getExit(), actionHandlers);

        CommonText commonText = new CommonText();

        JMenuItem logOutMenuItem = new JMenuItem(commonText.getLogOut());
        logOutMenuItem.setActionCommand(commonText.getLogOut());
        logOutMenuItem.addActionListener(actionHandlers[0]);

        JMenuItem exitAppMenuItem = new JMenuItem(commonText.getTerminate());
        exitAppMenuItem.setActionCommand(commonText.getTerminate());
        exitAppMenuItem.addActionListener(actionHandlers[1]);

        this.add(logOutMenuItem);
        this.add(exitAppMenuItem);
    }
}
