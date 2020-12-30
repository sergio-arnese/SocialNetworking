package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;

import java.util.Objects;

public abstract class CommandWithOutput implements Command {
    protected String output = "";

    protected void setOutput(String output) {
        this.output = Objects.requireNonNull(output);
    }

    protected void cleanOutput() {
        this.output = "";
    }

    @Override
    public boolean hasOutput() {
        return true;
    }

    @Override
    public String getOutput() {
        return this.output;
    }
}