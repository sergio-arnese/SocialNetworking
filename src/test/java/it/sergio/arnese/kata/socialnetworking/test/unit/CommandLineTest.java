package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.CommandLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {

    @Test
    void testWhenLineArgumentisNull() {
        Exception exception = assertThrows(NullPointerException.class, ()->{
            CommandLine commandLine = new CommandLine(null);});

        assertNotNull(exception);
    }

    @Test
    void testWhenLineArgumentisEmpty() {
        CommandLine commandLine = new CommandLine("");

        assertEquals("", commandLine.getUserName());
        assertEquals("", commandLine.getUserCommand());
        assertEquals("", commandLine.getFollowedUserName());
        assertEquals("", commandLine.getUserMessage());
    }

    @Test
    void testWhenLineisWall() {
        String userName = "user";
        String commandName = "wall";
        CommandLine commandLine = new CommandLine(userName + " " + commandName);

        assertEquals(userName, commandLine.getUserName());
        assertEquals(commandName, commandLine.getUserCommand());
        assertEquals("", commandLine.getUserMessage());
        assertEquals("", commandLine.getFollowedUserName());
    }

    @Test
    void testWhenLineIsPosting() {
        String userName = "user";
        String commandName = "->";
        String message = "something really interesting";

        CommandLine commandLine = new CommandLine(userName + " " + commandName + " " + message);

        assertEquals(userName, commandLine.getUserName());
        assertEquals(commandName, commandLine.getUserCommand());
        assertEquals(message, commandLine.getUserMessage());
        assertEquals("", commandLine.getFollowedUserName());

    }

    @Test
    void testWhenLineIsReading() {
        String userName = "user";

        CommandLine commandLine = new CommandLine(userName);

        assertEquals(userName, commandLine.getUserName());
        assertEquals("", commandLine.getUserCommand());
        assertEquals("", commandLine.getUserMessage());
        assertEquals("", commandLine.getFollowedUserName());
    }

    @Test
    void testWhenLineIsFollowing() {
        String userName = "user";
        String commandName = "follows";
        String followedUserName = "auser";

        CommandLine commandLine = new CommandLine(userName + " " + commandName + " " + followedUserName);

        assertEquals(userName, commandLine.getUserName());
        assertEquals(commandName, commandLine.getUserCommand());
        assertEquals("", commandLine.getUserMessage());
        assertEquals(followedUserName, commandLine.getFollowedUserName());
    }

    @Test
    void testWhenLineIsNotAvailableCommand() {
        String notAvailableCommand = "user :: what you want";

        CommandLine commandLine = new CommandLine(notAvailableCommand);

        assertEquals(notAvailableCommand, commandLine.getUserName());
        assertEquals("", commandLine.getUserCommand());
        assertEquals("", commandLine.getUserMessage());
        assertEquals("", commandLine.getFollowedUserName());
    }
}
