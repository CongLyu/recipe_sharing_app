package main.java.view.menu;

import main.java.controller.AppController;
import main.java.model.user.User;
import main.java.view_handler.ActionHandler;
import main.java.view_handler.account.BrowseRegularHandler;
import main.java.view_handler.account.CreateAdminHandler;
import main.java.view_handler.account.LoginHistoryHandler;
import main.java.view_handler.login.ExitHandler;
import main.java.view_handler.login.LogOutHandler;
import main.java.view_handler.message.BrowseInboxHandler;
import main.java.view_handler.message.BrowseNotifyAllHandler;
import main.java.view_handler.recipe.BrowseRecipeHandler;
import main.java.view_handler.recipe.CreateRecipeHandler;
import main.java.view_handler.search.BrowseSearchHandler;

import javax.swing.*;

/**
 * Menu bar for the GUI.
 */
public class SwingMenuBarBuilder {

    private final AppController[] controllers;

    private final User currUser;

    private final JMenuBar menuBar;

    /**
     * the constructor of the swing menu bar
     * @param controllers the three controllers for the app.
     * @param user object of the current user
     */
    public SwingMenuBarBuilder(AppController[] controllers, User user) {
        this.controllers = controllers.clone();
        this.currUser = user;
        this.menuBar = new JMenuBar();
    }

    /**
     * @return the menu bar object.
     */
    public SwingMenuBarBuilder getThis() {
        return this;
    }

    /**
     * @return the admin account menu we just instantiated.
     */
    public SwingMenuBarBuilder adminAccountMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new CreateAdminHandler(this.controllers, this.currUser),
                new BrowseRegularHandler(this.controllers, this.currUser),
                new LoginHistoryHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new AdminAccountMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the regular account menu we just instantiated.
     */
    public SwingMenuBarBuilder regularAccountMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new LoginHistoryHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new RegularAccountMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the recipe menu we just instantiated.
     */
    public SwingMenuBarBuilder recipeMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new BrowseRecipeHandler(this.controllers, this.currUser),
                new CreateRecipeHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new RecipeMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the admin Message Menu we just instantiated.
     */
    public SwingMenuBarBuilder adminMessageMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new BrowseInboxHandler(this.controllers, this.currUser),
                new BrowseNotifyAllHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new AdminMessageMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the regular Message Menu we just instantiated.
     */
    public SwingMenuBarBuilder regularMessageMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new BrowseInboxHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new RegularMessageMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the search menu we just instantiated.
     */
    public SwingMenuBarBuilder searchMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new BrowseSearchHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new SearchMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the exit menu we just instantiated.
     */
    public SwingMenuBarBuilder exitMenu() {
        ActionHandler[] actionHandlers = new ActionHandler[]{
                new LogOutHandler(this.controllers, this.currUser),
                new ExitHandler(this.controllers, this.currUser)
        };
        this.menuBar.add(new ExitMenu(actionHandlers));
        return this.getThis();
    }

    /**
     * @return the menu bar we just built.
     */
    public JMenuBar build() {
        return this.menuBar;
    }
}
