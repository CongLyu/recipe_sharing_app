package main.java.model.message;

import main.java.text.MessageText;
import main.java.utility.StrIdGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * the message entity that can be sent from recipes or admin users to all users.
 */
public class Message implements Serializable {

    private final static StrIdGenerator idGenerator = new StrIdGenerator();

    private final String id;

    private final Date createdTime;

    private final String subject;

    private final String senderId;

    private final String receiverId;

    private final String content;

    /**
     * Constructs a new message object with senderId, receiverId, subject and content.
     * @param senderId the id of the sender
     * @param receiverId the id of the receiver
     * @param subject the subject of the message
     * @param content the content of the message
     */
    public Message(String senderId, String receiverId, String subject, String content) {
        this.id = assignId();
        this.createdTime = new Date();
        this.subject = subject;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    /**
     * Indicates whether this message is the same as another object.
     * @param obj another message we want to compare.
     * @return true if the two messages share the same id, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Message
                && this.id.equals(((Message) obj).getId());
    }

    /**
     * Gets a string representations of the message.
     * @return the string representation of the message
     */
    @Override
    public String toString() {
        return String.format(
                new MessageText().getMessageTemplate(),
                this.subject, this.content);
    }

    private static String assignId() {
        return idGenerator.generate();
    }

    /**
     * Gets the id of the message.
     * @return id of the message
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the time that the recipe is created.
     * @return the time that the recipe is created
     */
    public Date getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Gets the subject of the message.
     * @return the subject of the message
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Gets the user id of the sender of the message.
     * @return the user id of the sender of the message
     */
    public String getSenderId() {
        return this.senderId;
    }

    /**
     * Gets the user id of the receiver of the message.
     * @return the user id of the receiver of the message
     */
    public String getReceiverId() {
        return this.receiverId;
    }

    /**
     * Gets the content of the message.
     * @return the content of the message
     */
    public String getContent() {
        return this.content;
    }
}
