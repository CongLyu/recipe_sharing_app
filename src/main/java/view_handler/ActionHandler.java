package main.java.view_handler;

import main.java.controller.AppController;
import main.java.model.user.User;

import java.awt.event.ActionListener;

/**
 *
 */
public abstract class ActionHandler implements ActionListener {

    protected final AppController[] controllers;

    protected final User currUser;

    /**
     * @param controllers
     * @param user
     */
    public ActionHandler(AppController[] controllers, User user) {
        this.controllers = controllers.clone();
        this.currUser = user;
    }
}
