package it.sergio.arnese.kata.socialnetworking.domain.command;

public interface Recognizable {
    boolean isKnown(CommandLine commandLine);
}
