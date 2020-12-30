package it.sergio.arnese.kata.socialnetworking.commandline;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetworkCommand;
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
    public void apply(SocialNetwork socialNetwork, String line) {
        if( socialNetwork.hasUser(getUserName(line)) ) {
            User user = socialNetwork.getUser(getUserName(line));
            user.addMessage(new Message(getUserMessage(line), new Date()));
        } else {
            User user = new User(getUserName(line));
            user.addMessage(new Message(getUserMessage(line), new Date()));
            socialNetwork.addUser(user);
        }
    }

    private String getUserMessage(String line) {
        int indexOfExpectedCommand = line.indexOf(POSTING_COMMAND_REPRESENTATION);
        return line.substring(indexOfExpectedCommand + POSTING_COMMAND_REPRESENTATION.length()).trim();
    }

    private String getUserName(String line) {
        int indexOfExpectedCommand = line.indexOf(POSTING_COMMAND_REPRESENTATION);
        return line.substring(0,indexOfExpectedCommand).trim();
    }
}
