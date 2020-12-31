package it.sergio.arnese.kata.socialnetworking.domain.command;

import java.util.Objects;

public abstract class OutputableBase implements Outputable {
    protected String output = "";

    protected void setOutput(String output) {
        this.output = Objects.requireNonNull(output);
    }

    protected void cleanOutput() {
        this.output = "";
    }

    @Override
    public boolean hasOutput() {
        return !"".equals(getOutput());
    }

    @Override
    public String getOutput() {
        return this.output;
    }
}
