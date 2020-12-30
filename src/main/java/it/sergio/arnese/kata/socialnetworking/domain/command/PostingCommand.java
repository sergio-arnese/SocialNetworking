package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

import java.util.Date;

public class PostingCommand extends CommandWithoutOutput implements SocialNetworkCommand {
    private final String POSTING_COMMAND_REPRESENTATION = "->";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(POSTING_COMMAND_REPRESENTATION);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(POSTING_COMMAND_REPRESENTATION);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + POSTING_COMMAND_REPRESENTATION.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public void apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( ! isKnown(commandLine.getLine()) ) {
            return;
        }

        String userName = commandLine.getArgBeforeCommandName(POSTING_COMMAND_REPRESENTATION);
        String userMessage = commandLine.getArgAfterCommandName(POSTING_COMMAND_REPRESENTATION);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);
            user.addMessage(new Message(userMessage, new Date()));
        } else {
            User user = new User(userName);
            user.addMessage(new Message(userMessage, new Date()));
            socialNetwork.addUser(user);
        }
    }
}
