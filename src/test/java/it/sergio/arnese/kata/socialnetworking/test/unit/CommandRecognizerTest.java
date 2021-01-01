package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.command.*;
import it.sergio.arnese.kata.socialnetworking.domain.configuration.CommandRecognizerConf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandRecognizerTest {

    @Test
    void testWhenLineArgumentisNull() {
        Exception exception = assertThrows(NullPointerException.class, ()->{ new CommandRecognizer().recognize(null);});

        assertNotNull(exception);
    }

    @Test
    void testWhenLineArgumentisEmpty() {
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(UnknownCommand.class.getSimpleName(), commandRecognizer.recognize("").getClass().getSimpleName());
    }

    @Test
    void testWhenLineisWall() {
        String userName = "user";
        String commandName = "wall";
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(WallCommand.class.getSimpleName(), commandRecognizer.recognize(userName + " " + commandName).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsPosting() {
        String userName = "user";
        String commandName = "->";
        String message = "something really interesting";

        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(PostingCommand.class.getSimpleName(), commandRecognizer.recognize(userName + " " + commandName + " " + message).getClass().getSimpleName());

    }

    @Test
    void testWhenLineIsReading() {
        String userName = "user";

        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(ReadingCommand.class.getSimpleName(), commandRecognizer.recognize(userName).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsFollowing() {
        String userName = "user";
        String commandName = "follows";
        String followedUserName = "auser";

        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(FollowsCommand.class.getSimpleName(), commandRecognizer.recognize(userName + " " + commandName + " " + followedUserName).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsNotAvailableCommand() {
        String notAvailableCommand = "user :: what you want";

        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(UnknownCommand.class.getSimpleName(), commandRecognizer.recognize(notAvailableCommand).getClass().getSimpleName());
    }
}
