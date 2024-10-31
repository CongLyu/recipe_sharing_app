package main.java.view.menu;

import main.java.text.RecipeText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;

/**
 * recipe menu that let user view the existed recipes or create new recipe.
 */
public class RecipeMenu extends SwingMenu {

    public RecipeMenu(ActionHandler[] actionHandlers) {
        super(new RecipeText().getRecipe(), actionHandlers);

        RecipeText recipeText = new RecipeText();

        JMenuItem viewMenuItem = new JMenuItem(recipeText.getView());
        viewMenuItem.setActionCommand(recipeText.getView());
        viewMenuItem.addActionListener(actionHandlers[0]);

        JMenuItem createNewMenuItem = new JMenuItem(recipeText.getCreateNew());
        createNewMenuItem.setActionCommand(recipeText.getCreateNew());
        createNewMenuItem.addActionListener(actionHandlers[1]);

        this.add(viewMenuItem);
        this.add(createNewMenuItem);
    }
}
