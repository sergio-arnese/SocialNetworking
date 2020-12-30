package it.sergio.arnese.kata.socialnetworking.commandline;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetworkCommand;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public class UnknownCommand extends CommandWithoutOutput implements SocialNetworkCommand {

    @Override
    public boolean isKnown(String line) {
        return false;
    }

    @Override
    public void apply(SocialNetwork socialNetwork, String line) {
    }
}
