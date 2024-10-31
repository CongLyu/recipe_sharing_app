package main.java.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageText {

    public String getInboxTitle(int num) {
        return String.format("Your inbox contains %d message(s).", num);
    }

    public String getMessage() {
        return "Message";
    }

    public String getMessageTemplate() {
        return "Subject: %s\nContent:\n%s";
    }

    public String getInbox() {
        return "Inbox";
    }

    public String getNotifyAll() {
        return "Notify All";
    }

    public String getSend() {
        return "Send";
    }

    public String getSystem() {
        return "System";
    }

    public String getEditFavoriteRecipeSubject() {
        return "one favorite recipe is updated";
    }

    public String getInboxMessageTitleTemplate() {
        return "Message #%d\n(timestamp: %s)\nFrom: %s";
    }

    public String getNotifyAllTitle() {
        return "You may write a short message as a notification for all users in the system.";
    }

    public String getSubjectLabel() {
        return "Subject: ";
    }

    public String getContentLabel() {
        return "Content: ";
    }

    public String getConfirmNotifyAllQuestion() {
        return "Are you sure that you want to send this notification to all users?";
    }

    public String getSendSuccess() {
        return "Your message is sent successfully.";
    }

    private String getEditFavoriteRecipeTemplate() {
        return "The recipe '%s' in you favorite list has been updated.";
    }

    public String getEditFavoriteRecipeNameMessage(String oldName, String newName) {
        String editFavoriteRecipeNameMessage =
                getEditFavoriteRecipeTemplate() + "\nIts name is updated to be %s.";
        return String.format(editFavoriteRecipeNameMessage, oldName, newName);
    }

    public String getEditFavoriteRecipeIngredientMessage(
            String name, Map<String, Integer> oldIngredientMap, Map<String, Integer> newIngredientMap) {
        String oldIngredients = getIngredientStr(oldIngredientMap);
        String newIngredients = getIngredientStr(newIngredientMap);
        String editFavoriteRecipeIngredientMessage =
                getEditFavoriteRecipeTemplate()
                        + "\nIts original ingredients are as follows:\n%s"
                        + "\nNow, its ingredients are updated to be the following:\n%s";
        return String.format(
                editFavoriteRecipeIngredientMessage,
                name,
                oldIngredients,
                newIngredients);
    }

    private String getIngredientStr(Map<String, Integer> ingredientMap) {
        String ingredients;
        if (ingredientMap == null || ingredientMap.isEmpty()) {
            ingredients = new CommonText().getNone();
        } else {
            List<String> ingredientStrList = new ArrayList<>();
            for (String ingredient: ingredientMap.keySet()) {
                ingredientStrList.add(String.format(
                        new RecipeText().getIngredientTemplate(),
                        ingredient,
                        ingredientMap.get(ingredient)));
            }
            ingredients = String.join("\n", ingredientStrList);
        }
        return ingredients;
    }

    public String getEditFavoriteRecipeStepMessage(
            String name, List<String> oldStepList, List<String> newStepList) {
        String oldSteps = getStepStr(oldStepList);
        String newSteps = getStepStr(newStepList);
        String editFavoriteRecipeStepMessage = getEditFavoriteRecipeTemplate()
                + "\nIts original steps are as follows:\n%s"
                + "\nNow, its steps are updated to be the following:\n%s";
        return String.format(
                editFavoriteRecipeStepMessage,
                name,
                oldSteps,
                newSteps);
    }

    private String getStepStr(List<String> stepList) {
        String steps;
        if (stepList == null || stepList.isEmpty()) {
            steps = new CommonText().getNone();
        } else {
            List<String> stepStrList = new ArrayList<>();
            int count = 1;
            for (String step : stepList) {
                stepStrList.add(String.format(new RecipeText().getStepTemplate(), count, step));
                count++;
            }
            steps = String.join("\n", stepStrList);
        }
        return steps;
    }
}
