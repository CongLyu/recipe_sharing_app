package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.controller.RecipeController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.RecipeText;
import main.java.view.panel.SwingPanel;
import main.java.view.panel.RecipeEditPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class EditRecipeHandler extends ActionHandler {

    private final RecipeController recipeController;

    protected final MessageController messageController;

    private Recipe recipe;

    private final String oldRecipe;

    public EditRecipeHandler(AppController[] controllers, User user, Recipe recipe) {
        super(controllers, user);
        this.recipeController = (RecipeController) controllers[1];
        this.messageController = (MessageController) controllers[2];
        this.recipe = recipe;
        if (this.recipe == null) {
            this.oldRecipe = null;
        } else {
            this.oldRecipe = this.recipe.toString();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        CommonText commonText = new CommonText();

        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        Object[] options = {commonText.getYes(), commonText.getNo()};
        SwingPanel sourcePanel = (SwingPanel) appFrame.getContentPane();
        int option;
        if (sourcePanel instanceof RecipeEditPanel) {
            option = JOptionPane.showOptionDialog(appFrame,
                    new RecipeText().getConfirmSaveQuestion(),
                    commonText.getSave(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
        } else {
            option = JOptionPane.showOptionDialog(appFrame,
                    String.format(
                            new RecipeText().getConfirmEditQuestionTemplate(),
                            this.recipe.getName()),
                    commonText.getEdit(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
        }
        if (option == JOptionPane.YES_OPTION) {
            if (sourcePanel instanceof RecipeEditPanel) {
                String recipeName = ((RecipeEditPanel) sourcePanel).getCurrentName();
                Map<String, Integer> ingredients = ((RecipeEditPanel) sourcePanel).getCurrentIngredients();
                List<String> steps = ((RecipeEditPanel) sourcePanel).getCurrentSteps();

                if (needCreate()) {
                    this.recipe = this.recipeController.createRecipe(recipeName, this.currUser);
                    this.recipeController.updateIngredient(this.recipe, ingredients);
                    this.recipeController.updateStep(this.recipe, steps);
                } else {
                    String oldName = this.recipe.getName();
                    if (!recipeName.equals(oldName)) {
                        this.recipeController.rename(this.recipe, recipeName);
                        this.messageController.sendEditFavoriteRecipeNameMessage(this.recipe, oldName);
                    }

                    Map<String, Integer> oldIngredients = this.recipe.getIngredientMap();
                    if (!ingredients.equals(oldIngredients)) {
                        this.recipeController.updateIngredient(this.recipe, ingredients);
                        this.messageController.sendEditFavoriteRecipeIngredientMessage(this.recipe, oldIngredients);
                    }

                    List<String> oldSteps = this.recipe.getStepList();
                    if (!steps.equals(oldSteps)) {
                        this.recipeController.updateStep(this.recipe, steps);
                        this.messageController.sendEditFavoriteRecipeStepMessage(this.recipe, oldSteps);
                    }
                }

                for (AppController controller: this.controllers) {
                    controller.save();
                }
            }
            RecipeEditPanel recipeEditPanel = new RecipeEditPanel(
                    new ActionHandler[]{
                            new EditRecipeHandler(this.controllers, this.currUser, this.recipe),
                            new CreateRecipeHandler(this.controllers, this.currUser)},
                    this.recipe);
            appFrame.getContentPane().removeAll();
            appFrame.setContentPane(recipeEditPanel);
            appFrame.setVisible(true);
        }
    }

    private boolean needCreate() {
        return this.oldRecipe == null;
    }
}
