package main.java.view_handler.recipe;

import main.java.controller.AppController;
import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.text.CommonText;
import main.java.text.RecipeText;
import main.java.utility.TextChecker;
import main.java.view.panel.RecipeDetailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RateHandler extends RecipeActionHandler {

    public RateHandler(AppController[] controllers, User user, Recipe recipe) {
        super(controllers, user, recipe);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());

        RecipeText recipeText = new RecipeText();

        String strIn = JOptionPane.showInputDialog(
                appFrame, recipeText.getEnterRateInstruction());
        TextChecker textChecker = new TextChecker();
        List<Integer> validRates = getValidRates();
        if (textChecker.containOnlyDigit(strIn)
                && validRates.contains(Integer.valueOf(strIn))) {
            this.recipeController.rate(this.recipe, this.currUser, Integer.valueOf(strIn));
            this.recipeController.save();
            JOptionPane.showMessageDialog(
                    appFrame,
                    recipeText.getRateSuccess(),
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

        } else {
            JOptionPane.showMessageDialog(
                    appFrame,
                    recipeText.getInvalidRate(),
                    new CommonText().getError(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Integer> getValidRates() {
        List<Integer> validRates = new ArrayList<>();
        validRates.add(0);
        validRates.add(1);
        validRates.add(2);
        validRates.add(3);
        validRates.add(4);
        validRates.add(5);
        return validRates;
    }
}
