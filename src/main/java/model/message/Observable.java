package main.java.model.message;

/**
 * the interface for MessagePublisher
 */
public interface Observable {

    /**
     * Adds a new observer to this observable.
     * @param o new observer
     */
    void addObserver(Observer o);

    /**
     * Delete an observer of this observable.
     * @param o abandoned observer
     */
    void deleteObserver(Observer o);

    /**
     * Notify its observers with message.
     * @param message notification message
     */
    void notifyObservers(Message message);
}
