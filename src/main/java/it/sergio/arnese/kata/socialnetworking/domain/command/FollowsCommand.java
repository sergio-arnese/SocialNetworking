package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public class FollowsCommand implements CommandSN {
    private final String FOLLOWS_COMMAND = "follows";

    @Override
    public boolean isKnown(CommandLine commandLine) {
        if(commandLine == null) {
            return false;
        }

        boolean isExpectedCommand = commandLine.containsCommandName(FOLLOWS_COMMAND);

        boolean isFirstArgEmpty = "".equals(commandLine.getArgBeforeCommandName(FOLLOWS_COMMAND));
        boolean isThirdArgEmpty = "".equals(commandLine.getArgAfterCommandName(FOLLOWS_COMMAND));

        return (isExpectedCommand && !isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        CommandLine commandLine = socialNetwork.getCommandLine();

        String userName = commandLine.getArgBeforeCommandName(FOLLOWS_COMMAND);
        String followedUserName = commandLine.getArgAfterCommandName(FOLLOWS_COMMAND);

        socialNetwork.addFollowedUser(userName, followedUserName);

        return "";
    }
}
