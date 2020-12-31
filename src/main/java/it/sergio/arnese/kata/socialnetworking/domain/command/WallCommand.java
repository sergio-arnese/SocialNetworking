package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class WallCommand implements Command, Recognizable {
    private final String WALL_COMMAND = "wall";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(WALL_COMMAND);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(WALL_COMMAND);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + WALL_COMMAND.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        String userName = commandLine.getArgBeforeCommandName(WALL_COMMAND);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);

            return new WallOutputFormatter().getWallOutput(user);
        }

        return "";
    }
}
