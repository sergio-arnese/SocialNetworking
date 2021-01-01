package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandRecognizer {
    private final List<Recognizable> allKnownCommand = new ArrayList<>();
    private Recognizable unknownCommand;

    public <T extends Recognizable> boolean addCommand(T recognizable) {
        Objects.requireNonNull(recognizable);

        return this.allKnownCommand.add(recognizable);
    }

    public <T extends Recognizable> void setUnknownCommand(T command) {
        Objects.requireNonNull(command);

        this.unknownCommand = command;
    }

    public <T extends Recognizable> T recognize(String line) {
        Objects.requireNonNull(line);

        return recognizeCommand(line);
    }

    private <T extends Recognizable> T recognizeCommand(String line) {
        T recognizedCommand = recognizeCommand(line, this.allKnownCommand);

        if( recognizedCommand == null ) {
            recognizedCommand = (T)this.unknownCommand;
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

