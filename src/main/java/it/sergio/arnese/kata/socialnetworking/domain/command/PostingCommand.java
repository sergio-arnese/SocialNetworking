package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

import java.util.Date;

public class PostingCommand implements Command, Recognizable {
    private final String POSTING_COMMAND = "->";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(POSTING_COMMAND);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(POSTING_COMMAND);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + POSTING_COMMAND.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && !isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( ! isKnown(commandLine.getLine()) ) {
            return "";
        }

        String userName = commandLine.getArgBeforeCommandName(POSTING_COMMAND);
        String userMessage = commandLine.getArgAfterCommandName(POSTING_COMMAND);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);
            user.addMessage(new Message(userMessage, new Date()));
        } else {
            User user = new User(userName);
            user.addMessage(new Message(userMessage, new Date()));
            socialNetwork.addUser(user);
        }

        return "";
    }
}
