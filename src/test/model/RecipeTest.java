package test.model;

import main.java.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotEquals;

public class RecipeTest {

    private String name1, creatorID1, name2, creatorID2;

    @Before
    public void setUp(){
        name1 = "Recipe AAA123";
        creatorID1 = UUID.randomUUID().toString();
        name2 = "cook a perfect cod with only sea salt";
        creatorID2 = UUID.randomUUID().toString();
    }

    @Test
    public void testConstructorName() {
        Recipe recipe = new Recipe(name1, creatorID1);
        Assert.assertEquals(recipe.getName(), name1);
    }

    @Test
    public void testConstructorCreatorId() {
        Recipe recipe = new Recipe(name1, creatorID1);
        Assert.assertEquals(recipe.getCreatorId(), creatorID1);
    }

    @Test
    public void testConstructorIngredientMap() {
        Recipe recipe = new Recipe(name1, creatorID1);
        Map<String, Integer> expected = new HashMap<>();
        Assert.assertEquals(recipe.getIngredientMap(), expected);
    }

    @Test
    public void testConstructorCalories() {
        Recipe recipe = new Recipe(name1, creatorID1);
        Integer expected = 0;
        Assert.assertEquals(recipe.getCalories(), expected);
    }

    @Test
    public void testConstructorStepList() {
        Recipe recipe = new Recipe(name1, creatorID1);
        List<String> expected = new ArrayList<>();
        Assert.assertEquals(recipe.getStepList(), expected);
    }

    @Test
    public void testConstructorCollectorList() {
        Recipe recipe = new Recipe(name1, creatorID1);
        List<String> expected = new ArrayList<>();
        Assert.assertEquals(recipe.getCollectorList(), expected);
    }

    @Test
    public void testEqual() {
        Recipe recipe = new Recipe(name1, creatorID1);
        Assert.assertEquals(recipe, recipe);
    }

    @Test
    public void testNotEqualSameInfo() {
        Recipe recipe1 = new Recipe(name1, creatorID1);
        Recipe recipe2 = new Recipe(name1, creatorID1);
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    public void testEqualDiffInfo() {
        Recipe recipe1 = new Recipe(name1, creatorID1);
        Recipe recipe2 = new Recipe(name2, creatorID2);
        assertNotEquals(recipe1, recipe2);
    }

    @Test
    public void testToString() {
        Recipe recipe = new Recipe(name1, creatorID1);
        String expected = String.join("\n",
                new String[]{"Recipe AAA123",
                        "   Average Rate: None",
                        "   Calories: 0",
                        "   Ingredients:",
                        "      None",
                        "   Steps:",
                        "      None"});
        Assert.assertEquals(recipe.toString(), expected);
    }
}
