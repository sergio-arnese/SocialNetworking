package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.command.*;
import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizerConf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandRecognizerTest {

    @Test
    void testWhenLineArgumentIsNull() {
        Exception exception = assertThrows(NullPointerException.class, ()-> new CommandRecognizerConf().getCommandRecognizer().recognize(null));

        assertNotNull(exception);
    }

    @Test
    void testWhenLineArgumentIsEmpty() {
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        CommandLine commandLine = new CommandLine("");

        assertEquals(UnknownCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsWall() {
        String userName = "user";
        String commandName = "wall";
        CommandLine commandLine = new CommandLine(userName + " " + commandName);
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(WallCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsPosting() {
        String userName = "user";
        String commandName = "->";
        String message = "something really interesting";
        CommandLine commandLine = new CommandLine(userName + " " + commandName + " " + message);
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(PostingCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsReading() {
        String userName = "user";
        CommandLine commandLine = new CommandLine(userName);
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(ReadingCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsFollowing() {
        String userName = "user";
        String commandName = "follows";
        String followedUserName = "auser";
        CommandLine commandLine = new CommandLine(userName + " " + commandName + " " + followedUserName);
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(FollowsCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }

    @Test
    void testWhenLineIsNotAvailableCommand() {
        String notAvailableCommand = "user :: what you want";
        CommandLine commandLine = new CommandLine(notAvailableCommand);
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();

        assertEquals(UnknownCommand.class.getSimpleName(), commandRecognizer.recognize(commandLine).getClass().getSimpleName());
    }
}
