package main.java.view.panel;

import main.java.model.Recipe;
import main.java.text.CommonText;
import main.java.text.RecipeText;
import main.java.utility.TextChecker;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * panel for user to edit the recipe.
 */
public class RecipeEditPanel extends SwingPanel {

    private final JTextField nameField;

    private final List<List<JTextField>> ingredientFieldList;

    private final List<JTextField> stepFieldList;

    private JPanel ingredientContentPanel, stepContentPanel;

    private final JScrollPane ingredientContentScrollPane, stepContentScrollPane;

    private final JTextArea nameErrorText, ingredientErrorText, stepErrorText;

    private final int minStepLength = 3;

    private final int minIngredientNameLength = 3;

    private final int minRecipeNameLength = 5;

    /**
     * @param actionHandlers list of handlers.
     * @param recipe recipe the user needs to edit.
     */
    public RecipeEditPanel(ActionHandler[] actionHandlers, Recipe recipe) {
        super(actionHandlers);

        RecipeText recipeText = new RecipeText();

        JTextArea headerText = new JTextArea(recipeText.getRecipe());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JLabel nameLabel = new JLabel();
        nameLabel.setText(recipeText.getRecipeNameLabel());
        nameField = new JTextField(38);
        if (recipe != null) {
            nameField.setText(recipe.getName());
        }
        JPanel nameInputPanel = new JPanel();
        nameInputPanel.add(nameLabel);
        nameInputPanel.add(nameField);

        JTextArea nameInstruction = new JTextArea();
        nameInstruction.setText(String.format(
                recipeText.getRecipeNameInstructionTemplate(), minRecipeNameLength));
        JPanel nameInstructionPanel = new JPanel();
        nameInstructionPanel.add(nameInstruction);

        nameErrorText = new JTextArea(recipeText.getInvalidName());

        JPanel namePanel = new JPanel();
        BoxLayout namePanelLayout = new BoxLayout(namePanel, BoxLayout.PAGE_AXIS);
        namePanel.setLayout(namePanelLayout);
        namePanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        namePanel.add(nameInputPanel);
        namePanel.add(nameErrorText);
        namePanel.add(nameInstructionPanel);

        JLabel ingredientTitleText = new JLabel(recipeText.getIngredientTitle());

        JButton addIngredientButton = new JButton(new CommonText().getAdd());

        JPanel ingredientTitlePanel = new JPanel();
        BoxLayout ingredientTitleLayout = new BoxLayout(ingredientTitlePanel, BoxLayout.X_AXIS);
        ingredientTitlePanel.setLayout(ingredientTitleLayout);
        ingredientTitlePanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        ingredientTitlePanel.add(ingredientTitleText);
        ingredientTitlePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        ingredientTitlePanel.add(addIngredientButton);

        JTextArea ingredientInstruction = new JTextArea();
        ingredientInstruction.setText(String.format(
                recipeText.getIngredientInstructionTemplate(), minIngredientNameLength));
        JPanel ingredientInstructionPanel = new JPanel();
        ingredientInstructionPanel.add(ingredientInstruction);

        ingredientFieldList = new ArrayList<>();
        if (recipe != null) {
            Map<String, Integer> ingredientMap = recipe.getIngredientMap();
            if (ingredientMap != null && !ingredientMap.isEmpty()) {
                fillInIngredient(ingredientMap);
            }
        }

        renewIngredientContentPanel();
        ingredientContentScrollPane = new JScrollPane();
        ingredientContentScrollPane.setViewportView(ingredientContentPanel);

        ingredientErrorText = new JTextArea(recipeText.getInvalidIngredients());

        JPanel ingredientPanel = new JPanel();
        BoxLayout ingredientLayout = new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS);
        ingredientPanel.setLayout(ingredientLayout);
        ingredientPanel.setBorder(new EmptyBorder(new Insets(1, 5, 1, 5)));
        ingredientPanel.add(ingredientTitlePanel);
        ingredientPanel.add(ingredientInstructionPanel);
        ingredientPanel.add(ingredientContentScrollPane);
        ingredientPanel.add(ingredientErrorText);

