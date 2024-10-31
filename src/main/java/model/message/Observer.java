package main.java.model.message;

/**
 * the interface for MessageRepository
 */
public interface Observer {

    /**
     * Gets Uid of this observer.
     * @return Uid of this observer
     */
    String getUid();

    /**
     * Appends the receiver of the message's id to messageIdList.
     * @param message message sent
     */
    void update(Message message);
}
