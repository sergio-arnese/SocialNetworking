package it.sergio.arnese.kata.socialnetworking.commandline;

public class UnknownCommand implements Recognizable {

    @Override
    public boolean isKnown(String line) {
        return false;
    }
}
