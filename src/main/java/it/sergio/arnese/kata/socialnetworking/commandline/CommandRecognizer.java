package it.sergio.arnese.kata.socialnetworking.commandline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandRecognizer {
    private List<Recognizable> allKnownCommand = new ArrayList<>();

    public CommandRecognizer() {
        init();
    }

    private void init() {
        this.allKnownCommand.add(new FollowsCommand());
        this.allKnownCommand.add(new PostingCommand());
        this.allKnownCommand.add(new ReadingCommand());
        this.allKnownCommand.add(new WallCommand());
    }

    public Recognizable recognize(String line) {
        Objects.requireNonNull(line);

        return recognizeCommand(line);
    }

    private Recognizable recognizeCommand(String line) {
        Recognizable recognizedCommand = recognizeCommand(line, this.allKnownCommand);

        if( recognizedCommand == null ) {
            recognizedCommand = new UnknownCommand();
        }

        return recognizedCommand;
    }

    private Recognizable recognizeCommand(String line, List<Recognizable> recognizables) {
        Recognizable recognizedCommand = null;
        for (Recognizable command: recognizables) {
            if( command.isKnown(line) ) {
                recognizedCommand = command;
            }
        }

        return recognizedCommand;
    }
}

