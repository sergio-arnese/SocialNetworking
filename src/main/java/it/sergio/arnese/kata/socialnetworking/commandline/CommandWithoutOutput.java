package it.sergio.arnese.kata.socialnetworking.commandline;

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
