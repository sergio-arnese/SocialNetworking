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

    public <T extends Recognizable> T recognize(CommandLine commandLine) {
        Objects.requireNonNull(commandLine);

        return recognizeCommand(commandLine);
    }

    private <T extends Recognizable> T recognizeCommand(CommandLine commandLine) {
        T recognizedCommand = recognizeCommand(commandLine, this.allKnownCommand);

        if( recognizedCommand == null ) {
            recognizedCommand = (T)this.unknownCommand;
        }

        return recognizedCommand;
    }

    private <T extends Recognizable> T recognizeCommand(CommandLine commandLine, List<Recognizable> recognizables) {
        T recognizedCommand = null;
        for (Recognizable recognizable: recognizables) {
            if( recognizable.isKnown(commandLine) ) {
                recognizedCommand = (T) recognizable;
            }
        }

        return recognizedCommand;
    }
}

