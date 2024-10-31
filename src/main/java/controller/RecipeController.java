package main.java.controller;

import main.java.model.Recipe;
import main.java.model.user.User;
import main.java.usecase.RecipeManager;

import java.io.File;
import java.util.*;

/**
 * A controller for Recipe
 */
public class RecipeController extends AppController {

    private final String[] RECIPE_CONTROLLER_PATH_NAMES =
            {"src", "main", "resource", "recipe_controller.ser"};

    private final String RECIPE_CONTROLLER_PATH =
            String.join(File.separator, RECIPE_CONTROLLER_PATH_NAMES);

    private final RecipeManager recipeManager;

    /**
     * read the existed controller in from the default path, if it does not exist, create a new one and save it.
     */
    public RecipeController() {
        RecipeController loadedRecipeController =
                (RecipeController) load(RECIPE_CONTROLLER_PATH);
        if (loadedRecipeController == null) {
            this.recipeManager = new RecipeManager();
            save();
        } else {
            this.recipeManager = loadedRecipeController.getRecipeManager();
        }
    }

    /**
     * save the current controller
     */
    @Override
    public void save() {
        this.write(RECIPE_CONTROLLER_PATH);
    }

    public RecipeManager getRecipeManager() {
        return this.recipeManager;
    }

    /**
     * createRecipe takes recipeName and a User to create a new recipe, and add the new recipe to list of recipe in
     * recipeManager, and return the new recipe
     */
    public Recipe createRecipe(String recipeName, User user) {
        return this.recipeManager.addRecipe(recipeName, user.getUid());
    }

    /**
     * rename takes one Recipe and a new name, and call renameRecipe in recipeManager to rename the recipe.
     */
    public void rename(Recipe recipe, String newName) {
        this.recipeManager.renameRecipe(recipe, newName);
    }

    /**
     * updateIngredient takes one recipe, and a Map of ingredients and their calories, and replaces the old Map of
     * ingredients with the new one of that recipe.
     */
    public void updateIngredient(Recipe recipe, Map<String, Integer> ingredientMap) {
        this.recipeManager.clearIngredient(recipe);
        for (String ingredient: ingredientMap.keySet()) {
            Integer calories = ingredientMap.get(ingredient);
            this.recipeManager.addIngredient(recipe, ingredient, calories);
        }
    }

    /**
     * updateStep takes one recipe, and a list of steps, and replaces the old list of steps with the new one of that
     * recipe.
     */
    public void updateStep(Recipe recipe, List<String> stepList) {
        this.recipeManager.clearStep(recipe);
        for (String step: stepList) {
            this.recipeManager.addStep(recipe, step);
        }
    }

    /**
     * check if the recipe is created by user, if so, return true
     */
    public boolean canEdit(Recipe recipe, User user) {
        return this.recipeManager.isCreator(recipe, user.getUid());
    }

    /**
     * return the list of all recipes
     */
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>(this.recipeManager.getRecipeList());
        recipeList.sort(Comparator.comparing(Recipe::getName));
        return recipeList;
    }

    /**
     * return the list of recipes that are created by user
     */
    public List<Recipe> getCreatedRecipe(User user) {
        List<Recipe> recipeList = new ArrayList<>();
        String uid = user.getUid();
        for (Recipe recipe: getAllRecipe()) {
            if (recipe.getCreatorId().equals(uid)) {
                recipeList.add(recipe);
            }
        }
        return recipeList;
    }

    /**
     * return true if the recipe is already been added to the list of favorite recipes of the user.
     */
    public boolean alreadyFavorite(Recipe recipe, User user) {
        return this.recipeManager.isCollector(recipe, user.getUid());
    }

    /**
     * add recipe to the list of favorite of the user
     */
    public void addToFavorites(Recipe recipe, User user) {
        String uid = user.getUid();
        this.recipeManager.addFavorite(recipe, uid);
    }

    /**
     * remove the recipe from the list of favorite of the user
     */
    public void removeFromFavorites(Recipe recipe, User user) {
        String uid = user.getUid();
        this.recipeManager.removeFavorite(recipe, uid);
    }

    /**
     * return the list of favorite recipes of the user
     */
    public List<Recipe> getFavoritesList(User user) {
        List<Recipe> recipeList = new ArrayList<>();
        String uid = user.getUid();
        for (Recipe recipe: getAllRecipe()) {
            if (recipe.getCollectorList().contains(uid)) {
                recipeList.add(recipe);
            }
        }
        return recipeList;
    }

    /**
     * return true if the user is able to rate the recipe, false if the user has already rated the recipe
     */
    public boolean canRate(Recipe recipe, User user) {
        return this.recipeManager.notRated(recipe, user.getUid());
    }

    /**
     * record the user's rate to the recipe
     */
    public void rate(Recipe recipe, User user, Integer rate) {
        this.recipeManager.rateRecipe(recipe, user.getUid(), rate);
    }
}
