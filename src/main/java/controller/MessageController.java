package main.java.controller;

import main.java.model.Recipe;
import main.java.model.message.Message;
import main.java.model.user.User;
import main.java.text.MessageText;
import main.java.usecase.MessageManager;

import java.io.File;
import java.util.*;

/**
 * A controller for Messages
 */
public class MessageController extends AppController {

    private final String[] MESSAGE_CONTROLLER_PATH_NAMES =
            {"src", "main", "resource", "message_controller.ser"};

    private final String MESSAGE_CONTROLLER_PATH =
            String.join(File.separator, MESSAGE_CONTROLLER_PATH_NAMES);

    private final MessageManager messageManager;

    /**
     * read the existed controller in from the default path, if it does not exist, create a new one and save it.
     */
    public MessageController() {
        MessageController loadedMessageController =
                (MessageController) load(MESSAGE_CONTROLLER_PATH);
        if (loadedMessageController == null) {
            this.messageManager = new MessageManager();
            save();
        } else {
            this.messageManager = loadedMessageController.getMessageManager();
        }
    }

    /**
     * save the current controller
     */
    @Override
    public void save() {
        this.write(MESSAGE_CONTROLLER_PATH);
    }

    private MessageManager getMessageManager() {
        return this.messageManager;
    }

    /**
     * @return all the messages in a list.
     */
    public List<Message> getAllMessage() {
        return this.messageManager.getMessageList();
    }

    /**
     * @param user current user
     * @return all the messages in the message repository of that user
     */
    public List<Message> getInboxMessages(User user) {
        List<Message> messageList = new ArrayList<>();
        for (String messageId: this.messageManager.getInbox(user.getUid())) {
            messageList.add(this.messageManager.getMessageById(messageId));
        }
        return messageList;
    }

    /**
     * @param recipe the recipe that has been edited
     * @param oldName the name of the recipe before changed
     * notify the users who have collected this recipe that the name of the recipe has been changed.
     */
    public void sendEditFavoriteRecipeNameMessage(Recipe recipe, String oldName) {
        Integer recipeID = recipe.getRecipeID();
        MessageText messageText = new MessageText();
        for (String uid: recipe.getCollectorList()) {
            Message message = new Message(
                    String.valueOf(recipeID),
                    uid,
                    messageText.getEditFavoriteRecipeSubject(),
                    messageText.getEditFavoriteRecipeNameMessage(oldName, recipe.getName()));
            this.messageManager.sendEditFavoriteRecipeMessage(recipe, message);
        }
    }

    /**
     * @param recipe the recipe that has been edited
     * @param oldIngredientMap the ingredients of the recipe before changed
     * notify the users who have collected this recipe that the ingredients of the recipe has been changed.
     */
    public void sendEditFavoriteRecipeIngredientMessage(
            Recipe recipe, Map<String, Integer> oldIngredientMap) {
        Integer recipeID = recipe.getRecipeID();
        MessageText messageText = new MessageText();
        for (String uid: recipe.getCollectorList()) {
            Message message = new Message(
                    String.valueOf(recipeID),
                    uid,
                    messageText.getEditFavoriteRecipeSubject(),
                    messageText.getEditFavoriteRecipeIngredientMessage(
                            recipe.getName(), oldIngredientMap, recipe.getIngredientMap()));
            this.messageManager.sendEditFavoriteRecipeMessage(recipe, message);
        }
    }

    /**
     * @param recipe the recipe that has been edited
     * @param oldStepList the steps of the recipe before changed
     * notify the users who have collected this recipe that the steps of the recipe has been changed.
     */
    public void sendEditFavoriteRecipeStepMessage(Recipe recipe, List<String> oldStepList) {
        Integer recipeID = recipe.getRecipeID();
        MessageText messageText = new MessageText();
        for (String uid: recipe.getCollectorList()) {
            Message message = new Message(
                    String.valueOf(recipeID),
                    uid,
                    messageText.getEditFavoriteRecipeSubject(),
                    messageText.getEditFavoriteRecipeStepMessage(
                            recipe.getName(), oldStepList, recipe.getStepList()));
            this.messageManager.sendEditFavoriteRecipeMessage(recipe, message);
        }
    }

    /**
     * @param userList list of all users
     * @param senderId user id of the sender
     * @param subject subject of the message
     * @param content content of the message
     * let admin user send a message to all users in the system
     */
    public void sendToAll(
            List<User> userList, String senderId, String subject, String content) {
        for (User user: userList) {
            String uid = user.getUid();
            Message message = new Message(senderId, uid, subject, content);
            this.messageManager.send(uid, message);
        }
    }

    /**
     * @param recipe current recipe
     * @param user user that will add this recipe to their favorite list.
     * add the message repo of the user to the list of collectors of the message publisher of the recipe.
     */
    public void subscribe(Recipe recipe, User user) {
        this.messageManager.addObserver(recipe.getRecipeID(), user.getUid());
    }

    /**
     * @param recipe current recipe
     * @param user user that will add this recipe to their favorite list.
     * remove the message repo of the user from the list of collectors of the message publisher of the recipe.
     */
    public void unsubscribe(Recipe recipe, User user) {
        this.messageManager.deleteObserver(recipe.getRecipeID(), user.getUid());
    }
}
