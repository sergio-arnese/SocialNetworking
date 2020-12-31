package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandRecognizer {
    private final List<Recognizable> allKnownCommand = new ArrayList<>();

    public CommandRecognizer() {
        init();
    }

    private void init() {
        this.allKnownCommand.add(new FollowsCommand());
        this.allKnownCommand.add(new PostingCommand());
        this.allKnownCommand.add(new ReadingCommand());
        this.allKnownCommand.add(new WallCommand());
    }

    public <T extends Recognizable> T recognize(String line) {
        Objects.requireNonNull(line);

        return recognizeCommand(line);
    }

    private <T extends Recognizable> T recognizeCommand(String line) {
        T recognizedCommand = recognizeCommand(line, this.allKnownCommand);

        if( recognizedCommand == null ) {
            recognizedCommand = (T) new UnknownCommand();
        }

        return recognizedCommand;
    }

    private <T extends Recognizable> T recognizeCommand(String line, List<Recognizable> recognizables) {
        T recognizedCommand = null;
        for (Recognizable recognizable: recognizables) {
            if( recognizable.isKnown(line) ) {
                recognizedCommand = (T) recognizable;
            }
        }

        return recognizedCommand;
    }
}

