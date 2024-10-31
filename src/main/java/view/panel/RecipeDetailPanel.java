package main.java.view.panel;

import main.java.model.Recipe;
import main.java.text.RecipeText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * panel for display one specific panel.
 */
public class RecipeDetailPanel extends SwingPanel {

    private final List<Recipe> recipeList;

    private final List<String> creatorNameList;

    private final List<List<String>> actionList;

    private final List<JTextArea> textAreaList;

    private int handlerIdx;

    /**
     * @param actionHandlers list of handlers.
     * @param recipeList list of recipes.
     * @param creatorNameList list of creator's name.
     * @param actionList list of actions user can implement.
     */
    public RecipeDetailPanel(
            ActionHandler[] actionHandlers, List<Recipe> recipeList,
            List<String> creatorNameList, List<List<String>> actionList) {
        super(actionHandlers);

        this.recipeList = new ArrayList<>(recipeList);
        this.creatorNameList = new ArrayList<>(creatorNameList);
        this.actionList = new ArrayList<>(actionList);

        JTextArea headerText = new JTextArea(new RecipeText().getRecipe());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JPanel detailPanel = new JPanel();
        BoxLayout detailPanelLayout = new BoxLayout(detailPanel, BoxLayout.PAGE_AXIS);
        detailPanel.setLayout(detailPanelLayout);
        detailPanel.setBorder(new EmptyBorder(new Insets(30, 10, 50, 10)));
        detailPanel.add(headerPanel);

        textAreaList = new ArrayList<>();
        handlerIdx = 0;
        for (int i = 0; i < this.recipeList.size(); i++) {
            JPanel rowPanel = generateRow(i);
            detailPanel.add(rowPanel);
        }

        JScrollPane detailScrollPane = new JScrollPane();
        detailScrollPane.setViewportView(detailPanel);
        if (recipeList.size() > 3) {
            detailScrollPane.setPreferredSize(new Dimension(1150, 800));
        }

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        mainPanel.add(detailScrollPane);

        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        setHeader(headerText);
        for (JTextArea textArea: textAreaList) {
            setDisabled(textArea);
        }
    }

    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }

    private JPanel generateRow(int idx) {
        Recipe recipe = this.recipeList.get(idx);
        JTextArea titleText = new JTextArea(String.format(
                new RecipeText().getDetailTitleTemplate(),
                idx + 1,
                this.creatorNameList.get(idx),
                recipe.getCollectorList().size()));
        textAreaList.add(titleText);
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleText);

        JTextArea recipeText = new JTextArea(recipe.toString());
        textAreaList.add(recipeText);
        JPanel recipeTextPanel = new JPanel();
        recipeTextPanel.add(recipeText);

        List<String> actions = this.actionList.get(idx);
        JPanel buttonPanel = generateButtonPanel(actions);

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        panel.add(titlePanel);
        panel.add(recipeTextPanel);
        panel.add(buttonPanel);
        return panel;
    }

    private JPanel generateButtonPanel(List<String> actions) {
        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        RecipeText recipeText = new RecipeText();
        if (actions.contains(recipeText.getEdit())) {
            JButton editButton = new JButton(recipeText.getEdit());
            editButton.addActionListener(actionHandlers[handlerIdx]);
            handlerIdx ++;
            buttonPanel.add(editButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        }
        if (actions.contains(recipeText.getFavorite())) {
            JButton favoriteButton = new JButton(recipeText.getFavorite());
            favoriteButton.addActionListener(actionHandlers[handlerIdx]);
            handlerIdx ++;
            buttonPanel.add(favoriteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        }
        if (actions.contains(recipeText.getCancelFavorite())) {
            JButton cancelFavoriteButton = new JButton(recipeText.getCancelFavorite());
            cancelFavoriteButton.addActionListener(actionHandlers[handlerIdx]);
            handlerIdx ++;
            buttonPanel.add(cancelFavoriteButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        }
        if (actions.contains(recipeText.getRate())) {
            JButton rateButton = new JButton(recipeText.getRate());
            rateButton.addActionListener(actionHandlers[handlerIdx]);
            handlerIdx ++;
            buttonPanel.add(rateButton);
        }
        return buttonPanel;
    }
}
