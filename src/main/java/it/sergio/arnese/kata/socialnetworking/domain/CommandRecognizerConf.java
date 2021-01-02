package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.*;

public class CommandRecognizerConf {
    public CommandRecognizer<CommandSN> getCommandRecognizer() {
        CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizer<>();

        commandRecognizer.addCommand(new FollowsCommand());
        commandRecognizer.addCommand(new PostingCommand());
        commandRecognizer.addCommand(new ReadingCommand());
        commandRecognizer.addCommand(new WallCommand());

        commandRecognizer.setUnknownCommand(new UnknownCommand());

        return commandRecognizer;
    }
}
