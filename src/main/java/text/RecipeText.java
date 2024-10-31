package main.java.text;

public class RecipeText {

    public String getRecipe() {
        return "Recipe";
    }

    public String getView() {
        return "View";
    }

    public String getViewAll() {
        return "View All";
    }

    public String getViewFavorites() {
        return "View Favorites";
    }

    public String getViewCreated() {
        return "View Created";
    }

    public String getCreateNew() {
        return "Create New";
    }

    public String getIngredientTemplate() {
        return "%s (%d calories)";
    }

    public String getStepTemplate() {
        return "Step %d: %s";
    }

    public String getViewAllTitleTemplate() {
        return "All %d public recipe(s) are listed as follows.";
    }

    public String getViewFavoritesTitleTemplate() {
        return "Your favorites list contains %d recipe(s) as follows.";
    }

    public String getViewCreatedTitleTemplate() {
        return "All %d recipe(s) created by you are listed as follows.";
    }

    public String getBrowseTitle(String template, int recipeNum) {
        return String.format(template, recipeNum);
    }

    public String getEdit() {
        return "Edit";
    }

    public String getFavorite() {
        return "Favorite";
    }

    public String getFavoriteSuccess() {
        return "You have successfully added this recipe to your favorites list.";
    }

    public String getCancelFavorite() {
        return "Cancel Favorite";
    }

    public String getCancelFavoriteSuccess() {
        return "You have successfully removed this recipe from your favorites list.";
    }

    public String getRate() {
        return "Rate";
    }

    public String getInvalidRate() {
        return "Sorry. Your input cannot make a valid rate.";
    }

    public String getRateSuccess() {
        return "Great! You have successfully rated the recipe.";
    }

    public String getRecipeNameLabel() {
        return "Recipe Name: ";
    }

    public String getIngredientTitle() {
        return "Ingredients (Optional)";
    }

    public String getIngredientLabel() {
        return "Ingredient %d: ";
    }

    public String getCaloriesLabel() {
        return "Calories: ";
    }

    public String getStepTitle() {
        return "Steps (Optional)";
    }

    public String getStepLabel() {
        return "Step %d: ";
    }

    public String getRecipeNameInstructionTemplate() {
        return "The name of a recipe should be at least %d characters long" +
                "\nand each character is either alphabetic or numeric or space.";
    }

    public String getIngredientInstructionTemplate() {
        CommonText commonText = new CommonText();
        return "An ingredient of a recipe should specify both 'name' and 'calories':\n"
                + commonText.getOptionPrefix()
                + "'name' is at least %d characters long\n"
                + commonText.getOptionPrefix()
                + "each character of 'name' is either alphabetic or numeric or space\n"
                + commonText.getOptionPrefix()
                + "'calories' is of integer value";
    }

    public String getStepInstructionTemplate() {
        return "A step of a recipe should be at least %d characters long.";
    }

    public String getConfirmSaveQuestion() {
        return "Are you sure that you want to save the current recipe?";
    }

    public String getConfirmEditQuestionTemplate() {
        return "Are you sure that you want to edit the recipe of name '%s'?";
    }

    public String getSave() {
        return "Save Current Edit";
    }

    public String getClear() {
        return "Abandon Current & Create New";
    }

    public String getInvalidName() {
        return "Your input is an invalid recipe name.";
    }

    public String getInvalidSteps() {
        return "Your input cannot make valid steps.";
    }

    public String getInvalidIngredients() {
        return "Your input cannot make valid ingredients.";
    }

    public String[] getRecipeTableColNames() {
        return new String[]{"Name", "Creator", "Calories", "# Ingredients",
                "# Steps", "Avg Rate", "# Favorites"};
    }

    public String getViewDetails() {
        return "View Details Of Selected Recipe(s)";
    }

    public String getDetailTitleTemplate() {
        return "Recipe #%d\n(created by %s; added to favorites by %d users)";
    }

    public String getEnterRateInstruction() {
        return "Enter your rate for this recipe." +
                "\nA valid rate of a recipe can only be one of 0, 1, 2, 3, 4, 5.";
    }
}
