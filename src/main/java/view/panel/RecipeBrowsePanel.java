package main.java.view.panel;

import main.java.model.Recipe;
import main.java.text.CommonText;
import main.java.text.RecipeText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * panel for browsing list of recipes.
 */
public class RecipeBrowsePanel extends SwingPanel {

    private List<Recipe> allRecipeList;
    private List<Recipe> favoriteList;
    private List<Recipe> createdList;

    private List<String> allRecipeListCreators;
    private List<String> favoriteListCreators;
    private List<String> createdListCreators;

    private JTextArea viewAllTitleText, viewFavoriteTitleText, viewCreatedTitleText;

    private JRadioButton viewAllButton, viewFavoriteButton, viewCreatedButton;

    private final JTable allRecipeTable, favoriteTable, createdTable;

    private final JScrollPane allRecipeTablePane, favoriteTablePane, createdTablePane;

    private JButton viewDetailButton;

    /**
     * @param actionHandlers list of handlers
     * @param recipeBrowseMap three types of list of recipes, which are all recipes, favorite recipes of the user, and recipes created by user and their corresponding keys.
     * @param creatorMap list of creators corresponding to different types of list of recipes.
     */
    public RecipeBrowsePanel(
            ActionHandler[] actionHandlers,
            Map<String, List<Recipe>> recipeBrowseMap,
            Map<String, List<String>> creatorMap) {
        super(actionHandlers);

        formatData(recipeBrowseMap, creatorMap);

        JTextArea headerText = new JTextArea(new RecipeText().getRecipe());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JPanel radioPanel = generateRadioPanel();

        JPanel titlePanel = generateTitlePanel();

        allRecipeTable = generateTable(allRecipeList, allRecipeListCreators);
        allRecipeTablePane = new JScrollPane(allRecipeTable);
        allRecipeTablePane.setPreferredSize(new Dimension(700, 450));

        favoriteTable = generateTable(favoriteList, favoriteListCreators);
        favoriteTablePane = new JScrollPane(favoriteTable);
        favoriteTablePane.setPreferredSize(new Dimension(700, 450));

        createdTable = generateTable(createdList, createdListCreators);
        createdTablePane = new JScrollPane(createdTable);
        createdTablePane.setPreferredSize(new Dimension(700, 450));

        JPanel buttonPanel = generateButtonPanel();

        JPanel browsePanel = new JPanel();
        BoxLayout browseLayout = new BoxLayout(browsePanel, BoxLayout.PAGE_AXIS);
        browsePanel.setLayout(browseLayout);
        browsePanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        browsePanel.add(headerPanel);
        browsePanel.add(radioPanel);
        browsePanel.add(titlePanel);
        browsePanel.add(allRecipeTablePane);
        browsePanel.add(favoriteTablePane);
        browsePanel.add(createdTablePane);
        browsePanel.add(buttonPanel);

        hideAll();

        add(browsePanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(headerText);
        setDisabled(viewAllTitleText);
        setDisabled(viewFavoriteTitleText);
        setDisabled(viewCreatedTitleText);
    }

    private JRadioButton getViewAllButton() {
        return this.viewAllButton;
    }

    private JRadioButton getViewFavoriteButton() {
        return this.viewFavoriteButton;
    }

    private JRadioButton getViewCreatedButton() {
        return this.viewCreatedButton;
    }

    /**
     * @return the list of recipes based on user's choice.
     */
    public List<Recipe> getSelectedRecipes() {
        List<Recipe> selectedRecipes = new ArrayList<>();
        if (viewAllButton.isSelected()) {
            int[] idxArray = allRecipeTable.getSelectedRows();
            for (int idx: idxArray) {
                selectedRecipes.add(allRecipeList.get(idx));
            }
        } else if (viewFavoriteButton.isSelected()) {
            int[] idxArray = favoriteTable.getSelectedRows();
            for (int idx: idxArray) {
                selectedRecipes.add(favoriteList.get(idx));
            }
        } else {
            int[] idxArray = createdTable.getSelectedRows();
            for (int idx: idxArray) {
                selectedRecipes.add(createdList.get(idx));
            }
        }
        return selectedRecipes;
    }

    private void formatData(Map<String, List<Recipe>> recipeBrowseMap,
                            Map<String, List<String>> creatorMap) {
        RecipeText recipeText = new RecipeText();

        allRecipeList = recipeBrowseMap.get(recipeText.getViewAll());
        allRecipeListCreators = creatorMap.get(recipeText.getViewAll());

        favoriteList = recipeBrowseMap.get(recipeText.getViewFavorites());
        favoriteListCreators = creatorMap.get(recipeText.getViewFavorites());

        createdList = recipeBrowseMap.get(recipeText.getViewCreated());
        createdListCreators = creatorMap.get(recipeText.getViewCreated());
    }

    private JPanel generateTitlePanel() {
        RecipeText recipeText = new RecipeText();
        viewAllTitleText = new JTextArea(
                recipeText.getBrowseTitle(
                        recipeText.getViewAllTitleTemplate(), allRecipeList.size()));
        viewFavoriteTitleText = new JTextArea(
                recipeText.getBrowseTitle(
                        recipeText.getViewFavoritesTitleTemplate(), favoriteList.size()));
        viewCreatedTitleText = new JTextArea(
                recipeText.getBrowseTitle(
                        recipeText.getViewCreatedTitleTemplate(), createdList.size()));
        JPanel titlePanel = new JPanel();
        titlePanel.add(viewAllTitleText);
        titlePanel.add(viewFavoriteTitleText);
        titlePanel.add(viewCreatedTitleText);
        return titlePanel;
    }

    private JPanel generateRadioPanel() {
        RecipeText recipeText = new RecipeText();

        viewAllButton = new JRadioButton();
        viewAllButton.setText(recipeText.getViewAll());
        viewAllButton.addActionListener(this::handleViewAllButton);

        viewFavoriteButton = new JRadioButton();
        viewFavoriteButton.setText(recipeText.getViewFavorites());
        viewFavoriteButton.addActionListener(this::handleViewFavoriteButton);

        viewCreatedButton = new JRadioButton();
        viewCreatedButton.setText(recipeText.getViewCreated());
        viewCreatedButton.addActionListener(this::handleViewCreatedButton);

        JPanel radioPanel = new JPanel();
        BoxLayout radioLayout = new BoxLayout(radioPanel, BoxLayout.X_AXIS);
        radioPanel.setLayout(radioLayout);
        radioPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        radioPanel.add(viewAllButton);
        radioPanel.add(viewFavoriteButton);
        radioPanel.add(viewCreatedButton);
        return radioPanel;
    }

    private void handleViewAllButton(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeBrowsePanel recipeBrowsePanel = (RecipeBrowsePanel) appFrame.getContentPane();
        if (recipeBrowsePanel.getViewAllButton().isSelected()) {
            recipeBrowsePanel.showAllRecipe();
        } else {
            recipeBrowsePanel.hideAll();
        }
        appFrame.setContentPane(recipeBrowsePanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void handleViewFavoriteButton(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeBrowsePanel recipeBrowsePanel = (RecipeBrowsePanel) appFrame.getContentPane();
        if (recipeBrowsePanel.getViewFavoriteButton().isSelected()) {
            recipeBrowsePanel.showFavoriteRecipe();
        } else {
            recipeBrowsePanel.hideAll();
        }
        appFrame.setContentPane(recipeBrowsePanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void handleViewCreatedButton(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeBrowsePanel recipeBrowsePanel = (RecipeBrowsePanel) appFrame.getContentPane();
        if (recipeBrowsePanel.getViewCreatedButton().isSelected()) {
            recipeBrowsePanel.showCreatedRecipe();
        } else {
            recipeBrowsePanel.hideAll();
        }
        appFrame.setContentPane(recipeBrowsePanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private JTable generateTable(List<Recipe> recipeList, List<String> creators) {
        String[] column = new RecipeText().getRecipeTableColNames().clone();
        Object[][] data = new Object[recipeList.size()][];
        for (int i = 0; i < recipeList.size(); i++) {
            String creatorName = creators.get(i);
            Recipe recipe = recipeList.get(i);
            data[i] = new Object[]{
                    recipe.getName(),
                    creatorName,
                    recipe.getCalories(),
                    recipe.getIngredientMap().size(),
                    recipe.getStepList().size(),
                    recipe.getAvgRateStr(),
                    recipe.getCollectorList().size()
            };
        }
        JTable table = new JTable(data, column);
//        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        return table;
    }

    private JPanel generateButtonPanel() {
        viewDetailButton = new JButton(new RecipeText().getViewDetails());
        viewDetailButton.addActionListener(this::handleViewDetailButton);

        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        buttonPanel.add(viewDetailButton);
        return buttonPanel;
    }

    private void handleViewDetailButton(ActionEvent event) {
        if (getSelectedRecipes().isEmpty()) {
            CommonText commonText = new CommonText();
            JOptionPane.showMessageDialog(
                    SwingUtilities.getRoot((Component) event.getSource()),
                    commonText.getInvalidSelection(),
                    commonText.getError(), JOptionPane.ERROR_MESSAGE);
        } else {
            actionHandlers[0].actionPerformed(event);
        }
    }

    private void hideAll() {
        viewAllButton.setSelected(false);
        viewFavoriteButton.setSelected(false);
        viewCreatedButton.setSelected(false);
        viewAllTitleText.setVisible(false);
        viewFavoriteTitleText.setVisible(false);
        viewCreatedTitleText.setVisible(false);
        allRecipeTablePane.setVisible(false);
        favoriteTablePane.setVisible(false);
        createdTablePane.setVisible(false);
        viewDetailButton.setVisible(false);
    }

    private void showAllRecipe() {
        viewAllButton.setSelected(true);
        viewFavoriteButton.setSelected(false);
        viewCreatedButton.setSelected(false);
        viewAllTitleText.setVisible(true);
        viewFavoriteTitleText.setVisible(false);
        viewCreatedTitleText.setVisible(false);
        allRecipeTablePane.setVisible(true);
        favoriteTablePane.setVisible(false);
        createdTablePane.setVisible(false);
        viewDetailButton.setVisible(true);
    }

    private void showFavoriteRecipe() {
        viewAllButton.setSelected(false);
        viewFavoriteButton.setSelected(true);
        viewCreatedButton.setSelected(false);
        viewAllTitleText.setVisible(false);
        viewFavoriteTitleText.setVisible(true);
        viewCreatedTitleText.setVisible(false);
        allRecipeTablePane.setVisible(false);
        favoriteTablePane.setVisible(true);
        createdTablePane.setVisible(false);
        viewDetailButton.setVisible(true);
    }

    private void showCreatedRecipe() {
        viewAllButton.setSelected(false);
        viewFavoriteButton.setSelected(false);
        viewCreatedButton.setSelected(true);
        viewAllTitleText.setVisible(false);
        viewFavoriteTitleText.setVisible(false);
        viewCreatedTitleText.setVisible(true);
        allRecipeTablePane.setVisible(false);
        favoriteTablePane.setVisible(false);
        createdTablePane.setVisible(true);
        viewDetailButton.setVisible(true);
    }
}
