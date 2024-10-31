package main.java.usecase;

import main.java.model.Recipe;
import main.java.model.message.Message;
import main.java.model.message.MessagePublisher;
import main.java.model.message.MessageRepository;

import java.util.*;

public class MessageManager extends EntityManager {

    private final List<Message> messageList;

    private final Map<Integer, MessagePublisher> recipePublisherMap;

    private final Map<String, MessagePublisher> userPublisherMap;

    private final Map<String, MessageRepository> inboxMap;

    /**
     * Constructs a new MessageManager object and initialize messageList, recipePublisherMap, userPublisherMap and
     * inboxMap as empty.
     */
    public MessageManager() {
        this.messageList = new ArrayList<>();
        this.recipePublisherMap = new HashMap<>();
        this.userPublisherMap = new HashMap<>();
        this.inboxMap = new HashMap<>();
    }

    /**
     * Gets MessageList of this MessageManager.
     * @return MessageList of this MessageManager
     */
    public List<Message> getMessageList() {
        return this.messageList;
    }

    /**
     * Gets RecipePublisherMap of this MessageManager.
     * @return RecipePublisherMap of this MessageManager
     */
    public Map<Integer, MessagePublisher> getRecipePublisherMap() {
        return this.recipePublisherMap;
    }

    /**
     * Gets UserPublisherMap of this MessageManager.
     * @return UserPublisherMap of this MessageManager
     */
    public Map<String, MessagePublisher> getUserPublisherMap() {
        return this.userPublisherMap;
    }

    /**
     * Gets InboxMap of this MessageManager.
     * @return InboxMap of this MessageManager
     */
    public Map<String, MessageRepository> getInboxMap() {
        return this.inboxMap;
    }

    /**
     * @param messageId
     * @return
     */
    public Message getMessageById(String messageId) {
        for (Message message: this.messageList) {
            if (message.getId().equals(messageId)) {
                return message;
            }
        }
        return null;
    }

    /**
     * @param receiverId
     * @param senderId
     * @return
     */
    public List<Message> getAll(String receiverId, String senderId) {
        List<Message> messageList = new ArrayList<>();
        for (String messageId : this.inboxMap.get(receiverId)) {
            Message message = getMessageById(messageId);
            if (message.getSenderId().equals(senderId)) {
                messageList.add(message);
            }
        }
        return messageList;
    }

    /**
     * @param uid
     * @return
     */
    public MessageRepository getInbox(String uid) {
        if (!this.inboxMap.containsKey(uid)) {
            this.inboxMap.put(uid, new MessageRepository(uid));
        }
        return this.inboxMap.get(uid);
    }

    /**
     * @param recipeId
     * @param uid
     */
    public void addObserver(Integer recipeId, String uid) {
        MessageRepository inbox = getInbox(uid);
        if (!this.recipePublisherMap.containsKey(recipeId)) {
            this.recipePublisherMap.put(recipeId, new MessagePublisher());
        }
        this.recipePublisherMap.get(recipeId).addObserver(inbox);
    }

    /**
     * @param recipeId
     * @param uid
     */
    public void deleteObserver(Integer recipeId, String uid) {
        MessageRepository inbox = getInbox(uid);
        if (!this.recipePublisherMap.containsKey(recipeId)) {
            this.recipePublisherMap.put(recipeId, new MessagePublisher());
        }
        this.recipePublisherMap.get(recipeId).deleteObserver(inbox);
    }

    /**
     * @param uid
     * @param message
     */
    public void send(String uid, Message message) {
        addMessage(message);
        MessageRepository inbox = getInbox(uid);
        inbox.update(message);
    }

    /**
     * @param recipe
     * @param message
     */
    public void sendEditFavoriteRecipeMessage(Recipe recipe, Message message) {
        Integer recipeId = recipe.getRecipeID();
        if (!this.recipePublisherMap.containsKey(recipeId)) {
            this.recipePublisherMap.put(recipeId, new MessagePublisher());
        }
        this.recipePublisherMap.get(recipeId).notifyObservers(message);
        addMessage(message);
    }

    /**
     * @param message
     */
    private void addMessage(Message message) {
        this.messageList.add(0, message);
    }
}
