package main.java.model.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * the publisher of messages, which contains a list of observers that can receive the message from.
 */
public class MessagePublisher implements Observable, Serializable {

    private final List<Observer> observers;

    /**
     * Construct a new MessagePublisher object with a new empty list of observers.
     */
    public MessagePublisher() {
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a new observer to this publisher.
     */
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Delete an existed observer from the list of observers.
     */
    @Override
    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Send the message to all the observers of this publisher.
     * @param message the message that the publisher will send.
     */
    @Override
    public void notifyObservers(Message message) {
        for (Observer observer: this.observers) {
            if (message.getReceiverId().equals(observer.getUid())) {
                observer.update(message);
            }
        }
    }
}
