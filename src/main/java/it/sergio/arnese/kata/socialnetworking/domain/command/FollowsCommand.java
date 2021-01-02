package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class FollowsCommand implements CommandSN {
    private final String FOLLOWS_COMMAND = "follows";

    @Override
    public boolean isKnown(CommandLine commandLine) {
        if(commandLine == null) {
            return false;
        }

        boolean isFirstArgEmpty = "".equals(commandLine.getArgBeforeCommandName(FOLLOWS_COMMAND));
        boolean isThirdArgEmpty = "".equals(commandLine.getArgAfterCommandName(FOLLOWS_COMMAND));

        return (!isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        CommandLine commandLine = socialNetwork.getCommandLine();

//        if( ! isKnown(commandLine) ) {
//            return "";
//        }

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
