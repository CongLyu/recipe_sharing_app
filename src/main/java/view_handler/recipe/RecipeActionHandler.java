package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.controller.RecipeController;
import main.java.controller.UserController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.text.RecipeText;
import main.java.view_handler.ActionHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class RecipeActionHandler extends ActionHandler {

    protected final UserController userController;

    protected final RecipeController recipeController;

    protected final Recipe recipe;

    protected final List<ActionHandler> actionHandlerList;

    public RecipeActionHandler(AppController[] controllers, User user, Recipe recipe) {
        super(controllers, user);
        this.userController = (UserController) controllers[0];
        this.recipeController = (RecipeController) controllers[1];
        this.recipe = recipe;
        this.actionHandlerList = new ArrayList<>();
    }

    protected List<String> getActions(Recipe recipe) {
        RecipeText recipeText = new RecipeText();
        List<String> optionList = new ArrayList<>();
        if (this.recipeController.canEdit(recipe, this.currUser)) {
            optionList.add(recipeText.getEdit());
            actionHandlerList.add(new EditRecipeHandler(this.controllers, this.currUser, recipe));
        }
        if (this.recipeController.alreadyFavorite(recipe, this.currUser)) {
            optionList.add(recipeText.getCancelFavorite());
            actionHandlerList.add(new CancelFavoriteHandler(this.controllers, this.currUser, recipe));
        } else {
            optionList.add(recipeText.getFavorite());
            actionHandlerList.add(new FavoriteHandler(this.controllers, this.currUser, recipe));
        }
        if (this.recipeController.canRate(recipe, this.currUser)) {
            optionList.add(recipeText.getRate());
            actionHandlerList.add(new RateHandler(this.controllers, this.currUser, recipe));
        }
        return optionList;
    }

    protected ActionHandler[] getActionHandlerArray() {
        int size = actionHandlerList.size();
        ActionHandler[] actionHandlers = new ActionHandler[size];
        for (int i = 0; i < size; i++) {
            actionHandlers[i] = actionHandlerList.get(i);
        }
        return actionHandlers;
    }
}
