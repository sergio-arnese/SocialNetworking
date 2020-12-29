package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void testMessageContentNull() {
        Exception exception = assertThrows(NullPointerException.class, ()-> new Message(null, new Date()));

        assertNotNull(exception);
    }

    @Test
    void testMessageTimestampNull() {
        Exception exception = assertThrows(NullPointerException.class, ()-> new Message("message", null));

        assertNotNull(exception);
    }

    @Test
    void testMessageContentEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> new Message("", new Date()));

        String excMessage = exception.getMessage();
        String expectedExcMessage = "content must be not empty";

        assertTrue(excMessage.contains(expectedExcMessage));
    }

    @Test
    void testMessageContent() {
        String messageContent = "message";
        Date now = new Date();

        Message message = new Message(messageContent, now);

        assertEquals(messageContent, message.getContent());
    }

    @Test
    void testMessageTimeStamp() {
        String messageContent = "message";
        Date now = new Date();

        Message message = new Message(messageContent, now);

        assertEquals(now, message.getTimestamp());
    }
}
