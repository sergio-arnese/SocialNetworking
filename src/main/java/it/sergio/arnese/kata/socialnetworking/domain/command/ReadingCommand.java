package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class ReadingCommand implements Command, Recognizable {

    @Override
    public boolean isKnown(String line) {
        String[] args = line.split(" ");

        if( args.length != 1 ) {
            return false;
        }

        boolean isArgEmpty = "".equals(line.trim());

        return !isArgEmpty;
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( socialNetwork.hasUser(commandLine.getLine().trim()) ) {
            User user = socialNetwork.getUser(commandLine.getLine().trim());

            return new ReadingOutputFormatter().getReadingOutput(user.getAllMessage());
        }

        return "";
    }
}
