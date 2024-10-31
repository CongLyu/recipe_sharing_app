package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.controller.RecipeController;
import main.java.controller.UserController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.text.RecipeText;
import main.java.view.panel.RecipeBrowsePanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowseRecipeHandler extends ActionHandler {

    private final UserController userController;

    private final RecipeController recipeController;

    public BrowseRecipeHandler(AppController[] controllers, User user) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
        this.recipeController = (RecipeController) controllers[1];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        RecipeText recipeText = new RecipeText();
        Map<String, List<Recipe>> recipeBrowseMap = new HashMap<>();
        List<Recipe> allRecipeList = recipeController.getAllRecipe();
        recipeBrowseMap.put(recipeText.getViewAll(), allRecipeList);
        List<Recipe> favoriteList = recipeController.getFavoritesList(this.currUser);
        recipeBrowseMap.put(recipeText.getViewFavorites(), favoriteList);
        List<Recipe> createdList = recipeController.getCreatedRecipe(this.currUser);
        recipeBrowseMap.put(recipeText.getViewCreated(), createdList);

        Map<String, List<String>> creatorMap = new HashMap<>();
        creatorMap.put(recipeText.getViewAll(), getCreatorList(allRecipeList));
        creatorMap.put(recipeText.getViewFavorites(), getCreatorList(favoriteList));
        creatorMap.put(recipeText.getViewCreated(), getCreatorList(createdList));

        RecipeBrowsePanel recipeBrowsePanel = new RecipeBrowsePanel(
                new ActionHandler[] {new ViewRecipeDetailHandler(this.controllers, this.currUser, null)},
                recipeBrowseMap,
                creatorMap
        );

        JFrame appFrame = (JFrame) SwingUtilities.getRoot(
                ((JPopupMenu) ((JMenuItem) event.getSource()).getParent()).getInvoker());
        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(recipeBrowsePanel);
        appFrame.setVisible(true);
    }

    private List<String> getCreatorList(List<Recipe> recipeList) {
        List<String> creatorList = new ArrayList<>();
        for (Recipe recipe: recipeList) {
            creatorList.add(this.userController.getUsernameById(recipe.getCreatorId()));
        }
        return creatorList;
    }
}
