package it.sergio.arnese.kata.socialnetworking.test;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;
import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkTest {

    private String userPostAMessage(String userName, String userMessage, SocialNetwork socialNetwork) {
        String postingCommandString = "->";
        String postingInputLine = userName + " " + " " + postingCommandString + " " + userMessage;

        return socialNetwork.elaborate((Command) new CommandRecognizer().recognize(postingInputLine), postingInputLine);
    }

    @Test
    void testSocialNetworkInitiallyHasNoUsers() {
        SocialNetwork socialNetwork = new SocialNetwork();

        assertTrue(socialNetwork.getAllUser().isEmpty());
    }

    @Test
    void testSocialNetworkWithPostingInputLine() {
        String userName = "user";
        String userMessage = "message";
        SocialNetwork socialNetwork = new SocialNetwork();

        String elaboration = userPostAMessage(userName, userMessage, socialNetwork);

        assertTrue(elaboration.isEmpty());
        assertEquals(1, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(userName));

        User user = socialNetwork.getUser(userName);

        assertEquals("user", user.getName());
        assertTrue(user.getAllMessageWithTimestamp().contains(userMessage));
    }

    @Test
    void testSocialNetworkWithReadingInputLine() {
        String userName = "user";
        String userMessage = "message";
        SocialNetwork socialNetwork = new SocialNetwork();
        userPostAMessage(userName, userMessage, socialNetwork);


        String readingInputLine = userName;
        String elaboration = socialNetwork.elaborate((Command) new CommandRecognizer().recognize(readingInputLine), readingInputLine);

        assertFalse(elaboration.isEmpty());
        assertTrue(elaboration.contains(userMessage));
    }

    @Test
    void testSocialNetworkWithFollowingInputLine_NoExistingUsers() {
        String userName = "user";
        String followingCommandString = "follows";
        String followedUserName = "auser";
        String followingInputLine = userName + " " + followingCommandString + " " + followedUserName;
        SocialNetwork socialNetwork = new SocialNetwork();

        String elaboration = socialNetwork.elaborate((Command) new CommandRecognizer().recognize(followingInputLine), followingInputLine);
        List<User> users = socialNetwork.getAllUser();

        assertTrue(elaboration.isEmpty());
        assertEquals(0, users.size());
    }

    @Test
    void testSocialNetworkAddingMoreUsers() {
        SocialNetwork socialNetwork = new SocialNetwork();

        String user1Name = "user1";
        String user1Message = "a user1 message";
        userPostAMessage(user1Name, user1Message, socialNetwork);

        assertEquals(1, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(user1Name));

        String user2Name = "user2";
        String user2Message = "a user2 message";
        userPostAMessage(user2Name, user2Message, socialNetwork);

        assertEquals(2, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(user2Name));

        String user1SecondMessage = "a second user1 message";
        userPostAMessage(user1Name, user1SecondMessage, socialNetwork);

        assertEquals(2, socialNetwork.getAllUser().size());

        String user2SecondMessage = "a second user2 message";
        userPostAMessage(user2Name, user2SecondMessage, socialNetwork);

        assertEquals(2, socialNetwork.getAllUser().size());
    }

    @Test
    void testSocialNetworkWithFollowingInputLine_UsersExist() {
        SocialNetwork socialNetwork = new SocialNetwork();

        String userName = "user";
        String userMessage = "a user message";
        userPostAMessage(userName, userMessage, socialNetwork);

        String followedUserName = "followed";
        String followedUserMessage = "a followed user message";
        userPostAMessage(followedUserName, followedUserMessage, socialNetwork);

        String followingCommandString = "follows";
        String followingInputLine = userName + " " + followingCommandString + " " + followedUserName;

        String elaboration = socialNetwork.elaborate((Command) new CommandRecognizer().recognize(followingInputLine), followingInputLine);

        assertTrue(elaboration.isEmpty());
        assertEquals(2, socialNetwork.getAllUser().size());

        User user = socialNetwork.getUser(userName);

        assertTrue(user.getAllMessageWithTimestamp().contains(userMessage));
        assertFalse(user.getAllMessageWithTimestamp().contains(followedUserMessage));

        assertTrue(user.getAllFollowedUserMessageWithTimestamp().contains(followedUserMessage));
    }

//    @Test
//    void testSocialNetworkWithWallInputLine() {
//        String userName = "user";
//        String followingCommandString = "follows";
//        String followedUserName = "auser";
//        String followingInputLine = userName + " " + followingCommandString + " " + followedUserName;
//        SocialNetwork socialNetwork = new SocialNetwork();
//
//        String elaboration = socialNetwork.elaborate((Command) new CommandRecognizer().recognize(followingInputLine), followingInputLine);
//        List<User> users = socialNetwork.getAllUser();
//
//        assertTrue(elaboration.isEmpty());
//        assertEquals(0, users.size());
//    }
}