        JLabel stepTitleText = new JLabel(recipeText.getStepTitle());

        JButton addStepButton = new JButton(new CommonText().getAdd());

        JPanel stepTitlePanel = new JPanel();
        BoxLayout stepTitleLayout = new BoxLayout(stepTitlePanel, BoxLayout.X_AXIS);
        stepTitlePanel.setLayout(stepTitleLayout);
        stepTitlePanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        stepTitlePanel.add(stepTitleText);
        stepTitlePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        stepTitlePanel.add(addStepButton);

        JTextArea stepInstruction = new JTextArea();
        stepInstruction.setText(String.format(recipeText.getStepInstructionTemplate(), minStepLength));
        JPanel stepInstructionPanel = new JPanel();
        stepInstructionPanel.add(stepInstruction);

        stepFieldList = new ArrayList<>();
        if (recipe != null) {
            List<String> stepList = recipe.getStepList();
            if (stepList != null && !stepList.isEmpty()) {
                fillInStep(stepList);
            }
        }

        renewStepContentPanel();
        stepContentScrollPane = new JScrollPane();
        stepContentScrollPane.setViewportView(stepContentPanel);

        stepErrorText = new JTextArea(recipeText.getInvalidSteps());

        JPanel stepPanel = new JPanel();
        BoxLayout stepLayout = new BoxLayout(stepPanel, BoxLayout.Y_AXIS);
        stepPanel.setLayout(stepLayout);
        stepPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        stepPanel.add(stepTitlePanel);
        stepPanel.add(stepInstructionPanel);
        stepPanel.add(stepContentScrollPane);
        stepPanel.add(stepErrorText);

        JButton saveButton = new JButton(recipeText.getSave());
        JButton clearButton = new JButton(recipeText.getClear());
        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(clearButton);

        JPanel editPanel = new JPanel();
        BoxLayout mainLayout = new BoxLayout(editPanel, BoxLayout.PAGE_AXIS);
        editPanel.setLayout(mainLayout);
        editPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        editPanel.add(headerPanel);
        editPanel.add(namePanel);
        editPanel.add(ingredientPanel);
        editPanel.add(stepPanel);
        editPanel.add(buttonPanel);

        add(editPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        saveButton.addActionListener(event -> checkAndHandle(actionHandlers[0], event));
        clearButton.addActionListener(actionHandlers[1]);
        addIngredientButton.addActionListener(this::addBlankIngredient);
        addStepButton.addActionListener(this::addBlankStep);
        renewListeners();

        setHeader(headerText);
        setDisabled(nameInstruction);
        setErrorText(nameErrorText);
        setDisabled(ingredientInstruction);
        setErrorText(ingredientErrorText);
        setDisabled(stepInstruction);
        setErrorText(stepErrorText);
    }

    /**
     * @return the current user's username
     */
    public String getCurrentName() {
        return nameField.getText();
    }

    public Map<String, Integer> getCurrentIngredients() {
        Map<String, Integer> ingredientMap = new HashMap<>();
        for (List<JTextField> ingredientInfo: ingredientFieldList) {
            ingredientMap.put(
                    ingredientInfo.get(0).getText(),
                    Integer.valueOf(ingredientInfo.get(1).getText()));
        }
        return ingredientMap;
    }

    /**
     * @return the current steps of the recipe.
     */
    public List<String> getCurrentSteps() {
        List<String> stepList = new ArrayList<>();
        for (JTextField stepField: stepFieldList) {
            stepList.add(stepField.getText());
        }
        return stepList;
    }

    private void renewListeners() {
        setErrorTextKeyListener(nameField, nameErrorText);
        for (List<JTextField> ingredientInfo: ingredientFieldList) {
            setErrorTextKeyListener(ingredientInfo.get(0), ingredientErrorText);
            setErrorTextKeyListener(ingredientInfo.get(1), ingredientErrorText);
        }
        for (JTextField stepField: stepFieldList) {
            setErrorTextKeyListener(stepField, stepErrorText);
        }
    }

