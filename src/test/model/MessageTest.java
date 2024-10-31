package test.model;

import main.java.model.message.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotEquals;

public class MessageTest {

    private String senderId1, receiverId1, subject1, content1;

    private String senderId2, receiverId2, subject2, content2;

    @Before
    public void setUp(){
        senderId1 = UUID.randomUUID().toString();
        receiverId1 = UUID.randomUUID().toString();
        subject1 = "a notification of favorite list update";
        content1 = "The recipe 'AAA' is renamed to be 'BBB'.";
        senderId2 = UUID.randomUUID().toString();
        receiverId2 = UUID.randomUUID().toString();
        subject2 = "SYSTEM NOTIFICATION";
        content2 = "Hello\nWorld";
    }

    @Test
    public void testConstructorSenderId() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        Assert.assertEquals(message.getSenderId(), senderId1);
    }

    @Test
    public void testConstructorReceiverId() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        Assert.assertEquals(message.getReceiverId(), receiverId1);
    }

    @Test
    public void testConstructorSubject() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        Assert.assertEquals(message.getSubject(), subject1);
    }

    @Test
    public void testConstructorContent() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        Assert.assertEquals(message.getContent(), content1);
    }

    @Test
    public void testEqual() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        Assert.assertEquals(message, message);
    }

    @Test
    public void testNotEqualSameInfo() {
        Message message1 = new Message(senderId1, receiverId1, subject1, content1);
        Message message2 = new Message(senderId1, receiverId1, subject1, content1);
        assertNotEquals(message1, message2);
    }

    @Test
    public void testEqualDiffInfo() {
        Message message1 = new Message(senderId1, receiverId1, subject1, content1);
        Message message2 = new Message(senderId2, receiverId2, subject2, content2);
        assertNotEquals(message1, message2);
    }

    @Test
    public void testToString() {
        Message message = new Message(senderId1, receiverId1, subject1, content1);
        String expected = String.join("\n",
                new String[]{"Subject: a notification of favorite list update",
                        "Content:",
                        "The recipe 'AAA' is renamed to be 'BBB'."});
        Assert.assertEquals(message.toString(), expected);
    }
}
