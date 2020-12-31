package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public interface Command extends Outputable {
    void apply(SocialNetwork socialNetwork, CommandLine commandLine);
}
