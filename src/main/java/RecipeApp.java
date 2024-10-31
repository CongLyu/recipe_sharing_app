package main.java;

import main.java.controller.AppController;
import main.java.controller.MessageController;
import main.java.controller.RecipeController;
import main.java.controller.UserController;
import main.java.text.CommonText;
import main.java.view.panel.LoginPanel;
import main.java.view_handler.ActionHandler;
import main.java.view_handler.login.SignInHandler;
import main.java.view_handler.login.SignUpHandler;

import javax.swing.*;

public class RecipeApp {

    private final UserController userController;

    private final RecipeController recipeController;

    private final MessageController messageController;

    public RecipeApp() {
        this.userController = new UserController();
        this.recipeController = new RecipeController();
        this.messageController = new MessageController();
    }

    public void run() {
        AppController[] controllers = new AppController[] {
                this.userController, this.recipeController, this.messageController};
        JFrame recipeAppFrame = new JFrame(new CommonText().getRecipeApp());
        LoginPanel loginPanel = new LoginPanel(new ActionHandler[]{
                new SignInHandler(controllers, null),
                new SignUpHandler(controllers, null, false)},
                false);
        recipeAppFrame.setJMenuBar(null);
        recipeAppFrame.setContentPane(loginPanel);
        recipeAppFrame.setSize(1200,960);
        recipeAppFrame.setVisible(true);
        recipeAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}