package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void testUserWithUserNameNull() {
        Exception exception = assertThrows(NullPointerException.class, ()-> new User(null));

        assertNotNull(exception);
    }

    @Test
    void testUserWithUserNameEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> new User(""));

        String excMessage = exception.getMessage();
        String expectedExcMessage = "name must be not empty";

        assertTrue(excMessage.contains(expectedExcMessage));
    }

    @Test
    void testUserWithUserNameNotEmpty() {
        String userName = "user";
        User user = new User(userName);

        assertEquals(userName, user.getName());
    }

    @Test
    void testUserWithAMessage() {
        String userName = "user";
        User user = new User(userName);
        String message = "message";
        user.addMessage(new Message(message, new Date()));

        assertEquals(1, user.getAllMessage().size());
        assertEquals(message, user.getAllMessage().get(0).getContent());
    }

    @Test
    void testUserWithAFollowed() {
        String userName = "user";
        User user = new User(userName);
        String message = "message";
        user.addMessage(new Message(message, new Date()));

        String followedUsername = "followed";
        User followedUser = new User(followedUsername);
        String followedUserMessage = "followed-message";
        followedUser.addMessage(new Message(followedUserMessage, new Date()));

        user.addFollowed(followedUser);

        assertEquals(1,user.getAllFollowedUser().size());
        assertEquals(followedUsername, user.getAllFollowedUser().get(0).getName());
        assertEquals(1,user.getAllFollowedUser().get(0).getAllMessage().size());
        assertEquals(followedUserMessage, user.getAllFollowedUser().get(0).getAllMessage().get(0).getContent());
    }
}
