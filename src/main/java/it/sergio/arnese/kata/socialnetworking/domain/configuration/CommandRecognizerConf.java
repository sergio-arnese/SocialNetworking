package it.sergio.arnese.kata.socialnetworking.domain.configuration;

import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.command.*;

public class CommandRecognizerConf {
    public CommandRecognizer getCommandRecognizer() {
        CommandRecognizer commandRecognizer = new CommandRecognizer();

        commandRecognizer.addCommand(new FollowsCommand());
        commandRecognizer.addCommand(new PostingCommand());
        commandRecognizer.addCommand(new ReadingCommand());
        commandRecognizer.addCommand(new WallCommand());

        commandRecognizer.setUnknownCommand(new UnknownCommand());

        return commandRecognizer;
    }
}
