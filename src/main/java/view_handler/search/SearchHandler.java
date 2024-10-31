package main.java.view_handler.search;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.controller.RecipeController;
import main.java.controller.UserController;
import main.java.model.user.User;
import main.java.text.SearchText;
import main.java.view.panel.SearchPanel;
import main.java.view_handler.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class SearchHandler extends ActionHandler {

    /**
     * @param controllers
     * @param user
     */
    public SearchHandler(AppController[] controllers, User user) {
        super(controllers, user);
    }

    /**
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame appFrame = (JFrame) SwingUtilities.getRoot((Component) event.getSource());
        SearchPanel sourcePanel = (SearchPanel) appFrame.getContentPane();
        String keyword = sourcePanel.getKeywordText();
        String searchType = sourcePanel.getSearchType();
        String searchAlgorithm = sourcePanel.getSearchAlgorithm();

        List<String> textList = getSearchTextList(searchType);
        PatternMatchStrategy matchStrategy = getMatchStrategy(searchAlgorithm);
        List<String> resultList = new ArrayList<>();
        for (String text: textList) {
            if (matchStrategy.match(text, keyword) != -1) {
                resultList.add(text);
            }
        }

        SearchPanel searchPanel = new SearchPanel(
                new ActionHandler[]{new SearchHandler(this.controllers, this.currUser)},
                resultList, keyword);
        handleSelection(searchPanel, searchType, searchAlgorithm);

        appFrame.getContentPane().removeAll();
        appFrame.setContentPane(searchPanel);
        appFrame.setVisible(true);
    }

    /**
     * @param searchType
     * @return
     */
    private List<String> getSearchTextList(String searchType) {
        SearchText searchText = new SearchText();
        if (searchType.equals(searchText.getUserStr())) {
            return ((UserController) controllers[0]).getAllUser()
                    .stream().map(Object::toString).collect(Collectors.toList());
        } else if (searchType.equals(searchText.getRecipeStr())) {
            return ((RecipeController) controllers[1]).getAllRecipe()
                    .stream().map(Object::toString).collect(Collectors.toList());
        } else {
            return ((MessageController) controllers[2]).getAllMessage()
                    .stream().map(Object::toString).collect(Collectors.toList());
        }
    }

    /**
     * @param searchAlgorithm
     * @return
     */
    private PatternMatchStrategy getMatchStrategy(String searchAlgorithm) {
        SearchText searchText = new SearchText();
        if (searchAlgorithm.equals(searchText.getBruteForceStr())) {
            return new BruteForceMatch();
        } else if (searchAlgorithm.equals(searchText.getRabinKarpStr())) {
            return new RabinKarpMatch();
        } else {
            return new BoyerMooreHorspoolMatch();
        }
    }

    /**
     * @param searchPanel
     * @param searchType
     * @param searchAlgorithm
     */
    private void handleSelection(
            SearchPanel searchPanel, String searchType, String searchAlgorithm) {
        SearchText searchText = new SearchText();
        if (searchType.equals(searchText.getUserStr())) {
            searchPanel.selectSearchUserButton();
        } else if (searchType.equals(searchText.getRecipeStr())) {
            searchPanel.selectSearchRecipeButton();
        } else {
            searchPanel.selectSearchMessageButton();
        }
        if (searchAlgorithm.equals(searchText.getBruteForceStr())) {
            searchPanel.selectBruteForceButton();
        } else if (searchAlgorithm.equals(searchText.getRabinKarpStr())) {
            searchPanel.selectRabinKarpButton();
        } else {
            searchPanel.selectBoyerMooreHorspoolButton();
        }
    }
}
