package test.usecase;

import main.java.model.Recipe;
import main.java.usecase.RecipeManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class RecipeManagerTest {

    private String name1, creatorID1, name2, creatorID2, name3, creatorID3;

    private String ingredient1, ingredient2, ingredient3;

    private Integer calories1, calories2, calories3;

    @Before
    public void setUp(){
        name1 = "Recipe AAA123";
        creatorID1 = UUID.randomUUID().toString();
        name2 = "cook a perfect cod with only sea salt";
        creatorID2 = UUID.randomUUID().toString();
        name3 = "Beef Wellington - cook at home as a real chef";
        creatorID3 = UUID.randomUUID().toString();
        ingredient1 = "butter";
        calories1 = 120;
        ingredient2 = "sea salt";
        calories2 = 31;
        ingredient3 = "oil";
        calories3 = 77;
    }

    @Test
    public void testConstructorRecipeList() {
        RecipeManager recipeManager = new RecipeManager();
        List<Recipe> expected = new ArrayList<>();
        Assert.assertEquals(recipeManager.getRecipeList(), expected);
    }

    @Test
    public void testAddRecipeForSingleRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        List<Recipe> expected = new ArrayList<>();
        Recipe recipe = recipeManager.addRecipe(name1, creatorID1);
        expected.add(recipe);
        Assert.assertEquals(recipeManager.getRecipeList(), expected);
    }

    @Test
    public void testAddRecipeForMultipleRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        List<Recipe> expected = new ArrayList<>();
        Recipe recipe1 = recipeManager.addRecipe(name1, creatorID1);
        expected.add(recipe1);
        Recipe recipe2 = recipeManager.addRecipe(name2, creatorID2);
        expected.add(recipe2);
        Recipe recipe3 = recipeManager.addRecipe(name3, creatorID3);
        expected.add(recipe3);
        Assert.assertEquals(recipeManager.getRecipeList(), expected);
    }

    @Test
    public void testAddSingleIngredientForTheOnlyExistingRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        Recipe recipe = recipeManager.addRecipe(name1, creatorID1);
        Map<String, Integer> expected = new HashMap<>();
        recipeManager.addIngredient(recipe, ingredient1, calories1);
        expected.put(ingredient1, calories1);
        Assert.assertEquals(recipe.getIngredientMap(), expected);
    }

    @Test
    public void testAddMultipleIngredientForTheOnlyExistingRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        Recipe recipe = recipeManager.addRecipe(name1, creatorID1);
        Map<String, Integer> expected = new HashMap<>();
        recipeManager.addIngredient(recipe, ingredient1, calories1);
        expected.put(ingredient1, calories1);
        recipeManager.addIngredient(recipe, ingredient2, calories2);
        expected.put(ingredient2, calories2);
        recipeManager.addIngredient(recipe, ingredient3, calories3);
        expected.put(ingredient3, calories3);
        Assert.assertEquals(recipe.getIngredientMap(), expected);
    }

    @Test
    public void testAddSingleIngredientAmongSeveralExistingRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        recipeManager.addRecipe(name1, creatorID1);
        Recipe recipe = recipeManager.addRecipe(name2, creatorID2);
        recipeManager.addRecipe(name3, creatorID3);
        Map<String, Integer> expected = new HashMap<>();
        recipeManager.addIngredient(recipe, ingredient1, calories1);
        expected.put(ingredient1, calories1);
        Assert.assertEquals(recipe.getIngredientMap(), expected);
    }

    @Test
    public void testAddMultipleIngredientAmongSeveralExistingRecipe() {
        RecipeManager recipeManager = new RecipeManager();
        recipeManager.addRecipe(name1, creatorID1);
        Recipe recipe = recipeManager.addRecipe(name2, creatorID2);
        recipeManager.addRecipe(name3, creatorID3);
        Map<String, Integer> expected = new HashMap<>();
        recipeManager.addIngredient(recipe, ingredient1, calories1);
        expected.put(ingredient1, calories1);
        recipeManager.addIngredient(recipe, ingredient2, calories2);
        expected.put(ingredient2, calories2);
        recipeManager.addIngredient(recipe, ingredient3, calories3);
        expected.put(ingredient3, calories3);
        Assert.assertEquals(recipe.getIngredientMap(), expected);
    }
}
