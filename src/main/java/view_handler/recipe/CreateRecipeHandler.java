package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.view.panel.RecipeEditPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateRecipeHandler extends ActionHandler {

    public CreateRecipeHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame;
        try {
            appFrame = (JFrame) SwingUtilities.getRoot(
                    ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        } catch (Exception e) {
            appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        }
        RecipeEditPanel recipeEditPanel = new RecipeEditPanel(
                new ActionHandler[]{
                        new EditRecipeHandler(this.controllers, this.currUser, null),
                        new CreateRecipeHandler(this.controllers, this.currUser)},
                null);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(recipeEditPanel);
        appFrame.setVisible(true);
    }
}
