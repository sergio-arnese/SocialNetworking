package it.sergio.arnese.kata.socialnetworking.test;

import it.sergio.arnese.kata.socialnetworking.commandline.Command;
import it.sergio.arnese.kata.socialnetworking.commandline.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkTest {
    @Test
    void testSocialNetworkWithPostingInputLine() {
        String userName = "user";
        String postingCommandString = "->";
        String userMessage = "message";
        String postingInputLine = userName + " " + " " + postingCommandString + " " + userMessage;
        SocialNetwork socialNetwork = new SocialNetwork();

        String elaboration = socialNetwork.elaborate((Command) new CommandRecognizer().recognize(postingInputLine), postingInputLine);
        List<User> users = socialNetwork.getAllUser();

        assertTrue(elaboration.isEmpty());
        assertEquals(1, users.size());
        assertEquals("user", users.get(0).getName());
        assertTrue(users.get(0).getAllMessageWithTimestamp().contains(userMessage));
    }
}
