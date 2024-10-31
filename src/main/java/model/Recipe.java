package main.java.model;

import main.java.text.RecipeText;
import main.java.utility.IntIdGenerator;
import main.java.text.CommonText;

import java.io.Serializable;
import java.util.*;

/**
 * recipe entity.
 */
public class Recipe implements Serializable {

    private final static IntIdGenerator rIdGenerator = new IntIdGenerator();

    private final Integer recipeID;

    private String name;

    private final String creatorId;

    private Map<String, Integer> ingredientMap;

    private Integer calories;

    private List<String> stepList;

    private final List<String> collectorList;

    private final Map<String, Integer> rateMap;

    /**
     * Constructs a new recipe object with name and creatorId, set other attribute to be empty.
     * @param name the name of the recipe
     * @param creatorId the uid of the creator.
     */
    public Recipe(String name, String creatorId) {
        this.recipeID = assignRId();
        this.name = name;
        this.creatorId = creatorId;
        this.ingredientMap = new HashMap<>();
        this.calories = 0;
        this.stepList = new ArrayList<>();
        this.collectorList = new ArrayList<>();
        this.rateMap = new HashMap<>();
    }

    /**
     * Indicates whether this recipe is equal to another recipe obj.
     * @param obj another recipe we want to compare with
     * @return true if both are recipes and share the same recipeID.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Recipe
                && this.recipeID.equals(((Recipe) obj).getRecipeID());
    }

    /**
     * Gives the string representation of the recipe.
     * @return the string representation of the recipe.
     */
    @Override
    public String toString() {
        CommonText commonText = new CommonText();
        String shortPrefix = commonText.getSpaceSeparator().repeat(3);
        String longPrefix = commonText.getSpaceSeparator().repeat(6);
        String recipeStr = String.format("%s\n", this.name)
                + shortPrefix + String.format("Average Rate: %s\n", getAvgRateStr())
                + shortPrefix + String.format("Calories: %d\n", this.calories)
                + shortPrefix + "Ingredients:\n";
        String ingredientStr;
        if (this.ingredientMap.isEmpty()) {
            ingredientStr = longPrefix + commonText.getNone();
        } else {
            ingredientStr = longPrefix
                    + String.join("\n" + longPrefix, getIngredientStrLst());
        }
        String stepStr;
        if (this.stepList.isEmpty()) {
            stepStr = longPrefix + commonText.getNone();
        } else {
            stepStr = longPrefix + String.join("\n" + longPrefix, getStepStrLst());
        }
        return recipeStr.concat(ingredientStr).concat("\n" + shortPrefix + "Steps:\n").concat(stepStr);
    }

    /**
     * Gets the average rating of this recipe.
     * @return the average rating of this recipe.
     */
    public String getAvgRateStr() {
        Set<String> raters = this.rateMap.keySet();
        if (raters.isEmpty()) {
            return new CommonText().getNone();
        }
        Integer sum = 0;
        for (String uid: raters) {
            sum += this.rateMap.get(uid);
        }
        return String.format("%.2f/5.00", (float) (sum / raters.size()));
    }

    /**
     * Gets the calories of the recipe.
     * @return the calories of this recipe.
     */
    public Integer getCalories() {
        return this.calories;
    }

    private List<String> getIngredientStrLst() {
        List<String> ingredientStrList = new ArrayList<>();
        int count = 1;
        for (String ingredient: this.ingredientMap.keySet()) {
            Integer calories = this.ingredientMap.get(ingredient);
            ingredientStrList.add(
                    String.format("%d. %s (%d calories)", count, ingredient, calories));
            count ++;
        }
        return ingredientStrList;
    }

    /**
     * return the list of steps of this recipe.
     */
    private List<String> getStepStrLst() {
        List<String> stepStrList = new ArrayList<>();
        int count = 1;
        for (String step: this.stepList) {
            stepStrList.add(String.format(new RecipeText().getStepTemplate(), count, step));
            count ++;
        }
        return stepStrList;
    }

    /**
     * Returns the RecipeId.
     * @return the RecipeId.
     */
    public Integer getRecipeID() {
        return this.recipeID;
    }

    private static Integer assignRId() {
        return rIdGenerator.generate();
    }

    /**
     * Gets the name of the recipe.
     * @return the name of the recipe
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name for the recipe.
     * @param name new name for the recipe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the creatorId of the recipe.
     * @return the id of the creator of the recipe
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * Gets the ingredientMap.
     * @return the ingredientMap
     */
    public Map<String, Integer> getIngredientMap() {
        return this.ingredientMap;
    }

    /**
     * Sets this ingredientMap as ingredientMap.
     * @param ingredientMap the new IngredientMap
     */
    public void setIngredientMap(Map<String, Integer> ingredientMap) {
        this.ingredientMap = new HashMap<>(ingredientMap);
    }

    private boolean isUsedIngredient(String ingredient) {
        return this.ingredientMap.containsKey(ingredient);
    }

    /**
     * Adds a new ingredient and its calories to ingredientMap.
     * @param ingredient new ingredient
     * @param calories the calories of that ingredient
     */
    public void addIngredient(String ingredient, Integer calories) {
        if (!this.isUsedIngredient(ingredient)) {
            this.ingredientMap.put(ingredient, calories);
            this.calories += calories;
        }
    }

    /**
     * Gets the stepList of this recipe.
     * @return the stepList
     */
    public List<String> getStepList() {
        return this.stepList;
    }

    /**
     * Sets the stepList of this recipe as stepList.
     * @param stepList new stepList
     */
    public void setStepList(List<String> stepList) {
        this.stepList = new ArrayList<>(stepList);
    }

    /**
     * Appends new step to the stepList.
     * @param step new step
     */
    public void addStep(String step) {
        this.stepList.add(step);
    }

    /**
     * Gets the collectorList of the recipe.
     * @return the collectorList
     */
    public List<String> getCollectorList() {
        return this.collectorList;
    }

    /**
     * Indicates whether a user with uid has collected this recipe.
     * @param uid user if of the collector we want to check
     * @return true if the collectorList contains uid, false otherwise
     */
    public boolean hasCollected(String uid) {
        return this.collectorList.contains(uid);
    }

    /**
     * Appends the user with uid to the collectorList.
     * @param uid the new collector that we want to add to the collectorList
     */
    public void addCollector(String uid) {
        this.collectorList.add(uid);
    }

    /**
     * Removes existed collector with uid from the collectorList.
     * @param uid the existed collector that we want to remove from the collectorList
     */
    public void removeCollector(String uid) {
        this.collectorList.remove(uid);
    }

    /**
     * Indicates whether the user with uid has rated this recipe.
     * @param uid user that we want to check whether they have rated before
     * @return true if user with uid has rated this recipe, false otherwise
     */
    public boolean hasRated(String uid) {
        return this.rateMap.containsKey(uid);
    }

    /**
     * Adds a new rate given by user with uid to this recipe.
     * @param uid the new user that will rate the recipe
     * @param rate the score they want to rate.
     * add a new pair of uid and rate to the rateMap of this recipe.
     */
    public void addRate(String uid, Integer rate) {
        this.rateMap.put(uid, rate);
    }
}
