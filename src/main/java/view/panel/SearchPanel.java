package main.java.view.panel;

import main.java.text.SearchText;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * add panel for search feature.
 */
public class SearchPanel extends SwingPanel {

    private final SearchText searchText = new SearchText();

    private final List<String> resultList;

    private JButton searchButton;

    private JRadioButton searchUserButton, searchRecipeButton, searchMessageButton;

    private JRadioButton bruteForceButton, rabinKarpButton, boyerMooreHorspoolButton;

    private JTextField keywordFiled;

    private final JPanel resultPanel;

    private final List<JTextArea> textAreaList;

    /**
     * @param actionHandlers list of handlers
     * @param resultList list of result strings.
     * @param keyword the keyword user is searching for.
     */
    public SearchPanel(ActionHandler[] actionHandlers, List<String> resultList, String keyword) {
        super(actionHandlers);

        if (resultList == null) {
            this.resultList = null;
        } else {
            this.resultList = new ArrayList<>(resultList);
        }

        JTextArea headerText = new JTextArea(searchText.getSearchStr());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerText);

        JPanel inputPanel = generateInputPanel(keyword);

        JPanel searchTypeRadioPanel = generateSearchTypeRadioPanel();
        JPanel searchAlgorithmRadioPanel = generateSearchAlgorithmRadioPanel();

        textAreaList = new ArrayList<>();
        JPanel resultTitlePanel = generateResultTitlePanel(resultList);

