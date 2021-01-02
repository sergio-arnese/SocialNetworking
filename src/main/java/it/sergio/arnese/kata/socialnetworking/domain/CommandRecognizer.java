package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandRecognizer <T extends CommandSN> {
    private final List<T> allKnownCommand = new ArrayList<>();
    private T unknownCommand;

    public boolean addCommand(T recognizable) {
        Objects.requireNonNull(recognizable);

        return this.allKnownCommand.add(recognizable);
    }

    public void setUnknownCommand(T command) {
        Objects.requireNonNull(command);

        this.unknownCommand = command;
    }

    public T recognize(CommandLine commandLine) {
        Objects.requireNonNull(commandLine);

        return recognizeCommand(commandLine);
    }

    private T recognizeCommand(CommandLine commandLine) {
        T recognizedCommand = recognizeCommand(commandLine, this.allKnownCommand);

        if( recognizedCommand == null ) {
            recognizedCommand = this.unknownCommand;
        }

        return recognizedCommand;
    }

    private T recognizeCommand(CommandLine commandLine, List<T> recognizables) {
        T recognizedCommand = null;
        for (T recognizable: recognizables) {
            if( recognizable.isKnown(commandLine) ) {
                recognizedCommand = recognizable;
            }
        }

        return recognizedCommand;
    }
}

