package it.sergio.arnese.kata.socialnetworking.test;

import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;
import it.sergio.arnese.kata.socialnetworking.domain.configuration.CommandRecognizerConf;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkTest {

    private String userPostAMessage(String userName, String userMessage, SocialNetwork socialNetwork, CommandRecognizer commandRecognizer) {
        String postingCommandString = "->";
        String postingInputLine = userName + " " + " " + postingCommandString + " " + userMessage;

        return socialNetwork.elaborate(commandRecognizer.recognize(postingInputLine), new CommandLine(postingInputLine));
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
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        String elaboration = userPostAMessage(userName, userMessage, socialNetwork, commandRecognizer);

        assertTrue(elaboration.isEmpty());
        assertEquals(1, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(userName));
    }

    @Test
    void testSocialNetworkWithReadingInputLine() {
        String userName = "user";
        String userMessage = "message";
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();
        userPostAMessage(userName, userMessage, socialNetwork, commandRecognizer);


        String readingInputLine = userName;
        String elaboration = socialNetwork.elaborate(commandRecognizer.recognize(readingInputLine), new CommandLine(readingInputLine));

        assertFalse(elaboration.isEmpty());
        assertTrue(elaboration.contains(userMessage));
    }

    @Test
    void testSocialNetworkWithFollowingInputLine_NoExistingUsers() {
        String userName = "user";
        String followingCommandString = "follows";
        String followedUserName = "auser";
        String followingInputLine = userName + " " + followingCommandString + " " + followedUserName;
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        String elaboration = socialNetwork.elaborate(commandRecognizer.recognize(followingInputLine), new CommandLine(followingInputLine));
        List<User> users = socialNetwork.getAllUser();

        assertTrue(elaboration.isEmpty());
        assertEquals(0, users.size());
    }

    @Test
    void testSocialNetworkAddingMoreUsers() {
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        String user1Name = "user1";
        String user1Message = "a user1 message";
        userPostAMessage(user1Name, user1Message, socialNetwork, commandRecognizer);

        assertEquals(1, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(user1Name));

        String user2Name = "user2";
        String user2Message = "a user2 message";
        userPostAMessage(user2Name, user2Message, socialNetwork, commandRecognizer);

        assertEquals(2, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(user2Name));

        String user1SecondMessage = "a second user1 message";
        userPostAMessage(user1Name, user1SecondMessage, socialNetwork, commandRecognizer);

        assertEquals(2, socialNetwork.getAllUser().size());

        String user2SecondMessage = "a second user2 message";
        userPostAMessage(user2Name, user2SecondMessage, socialNetwork, commandRecognizer);

        assertEquals(2, socialNetwork.getAllUser().size());
    }

    @Test
    void testSocialNetworkWithFollowingInputLine_UsersExist() {
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        String userName = "user";
        String userMessage = "a user message";
        userPostAMessage(userName, userMessage, socialNetwork, commandRecognizer);

        String followedUserName = "followed";
        String followedUserMessage = "a followed user message";
        userPostAMessage(followedUserName, followedUserMessage, socialNetwork, commandRecognizer);

        String followingCommandString = "follows";
        String followingInputLine = userName + " " + followingCommandString + " " + followedUserName;

        String elaboration = socialNetwork.elaborate(commandRecognizer.recognize(followingInputLine), new CommandLine(followingInputLine));

        assertTrue(elaboration.isEmpty());
        assertEquals(2, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(userName));

        User user = socialNetwork.getUser(userName);

        assertEquals(1, user.getAllFollowedUser().size());
        assertEquals(followedUserName, user.getAllFollowedUser().get(0).getName());
    }

    @Test
    void testSocialNetworkWithWallInputLine() {
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        String user1Name = "user1";
        String user1Message = "a user1 message";
        userPostAMessage(user1Name, user1Message, socialNetwork, commandRecognizer);

        assertEquals(1, socialNetwork.getAllUser().size());
        assertTrue(socialNetwork.hasUser(user1Name));

        String user2Name = "user2";
        String user2Message = "a user2 message";
        userPostAMessage(user2Name, user2Message, socialNetwork, commandRecognizer);

        String followingCommandString = "follows";
        String followingInputLine = user1Name + " " + followingCommandString + " " + user2Name;

        socialNetwork.elaborate(commandRecognizer.recognize(followingInputLine), new CommandLine(followingInputLine));

        String wallCommandString = "wall";
        String wallInputLine = user1Name + " " + wallCommandString;

        String elaboration = socialNetwork.elaborate(commandRecognizer.recognize(wallInputLine), new CommandLine(wallInputLine));

        assertFalse(elaboration.isEmpty());

        assertTrue(elaboration.contains(user1Name));
        assertTrue(elaboration.contains(user2Name));

        assertTrue(elaboration.contains(user1Message));
        assertTrue(elaboration.contains(user2Message));
    }
}
