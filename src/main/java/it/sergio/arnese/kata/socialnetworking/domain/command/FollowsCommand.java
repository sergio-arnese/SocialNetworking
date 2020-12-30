package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class FollowsCommand extends CommandWithoutOutput implements SocialNetworkCommand {
    private final String FOLLOWS_COMMAND_REPRESENTATION = "follows";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(FOLLOWS_COMMAND_REPRESENTATION);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(FOLLOWS_COMMAND_REPRESENTATION);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + FOLLOWS_COMMAND_REPRESENTATION.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public void apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( ! isKnown(commandLine.getLine()) ) {
            return;
        }

        String userName = commandLine.getArgBeforeCommandName(FOLLOWS_COMMAND_REPRESENTATION);
        String followedUserName = commandLine.getArgAfterCommandName(FOLLOWS_COMMAND_REPRESENTATION);

        if( socialNetwork.hasUser(userName) && socialNetwork.hasUser(followedUserName) ) {
            User user = socialNetwork.getUser(userName);
            User followedUser = socialNetwork.getUser(followedUserName);

            user.addFollowed(followedUser);
        }
    }
}
