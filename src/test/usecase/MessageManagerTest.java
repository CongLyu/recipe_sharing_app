package test.usecase;

import main.java.model.message.Message;
import main.java.usecase.MessageManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MessageManagerTest {

    private String senderId1, receiverId1, subject1, content1;

    private String senderId2, receiverId2, subject2, content2;

    private String senderId3, receiverId3, subject3, content3;

    @Before
    public void setUp(){
        senderId1 = "10086";
        receiverId1 = UUID.randomUUID().toString();
        subject1 = "a notification of favorite list update";
        content1 = "The recipe 'AAA' is renamed to be 'BBB'.";
        senderId2 = UUID.randomUUID().toString();
        receiverId2 = UUID.randomUUID().toString();
        subject2 = "SYSTEM NOTIFICATION";
        content2 = "Hello!\nWelcome to this recipe sharing platform!";
        senderId3 = receiverId1;
        receiverId3 = receiverId2;
        subject3 = "Just to say 'hi' ;)";
        content3 = "Love your recipes LOL";
    }

    @Test
    public void testConstructorMessageList() {
        MessageManager messageManager = new MessageManager();
        List<Message> expected = new ArrayList<>();
        Assert.assertEquals(messageManager.getMessageList(), expected);
    }

    @Test
    public void testSendOneMessage() {
        MessageManager messageManager = new MessageManager();
        List<Message> expected = new ArrayList<>();
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        messageManager.send(receiverId1, message);
        expected.add(0, message);
        Assert.assertEquals(messageManager.getMessageList(), expected);
    }

    @Test
    public void testSendMultipleMessage() {
        MessageManager messageManager = new MessageManager();
        List<Message> expected = new ArrayList<>();
        Message message1 = new Message(senderId1, receiverId1, subject1, content1);
        messageManager.send(receiverId1, message1);
        expected.add(0, message1);
        Message message2 = new Message(senderId2, receiverId2, subject2, content2);
        messageManager.send(receiverId2, message2);
        expected.add(0, message2);
        Message message3 = new Message(senderId3, receiverId3, subject3, content3);
        messageManager.send(receiverId3, message3);
        expected.add(0, message3);
        Assert.assertEquals(messageManager.getMessageList(), expected);
    }

    @Test
    public void testGetMessageByIdAmongZeroMessage() {
        MessageManager messageManager = new MessageManager();
        Assert.assertNull(messageManager.getMessageById("invalidID"));
    }

    @Test
    public void testGetMessageByIdForNotExistingIdAmongSingleMessage() {
        MessageManager messageManager = new MessageManager();
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        messageManager.send(receiverId1, message);
        Assert.assertNull(messageManager.getMessageById("invalidID"));
    }

    @Test
    public void testGetMessageByIdForNotExistingIdAmongMultipleMessage() {
        MessageManager messageManager = new MessageManager();
        Message message1 = new Message(senderId1, receiverId1, subject1, content1);
        messageManager.send(receiverId1, message1);
        Message message2 = new Message(senderId2, receiverId2, subject2, content2);
        messageManager.send(receiverId2, message2);
        Message message3 = new Message(senderId3, receiverId3, subject3, content3);
        messageManager.send(receiverId3, message3);
        Assert.assertNull(messageManager.getMessageById("invalidID"));
    }

    @Test
    public void testGetMessageByIdForExistingIdAmongSingleMessage() {
        MessageManager messageManager = new MessageManager();
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        messageManager.send(receiverId1, message);
        Assert.assertEquals(messageManager.getMessageById(message.getId()), message);
    }

    @Test
    public void testGetMessageByIdForExistingIdAmongMultipleMessage() {
        MessageManager messageManager = new MessageManager();
        messageManager.send(receiverId1,
                new Message(senderId1, receiverId1, subject1, content1));
        Message message = new Message(senderId2, receiverId2, subject2, content2);
        messageManager.send(receiverId2, message);
        messageManager.send(receiverId3,
                new Message(senderId3, receiverId3, subject3, content3));
        Assert.assertEquals(messageManager.getMessageById(message.getId()), message);
    }
}
