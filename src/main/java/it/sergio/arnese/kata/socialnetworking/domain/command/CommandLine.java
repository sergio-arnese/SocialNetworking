package it.sergio.arnese.kata.socialnetworking.domain.command;

import java.util.Objects;

public class CommandLine {

    private String line;
    public CommandLine(String line) {
        this.line = Objects.requireNonNull(line);
    }

    public String getLine() {
        return this.line;
    }

    public boolean containsCommandName(String commandName) {
        Objects.requireNonNull(commandName);

        return this.line.contains(commandName);
    }

    public String getArgBeforeCommandName(String commandName) {
        Objects.requireNonNull(commandName);

        int indexOfCommand = this.line.indexOf(commandName);

        return ( indexOfCommand != -1 ? this.line.substring(0,indexOfCommand).trim() : "" );
    }

    public String getArgAfterCommandName(String commandName) {
        Objects.requireNonNull(commandName);

        int indexOfCommand = this.line.indexOf(commandName);

        return ( indexOfCommand != -1 ? this.line.substring(indexOfCommand + commandName.length()).trim() : "" );
    }
}
