package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class WallCommand extends CommandWithOutput implements SocialNetworkCommand {
    private final String WALL_COMMAND_REPRESENTATION = "wall";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(WALL_COMMAND_REPRESENTATION);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(WALL_COMMAND_REPRESENTATION);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + WALL_COMMAND_REPRESENTATION.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && isThirdArgEmpty);
    }

    @Override
    public void apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        cleanOutput();

        String userName = commandLine.getArgBeforeCommandName(WALL_COMMAND_REPRESENTATION);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);

            setOutput(user.writeAll());
        }
    }
}
