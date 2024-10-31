package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.RecipeText;
import main.java.view.panel.RecipeDetailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class FavoriteHandler extends RecipeActionHandler {

    public FavoriteHandler(AppController[] controllers, User user, Recipe recipe) {
        super(controllers, user, recipe);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.recipeController.addToFavorites(this.recipe, this.currUser);
        ((MessageController) controllers[2]).subscribe(this.recipe, this.currUser);

        for (AppController controller: this.controllers) {
            controller.save();
        }

        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        JOptionPane.showMessageDialog(
                appFrame,
                new RecipeText().getFavoriteSuccess(),
                new CommonText().getNotification(), JOptionPane.INFORMATION_MESSAGE);

        List<String> creatorNameList = new ArrayList<>();
        List<List<String>> actionList = new ArrayList<>();
        List<Recipe> recipeList = ((RecipeDetailPanel) appFrame.getContentPane()).getRecipeList();
        for (Recipe r: recipeList) {
            creatorNameList.add(this.userController.getUsernameById(r.getCreatorId()));
            actionList.add(getActions(r));
        }
        RecipeDetailPanel recipeDetailPanel = new RecipeDetailPanel(
                getActionHandlerArray(),
                recipeList,
                creatorNameList,
                actionList);
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(recipeDetailPanel);
        appFrame.setVisible(true);
    }
}