    private void fillInIngredient(Map<String, Integer> ingredientMap) {
        for (String ingredient: ingredientMap.keySet()) {
            JTextField ingredientField = new JTextField(15);
            ingredientField.setText(ingredient);

            JTextField caloriesField = new JTextField(10);
            caloriesField.setText(String.valueOf(ingredientMap.get(ingredient)));

            List<JTextField> ingredientInfo = new ArrayList<>();
            ingredientInfo.add(ingredientField);
            ingredientInfo.add(caloriesField);
            ingredientFieldList.add(ingredientInfo);
        }
    }

    private void addBlankIngredient(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeEditPanel recipeEditPanel = (RecipeEditPanel) appFrame.getContentPane();
        recipeEditPanel.addBlankIngredientTextField();
        recipeEditPanel.renewIngredientContentPanel();
        recipeEditPanel.renewListeners();
        appFrame.setContentPane(recipeEditPanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void addBlankIngredientTextField() {
        JTextField ingredientField = new JTextField(15);
        JTextField caloriesField = new JTextField(10);
        List<JTextField> ingredientInfo = new ArrayList<>();
        ingredientInfo.add(ingredientField);
        ingredientInfo.add(caloriesField);
        ingredientFieldList.add(ingredientInfo);
        resizeIngredientPane();
    }

    private void renewIngredientContentPanel() {
        if (ingredientContentPanel != null) {
            ingredientContentPanel.removeAll();
        } else {
            ingredientContentPanel = new JPanel();
        }
        BoxLayout ingredientContentLayout = new BoxLayout(ingredientContentPanel, BoxLayout.Y_AXIS);
        ingredientContentPanel.setLayout(ingredientContentLayout);
        ingredientContentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        int i = 1;
        for (List<JTextField> ingredientInfo: ingredientFieldList) {
            RecipeText recipeText = new RecipeText();

            JLabel ingredientLabel = new JLabel();
            ingredientLabel.setText(String.format(recipeText.getIngredientLabel(), i));

            JLabel caloriesLabel = new JLabel();
            caloriesLabel.setText(recipeText.getCaloriesLabel());

            JButton deleteIngredientButton = new JButton(new CommonText().getDelete());

            JPanel rowPanel = new JPanel();
            BoxLayout rowLayout = new BoxLayout(rowPanel, BoxLayout.X_AXIS);
            rowPanel.setLayout(rowLayout);
            rowPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            rowPanel.add(ingredientLabel);
            rowPanel.add(ingredientInfo.get(0));
            rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            rowPanel.add(caloriesLabel);
            rowPanel.add(ingredientInfo.get(1));
            rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            rowPanel.add(deleteIngredientButton);

            int idx = i - 1;
            deleteIngredientButton.addActionListener(event -> deleteIngredientRow(event, idx));

            ingredientContentPanel.add(rowPanel);

            i ++;
        }
    }

    private void deleteIngredientRow(ActionEvent event, int idx) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeEditPanel recipeEditPanel = (RecipeEditPanel) appFrame.getContentPane();
        recipeEditPanel.deleteIngredientField(idx);
        recipeEditPanel.renewIngredientContentPanel();
        appFrame.setContentPane(recipeEditPanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void deleteIngredientField(int idx) {
        ingredientFieldList.remove(idx);
        resizeIngredientPane();
        hideIngredientError();
    }

    private void hideIngredientError() {
        if (ingredientErrorText.isVisible()) {
            ingredientErrorText.setVisible(false);
        }
    }

    private void resizeIngredientPane() {
        if (ingredientFieldList.size() > 3) {
            ingredientContentScrollPane.setPreferredSize(new Dimension(570, 180));
        } else {
            ingredientContentScrollPane.setPreferredSize(null);
        }
    }

    private void fillInStep(List<String> stepList) {
        for (String step: stepList) {
            JTextField stepField = new JTextField(35);
            stepField.setText(step);
            stepFieldList.add(stepField);
        }
    }

    private void addBlankStep(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeEditPanel recipeEditPanel = (RecipeEditPanel) appFrame.getContentPane();
        recipeEditPanel.addBlankStepTextField();
        recipeEditPanel.renewStepContentPanel();
        appFrame.setContentPane(recipeEditPanel);
        recipeEditPanel.renewListeners();
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void addBlankStepTextField() {
        JTextField stepField = new JTextField(35);
        stepFieldList.add(stepField);
        resizeStepPane();
    }

    private void renewStepContentPanel() {
        if (stepContentPanel != null) {
            stepContentPanel.removeAll();
        } else {
            stepContentPanel = new JPanel();
        }
        BoxLayout stepContentLayout = new BoxLayout(stepContentPanel, BoxLayout.Y_AXIS);
        stepContentPanel.setLayout(stepContentLayout);
        stepContentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        int i = 1;
        for (JTextField stepField: stepFieldList) {
            JLabel stepLabel = new JLabel();
            stepLabel.setText(String.format(new RecipeText().getStepLabel(), i));

            JButton deleteStepButton = new JButton(new CommonText().getDelete());

            JPanel rowPanel = new JPanel();
            BoxLayout rowLayout = new BoxLayout(rowPanel, BoxLayout.X_AXIS);
            rowPanel.setLayout(rowLayout);
            rowPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            rowPanel.add(stepLabel);
            rowPanel.add(stepField);
            rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            rowPanel.add(deleteStepButton);

            int idx = i - 1;
            deleteStepButton.addActionListener(event -> deleteStepRow(event, idx));

            stepContentPanel.add(rowPanel);

            i ++;
        }
    }

    private void deleteStepRow(ActionEvent event, int idx) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        RecipeEditPanel recipeEditPanel = (RecipeEditPanel) appFrame.getContentPane();
        recipeEditPanel.deleteStepField(idx);
        recipeEditPanel.renewStepContentPanel();
        appFrame.setContentPane(recipeEditPanel);
        appFrame.revalidate();
        appFrame.repaint();
        appFrame.setVisible(true);
    }

    private void deleteStepField(int idx) {
        stepFieldList.remove(idx);
        resizeStepPane();
        hideStepError();
    }

    private void hideStepError() {
        if (stepErrorText.isVisible()) {
            stepErrorText.setVisible(false);
        }
    }

    private void resizeStepPane() {
        if (stepFieldList.size() > 3) {
            stepContentScrollPane.setPreferredSize(new Dimension(570, 180));
        } else {
            stepContentScrollPane.setPreferredSize(null);
        }
    }

    private void checkAndHandle(ActionHandler actionHandler, ActionEvent event) {
        boolean isValidName = isValidName();
        boolean isValidIngredient = isValidIngredient();
        boolean isValidStep = isValidStep();
        if (isValidName && isValidIngredient && isValidStep) {
            actionHandler.actionPerformed(event);
        } else {
            if (!isValidName) {
                nameErrorText.setVisible(true);
            }
            if (!isValidIngredient) {
                ingredientErrorText.setVisible(true);
            }
            if (!isValidStep) {
                stepErrorText.setVisible(true);
            }
            CommonText commonText = new CommonText();
            JOptionPane.showMessageDialog(
                    SwingUtilities.getRoot(this),
                    commonText.getInvalidInput(),
                    commonText.getError(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidName() {
        String strIn = nameField.getText();
        TextChecker textChecker = new TextChecker();
        return (textChecker.matchLength(strIn, minRecipeNameLength)
                && textChecker.containOnlyAlphaNumericSpace(strIn));
    }

    private boolean isValidIngredient() {
        for (List<JTextField> ingredientInfo: ingredientFieldList) {
            TextChecker textChecker = new TextChecker();
            String name = ingredientInfo.get(0).getText();
            String calories = ingredientInfo.get(1).getText();
            if (!(textChecker.matchLength(name, minIngredientNameLength)
                    && textChecker.containOnlyAlphaNumericSpace(name)
                    && textChecker.containOnlyDigit(calories))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidStep() {
        for (JTextField stepField: stepFieldList) {
            TextChecker textChecker = new TextChecker();
            String step = stepField.getText();
            if (!textChecker.matchLength(step, minStepLength)) {
                return false;
            }
        }
        return true;
    }
}
