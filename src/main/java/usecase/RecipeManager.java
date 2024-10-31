package main.java.usecase;

import main.java.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeManager extends EntityManager {

    private final List<Recipe> recipeList;

    /**
     * Constructs a new RecipeManager with an empty recipeList.
     */
    public RecipeManager() {
        this.recipeList = new ArrayList<>();
    }


    /**
     * Returns the list of recipes of this RecipeManager.
     * @return the list of recipes of this RecipeManager
     */
    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }


    /**
     * Indicates whether the recipe has existed.
     * @param recipe recipe needs to be checked existence
     * @return true if and only if the recipe is already existed in the recipeList.
     */
    private boolean isExistingRecipe(Recipe recipe) {
        return this.recipeList.contains(recipe);
    }


    /**
     * Adds new recipe in the recipeList of this recipeManager
     * @param recipeName name of the recipe
     * @param creatorId if of creator
     * @return the new recipe
     */
    public Recipe addRecipe(String recipeName, String creatorId) {
        Recipe recipe = new Recipe(recipeName, creatorId);
        this.recipeList.add(recipe);
        return recipe;
    }


    /**
     * Removes the Map of ingredients of this recipe.
     * @param recipe
     */
    public void clearIngredient(Recipe recipe) {
        if (isExistingRecipe(recipe)) {
            recipe.setIngredientMap(new HashMap<>());
        }
    }

    /**
     * Adds ingredient and its calories to the recipe in recipeList.
     * @param recipe
     * @param ingredient
     * @param calories
     */
    public void addIngredient(Recipe recipe, String ingredient, Integer calories) {
        if (isExistingRecipe(recipe)) {
            recipe.addIngredient(ingredient, calories);
        }
    }

    /**
     * Removes the list of steps of this recipe.
     * @param recipe
     */
    public void clearStep(Recipe recipe) {
        if (isExistingRecipe(recipe)) {
            recipe.setStepList(new ArrayList<>());
        }
    }

    /**
     * Adds step to the recipe in recipeList.
     * @param recipe
     * @param step
     */
    public void addStep(Recipe recipe, String step) {
        if (isExistingRecipe(recipe)) {
            recipe.addStep(step);
        }
    }

    /**
     * Renames the recipe with newName in recipeList.
     * @param recipe
     * @param newName
     */
    public void renameRecipe(Recipe recipe, String newName) {
        if (isExistingRecipe(recipe)) {
            recipe.setName(newName);
        }
    }

    /**
     * Returns true if and only if the recipe is created by user with uid.
     * @param recipe
     * @param uid
     * @return true if and only if the recipe is created by user with uid
     */
    public boolean isCreator(Recipe recipe, String uid) {
        if (isExistingRecipe(recipe)) {
            return recipe.getCreatorId().equals(uid);
        }
        return false;
    }

    /**
     * Returns true if the recipe is collected by user with uid.
     * @param recipe
     * @param uid
     * @return
     */
    public boolean isCollector(Recipe recipe, String uid) {
        if (isExistingRecipe(recipe)) {
            return recipe.hasCollected(uid);
        }
        return false;
    }


    /**
     * Adds uid to the list of collectors of recipe if user with uid hasn't collected this recipe yet.
     * @param recipe
     * @param uid
     */
    public void addFavorite(Recipe recipe, String uid) {
        if (isExistingRecipe(recipe) && !isCollector(recipe, uid)) {
            recipe.addCollector(uid);
        }
    }

    /**
     * Removes the uid from the list of collectors of recipe
     * @param recipe
     * @param uid
     */
    public void removeFavorite(Recipe recipe, String uid) {
        if (isExistingRecipe(recipe) && isCollector(recipe, uid)) {
            recipe.removeCollector(uid);
        }
    }


    /**
     * Returns true if and only if the recipe hasn't received any rate yet.
     * @param recipe
     * @param uid
     * @return true if and only if the recipe hasn't received any rate yet
     */
    public boolean notRated(Recipe recipe, String uid) {
        if (isExistingRecipe(recipe)) {
            return !recipe.hasRated(uid);
        }
        return true;
    }


    /**
     * Adds rate and the uid of the rater to recipe.
     * @param recipe
     * @param uid
     * @param rate
     */
    public void rateRecipe(Recipe recipe, String uid, Integer rate) {
        if (isExistingRecipe(recipe) && notRated(recipe, uid)) {
            recipe.addRate(uid, rate);
        }
    }
}
