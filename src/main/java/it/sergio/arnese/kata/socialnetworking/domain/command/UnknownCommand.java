package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public class UnknownCommand extends OutputableBase implements Command, Recognizable, Outputable {

    @Override
    public boolean isKnown(String line) {
        return false;
    }

    @Override
    public void apply(SocialNetwork socialNetwork, CommandLine commandLine) {
    }
}
