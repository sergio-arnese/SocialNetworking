package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class ReadingCommand implements Command, Recognizable {

    @Override
    public boolean isKnown(CommandLine commandLine) {
        String[] args = commandLine.getLine().split(" ");

        if( args.length != 1 ) {
            return false;
        }

        boolean isArgEmpty = "".equals(commandLine.getLine().trim());

        return !isArgEmpty;
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        CommandLine commandLine = socialNetwork.getCommandLine();

        if( socialNetwork.hasUser(commandLine.getLine().trim()) ) {
            User user = socialNetwork.getUser(commandLine.getLine().trim());

            return new ReadingOutputFormatter().getReadingOutput(user.getAllMessage());
        }

        return "";
    }
}
