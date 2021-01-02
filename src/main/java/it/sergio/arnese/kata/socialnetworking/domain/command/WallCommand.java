package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class WallCommand implements CommandSN {
    private final String WALL_COMMAND = "wall";

    @Override
    public boolean isKnown(CommandLine commandLine) {
        if(commandLine == null) {
            return false;
        }

        boolean isFirstArgEmpty = "".equals(commandLine.getArgBeforeCommandName(WALL_COMMAND));
        boolean isThirdArgEmpty = "".equals(commandLine.getArgAfterCommandName(WALL_COMMAND));

        return (!isFirstArgEmpty && isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        CommandLine commandLine = socialNetwork.getCommandLine();

        String userName = commandLine.getArgBeforeCommandName(WALL_COMMAND);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);

            return new WallOutputFormatter().getWallOutput(user);
        }

        return "";
    }
}