        resultPanel = new JPanel();
        BoxLayout resultLayout = new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS);
        resultPanel.setLayout(resultLayout);
        resultPanel.setBorder(new EmptyBorder(new Insets(30, 10, 50, 10)));

        if (this.resultList != null) {
            addRows();
        }

        JScrollPane resultScrollPane = new JScrollPane();
        resultScrollPane.setViewportView(resultPanel);
        if (this.resultList == null) {
            resultScrollPane.setVisible(false);
        } else {
            resultScrollPane.setPreferredSize(new Dimension(650, 450));
        }

        JPanel mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 10, 80, 10)));
        mainPanel.add(headerPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(searchTypeRadioPanel);
        mainPanel.add(searchAlgorithmRadioPanel);
        mainPanel.add(resultTitlePanel);
        mainPanel.add(resultScrollPane);

        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        searchButton.addActionListener(actionHandlers[0]);

        setHeader(headerText);
        for (JTextArea textArea: textAreaList) {
            setDisabled(textArea);
        }
    }

    public String getKeywordText() {
        return this.keywordFiled.getText();
    }

    public String getSearchType() {
        if (searchUserButton.isSelected()) {
            return searchText.getUserStr();
        } else if (searchRecipeButton.isSelected()) {
            return searchText.getRecipeStr();
        } else {
            return searchText.getMessageStr();
        }
    }

    public String getSearchAlgorithm() {
        if (bruteForceButton.isSelected()) {
            return searchText.getBruteForceStr();
        } else if (rabinKarpButton.isSelected()) {
            return searchText.getRabinKarpStr();
        } else {
            return searchText.getBoyerMooreHorspoolStr();
        }
    }

    public void selectSearchUserButton() {
        searchUserButton.setSelected(true);
        searchRecipeButton.setSelected(false);
        searchMessageButton.setSelected(false);
    }

    public void selectSearchRecipeButton() {
        searchUserButton.setSelected(false);
        searchRecipeButton.setSelected(true);
        searchMessageButton.setSelected(false);
    }

    public void selectSearchMessageButton() {
        searchUserButton.setSelected(false);
        searchRecipeButton.setSelected(false);
        searchMessageButton.setSelected(true);
    }

    public void selectBruteForceButton() {
        bruteForceButton.setSelected(true);
        rabinKarpButton.setSelected(false);
        boyerMooreHorspoolButton.setSelected(false);
    }

    public void selectRabinKarpButton() {
        bruteForceButton.setSelected(false);
        rabinKarpButton.setSelected(true);
        boyerMooreHorspoolButton.setSelected(false);
    }

    public void selectBoyerMooreHorspoolButton() {
        bruteForceButton.setSelected(false);
        rabinKarpButton.setSelected(false);
        boyerMooreHorspoolButton.setSelected(true);
    }

    private JPanel generateInputPanel(String keyword) {
        JLabel keywordLabel = new JLabel();
        keywordLabel.setText(searchText.getKeywordLabel());

        keywordFiled = new JTextField(35);
        if (keyword != null) {
            keywordFiled.setText(keyword);
        }

        searchButton = new JButton(searchText.getSearchStr());

        JPanel inputPanel = new JPanel();
        BoxLayout inputPanelLayout = new BoxLayout(inputPanel, BoxLayout.X_AXIS);
        inputPanel.setLayout(inputPanelLayout);
        inputPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        inputPanel.add(keywordLabel);
        inputPanel.add(keywordFiled);
        inputPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        inputPanel.add(searchButton);
        return inputPanel;
    }

    private JPanel generateSearchTypeRadioPanel() {
        JLabel searchTypeLabel = new JLabel();
        searchTypeLabel.setText(searchText.getSearchTypeLabel());

        searchUserButton = new JRadioButton();
        searchUserButton.setText(searchText.getUserStr());
        searchUserButton.addActionListener(event -> handleSearchUserButton());
        if (resultList == null) {
            searchUserButton.setSelected(true);
        }

        searchRecipeButton = new JRadioButton();
        searchRecipeButton.setText(searchText.getRecipeStr());
        searchRecipeButton.addActionListener(event -> handleSearchRecipeButton());
        if (resultList == null) {
            searchRecipeButton.setSelected(false);
        }

        searchMessageButton = new JRadioButton();
        searchMessageButton.setText(searchText.getMessageStr());
        searchMessageButton.addActionListener(event -> handleSearchMessageButton());
        if (resultList == null) {
            searchMessageButton.setSelected(false);
        }

        JPanel radioPanel = new JPanel();
        BoxLayout radioLayout = new BoxLayout(radioPanel, BoxLayout.X_AXIS);
        radioPanel.setLayout(radioLayout);
        radioPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        radioPanel.add(searchTypeLabel);
        radioPanel.add(searchUserButton);
        radioPanel.add(searchRecipeButton);
        radioPanel.add(searchMessageButton);
        return radioPanel;
    }

    private void handleSearchUserButton() {
        if (searchUserButton.isSelected()) {
            searchRecipeButton.setSelected(false);
            searchMessageButton.setSelected(false);
        }
    }

    private void handleSearchRecipeButton() {
        if (searchRecipeButton.isSelected()) {
            searchUserButton.setSelected(false);
            searchMessageButton.setSelected(false);
        }
    }

    private void handleSearchMessageButton() {
        if (searchMessageButton.isSelected()) {
            searchUserButton.setSelected(false);
            searchRecipeButton.setSelected(false);
        }
    }

    private JPanel generateSearchAlgorithmRadioPanel() {
        JLabel searchAlgorithmLabel = new JLabel();
        searchAlgorithmLabel.setText(searchText.getSearchAlgorithmLabel());

        bruteForceButton = new JRadioButton();
        bruteForceButton.setText(searchText.getBruteForceStr());
        bruteForceButton.addActionListener(event -> handleBruteForceButton());
        if (resultList == null) {
            bruteForceButton.setSelected(true);
        }

        rabinKarpButton = new JRadioButton();
        rabinKarpButton.setText(searchText.getRabinKarpStr());
        rabinKarpButton.addActionListener(event -> handleRabinKarpButton());
        if (resultList == null) {
            rabinKarpButton.setSelected(false);
        }

        boyerMooreHorspoolButton = new JRadioButton();
        boyerMooreHorspoolButton.setText(searchText.getBoyerMooreHorspoolStr());
        boyerMooreHorspoolButton.addActionListener(event -> handleBoyerMooreHorspoolButton());
        if (resultList == null) {
            boyerMooreHorspoolButton.setSelected(false);
        }

        JPanel radioPanel = new JPanel();
        BoxLayout radioLayout = new BoxLayout(radioPanel, BoxLayout.X_AXIS);
        radioPanel.setLayout(radioLayout);
        radioPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        radioPanel.add(searchAlgorithmLabel);
        radioPanel.add(bruteForceButton);
        radioPanel.add(rabinKarpButton);
        radioPanel.add(boyerMooreHorspoolButton);
        return radioPanel;
    }

    private void handleBruteForceButton() {
        if (bruteForceButton.isSelected()) {
            rabinKarpButton.setSelected(false);
            boyerMooreHorspoolButton.setSelected(false);
        }
    }

    private void handleRabinKarpButton() {
        if (rabinKarpButton.isSelected()) {
            bruteForceButton.setSelected(false);
            boyerMooreHorspoolButton.setSelected(false);
        }
    }

    private void handleBoyerMooreHorspoolButton() {
        if (boyerMooreHorspoolButton.isSelected()) {
            bruteForceButton.setSelected(false);
            rabinKarpButton.setSelected(false);
        }
    }

    private JPanel generateResultTitlePanel(List<String> resultList) {
        JPanel resultTitlePanel = new JPanel();
        if (this.resultList != null) {
            JTextArea resultTitleText = new JTextArea(
                    searchText.getResultTitleStr(resultList.size()));
            textAreaList.add(resultTitleText);
            JPanel titlePanel = new JPanel();
            titlePanel.add(resultTitleText);
            resultTitlePanel.add(titlePanel);
        }
        return resultTitlePanel;
    }

    private void addRows() {
        for (int i = 0; i < this.resultList.size(); i ++) {
            String result = this.resultList.get(i);
            JTextArea resultText = new JTextArea(searchText.getResultStr(i + 1, result));
            textAreaList.add(resultText);
            JPanel panel = new JPanel();
            BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
            panel.setLayout(layout);
            panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            panel.add(resultText);
            resultPanel.add(panel);
        }
    }
}
