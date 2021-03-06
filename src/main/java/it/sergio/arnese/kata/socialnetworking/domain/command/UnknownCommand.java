package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public class UnknownCommand implements CommandSN {

    @Override
    public boolean isKnown(CommandLine commandLine) {
        return false;
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        return "";
    }
}
