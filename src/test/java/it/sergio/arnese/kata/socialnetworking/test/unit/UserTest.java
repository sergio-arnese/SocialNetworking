package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        String message = "message";
        User user = getUserWithUserNameAndMessage(userName, message);

        assertEquals(1, user.getAllMessage().size());
        assertEquals(message, user.getAllMessage().get(0).getContent());
    }

    @Test
    void testUserWithMoreMessage_NoAccessToMessageColl() {
        String userName = "user";
        String message = "message";
        User user = getUserWithUserNameAndMessage(userName, message);

        String amessage = "amessage";
        user.addMessage(new Message(amessage, new Date()));

        assertEquals(2, user.getAllMessage().size());
        assertEquals(message, user.getAllMessage().get(0).getContent());
        assertEquals(amessage, user.getAllMessage().get(1).getContent());

        List<Message> messages = user.getAllMessage();

        Collections.reverse(messages);

        assertEquals(2, user.getAllMessage().size());
        assertEquals(message, user.getAllMessage().get(0).getContent());
        assertEquals(amessage, user.getAllMessage().get(1).getContent());
    }

    @Test
    void testUserWithAFollowed() {
        String userName = "user";
        String message = "message";
        User user = getUserWithUserNameAndMessage(userName, message);

        String followedUsername = "followed";
        String followedUserMessage = "followed-message";
        User followedUser = getUserWithUserNameAndMessage(followedUsername, followedUserMessage);

        user.addFollowed(followedUser);

        assertEquals(1,user.getAllFollowedUser().size());
        assertEquals(followedUsername, user.getAllFollowedUser().get(0).getName());
        assertEquals(1,user.getAllFollowedUser().get(0).getAllMessage().size());
        assertEquals(followedUserMessage, user.getAllFollowedUser().get(0).getAllMessage().get(0).getContent());
    }

    private User getUserWithUserNameAndMessage(String userName, String message) {
        User user = new User(userName);
        user.addMessage(new Message(message, new Date()));
        return user;
    }

    @Test
    void testUserWithMoreFollowed() {
        String userName = "user";
        String message = "message";
        User user = getUserWithUserNameAndMessage(userName, message);

        String followedUsername = "followed";
        String followedUserMessage = "followed-message";
        User followed = getUserWithUserNameAndMessage(followedUsername, followedUserMessage);

        user.addFollowed(followed);

        String afollowedUsername = "afollowed";
        String afollowedUserMessage = "afollowed-message";
        User afollowed = getUserWithUserNameAndMessage(afollowedUsername, afollowedUserMessage);

        user.addFollowed(afollowed);

        assertEquals(2,user.getAllFollowedUser().size());
        assertEquals(followedUsername,user.getAllFollowedUser().get(0).getName());
        assertEquals(afollowedUsername,user.getAllFollowedUser().get(1).getName());
    }


    @Test
    void testUserWithMoreFollowed_NoAccessToUserColl() {
        String userName = "user";
        String message = "message";
        User user = getUserWithUserNameAndMessage(userName, message);

        String followedUsername = "followed";
        String followedUserMessage = "followed-message";
        User followed = getUserWithUserNameAndMessage(followedUsername, followedUserMessage);

        user.addFollowed(followed);

        String afollowedUsername = "afollowed";
        String afollowedUserMessage = "afollowed-message";
        User afollowed = getUserWithUserNameAndMessage(afollowedUsername, afollowedUserMessage);

        user.addFollowed(afollowed);

        assertEquals(2,user.getAllFollowedUser().size());
        assertEquals(followedUsername,user.getAllFollowedUser().get(0).getName());
        assertEquals(afollowedUsername,user.getAllFollowedUser().get(1).getName());

        List<User> messages = user.getAllFollowedUser();

        Collections.reverse(messages);

        assertEquals(2,user.getAllFollowedUser().size());
        assertEquals(followedUsername,user.getAllFollowedUser().get(0).getName());
        assertEquals(afollowedUsername,user.getAllFollowedUser().get(1).getName());
    }
}
