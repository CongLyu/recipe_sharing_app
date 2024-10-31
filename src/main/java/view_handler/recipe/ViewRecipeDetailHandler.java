package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.view.panel.RecipeBrowsePanel;
import main.java.view.panel.RecipeDetailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewRecipeDetailHandler extends RecipeActionHandler {

    public ViewRecipeDetailHandler(AppController[] controllers, User user, Recipe recipe) {
        super(controllers, user, recipe);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        List<Recipe> recipeList = ((RecipeBrowsePanel) appFrame.getContentPane()).getSelectedRecipes();
        List<String> creatorNameList = new ArrayList<>();
        List<List<String>> actionList = new ArrayList<>();
        for (Recipe recipe: recipeList) {
            creatorNameList.add(this.userController.getUsernameById(recipe.getCreatorId()));
            actionList.add(getActions(recipe));
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
