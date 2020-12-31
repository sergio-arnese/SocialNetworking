package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class FollowsCommand implements Command, Recognizable {
    private final String FOLLOWS_COMMAND = "follows";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(FOLLOWS_COMMAND);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(FOLLOWS_COMMAND);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + FOLLOWS_COMMAND.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( ! isKnown(commandLine.getLine()) ) {
            return "";
        }

        String userName = commandLine.getArgBeforeCommandName(FOLLOWS_COMMAND);
        String followedUserName = commandLine.getArgAfterCommandName(FOLLOWS_COMMAND);

        if( socialNetwork.hasUser(userName) && socialNetwork.hasUser(followedUserName) ) {
            User user = socialNetwork.getUser(userName);
            User followedUser = socialNetwork.getUser(followedUserName);

            user.addFollowed(followedUser);
        }

        return "";
    }
}
