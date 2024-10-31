package main.java.model.message;

import java.io.Serializable;
import java.util.*;

/**
 * the message repo of each user.
 */
public class MessageRepository implements Observer, Serializable, Iterable<String> {

    /**
     * The user id of this corresponding MessageRepository.
     */
    protected final String uid;

    /**
     * The id list of the messages the user has received.
     */
    protected final List<String> messageIdList;

    /**
     * Constructs a new MessageRepository object for a user whose id is uid.
     * @param uid the user id of the current message repo.
     */
    public MessageRepository(String uid) {
        this.uid = uid;
        this.messageIdList = new ArrayList<>();
    }

    /**
     * Indicates whether this repository and another obj are the same user's MessageRepository.
     * @param obj another MessageRepository we want to compare with.
     * @return true if the two MessageRepository share the same id, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof MessageRepository
                && this.uid.equals(((MessageRepository) obj).getUid());
    }

    /**
     * Gets the user id of the MessageRepository.
     * @return the user id of the MessageRepository
     */
    @Override
    public String getUid() {
        return this.uid;
    }

    /**
     * Adds newly received message to the MessageRepository.
     * @param message the new message needed to be added
     */
    @Override
    public void update(Message message) {
        if (message.getReceiverId().equals(this.uid)) {
            this.messageIdList.add(0, message.getId());
        }
    }

    /**
     * Returns the messageId iterator of the MessageRepository.
     * @return the messageId iterator of the MessageRepository
     */
    @Override
    public Iterator<String> iterator() {
        return new MessageIdIterator();
    }

    /**
     * Return the number of messageIds in the messageIdList.
     * @return the number of messageIds in the messageIdList
     */
    public Integer size() {
        return this.messageIdList.size();
    }

    private class MessageIdIterator implements Iterator<String> {

        private int index;

        /**
         * Returns true if the iterator hasn't reached the end of the list of messageId.
         * @return true if the iterator hasn't reached the end of the list of messageId.
         */
        @Override
        public boolean hasNext() {
            return index < messageIdList.size();
        }

        /**
         * Return the next messageID if it has next, return null otherwise.
         * @return the next messageID if it has next, return null otherwise
         */
        @Override
        public String next() {
            if (this.hasNext()){
                return messageIdList.get(index++);
            }
            return null;
        }
    }
}
