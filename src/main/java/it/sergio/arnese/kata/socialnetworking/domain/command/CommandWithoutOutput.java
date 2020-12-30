package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;

public abstract class CommandWithoutOutput implements Command {
    @Override
    public boolean hasOutput() {
        return false;
    }

    @Override
    public String getOutput() {
        return "";
    }

}
