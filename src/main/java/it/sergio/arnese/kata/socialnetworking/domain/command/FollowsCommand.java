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
    public void apply(SocialNetwork socialNetwork, String line) {
        if( ! isKnown(line) ) {
            return;
        }

        if( socialNetwork.hasUser(getUserName(line)) && socialNetwork.hasUser(getFollowedUserName(line)) ) {
            User user = socialNetwork.getUser(getUserName(line));
            User followedUser = socialNetwork.getUser(getFollowedUserName(line));

            user.addFollowed(followedUser);
        }
    }

    private String getFollowedUserName(String line) {
        int indexOfExpectedCommand = line.indexOf(FOLLOWS_COMMAND_REPRESENTATION);
        return line.substring(indexOfExpectedCommand + FOLLOWS_COMMAND_REPRESENTATION.length()).trim();
    }

    private String getUserName(String line) {
        int indexOfExpectedCommand = line.indexOf(FOLLOWS_COMMAND_REPRESENTATION);
        return line.substring(0,indexOfExpectedCommand).trim();
    }
}
