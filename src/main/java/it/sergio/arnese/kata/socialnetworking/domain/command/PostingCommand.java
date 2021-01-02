package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

import java.util.Date;

public class PostingCommand implements CommandSN {
    private final String POSTING_COMMAND = "->";

    @Override
    public boolean isKnown(CommandLine commandLine) {
        if(commandLine == null) {
            return false;
        }

        boolean isExpectedCommand = commandLine.containsCommandName(POSTING_COMMAND);

        boolean isFirstArgEmpty = "".equals(commandLine.getArgBeforeCommandName(POSTING_COMMAND));
        boolean isThirdArgEmpty = "".equals(commandLine.getArgAfterCommandName(POSTING_COMMAND));

        return (isExpectedCommand && !isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork) {
        CommandLine commandLine = socialNetwork.getCommandLine();

        String userName = commandLine.getArgBeforeCommandName(POSTING_COMMAND);
        String userMessage = commandLine.getArgAfterCommandName(POSTING_COMMAND);

        if( socialNetwork.hasUser(userName) ) {
            socialNetwork.addMessageToUser(userName, userMessage);
        } else {
            User user = new User(userName);
            user.addMessage(new Message(userMessage, new Date()));
            socialNetwork.addUser(user);
        }

        return "";
    }
}
