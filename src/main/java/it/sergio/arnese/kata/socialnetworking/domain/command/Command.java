package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public interface Command {
    void apply(SocialNetwork socialNetwork, CommandLine commandLine);
    boolean hasOutput();
    String getOutput();
}
