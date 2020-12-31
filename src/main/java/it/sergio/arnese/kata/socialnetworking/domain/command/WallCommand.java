package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

import java.util.Date;
import java.util.List;

public class WallCommand implements Command, Recognizable {
    private final String WALL_COMMAND = "wall";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(WALL_COMMAND);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(WALL_COMMAND);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + WALL_COMMAND.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && isThirdArgEmpty);
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        String userName = commandLine.getArgBeforeCommandName(WALL_COMMAND);

        if( socialNetwork.hasUser(userName) ) {
            User user = socialNetwork.getUser(userName);

            return prepareOutput(user);
        }

        return "";
    }

    private String prepareOutput(User user) {
        StringBuffer buff = new StringBuffer();

        List<User> allFollowedUser = user.getAllFollowedUser();

        if( ! allFollowedUser.isEmpty() ) {
            for(int i = allFollowedUser.size() - 1; i > 0; i--) {
                User followedUser = allFollowedUser.get(i);

                buff.append(getAllFormatted(followedUser.getName(), followedUser.getAllMessage())).append(System.lineSeparator());
            }

            buff.append(getAllFormatted(allFollowedUser.get(0).getName(), allFollowedUser.get(0).getAllMessage()));
        }

        return ( buff.length() != 0 ? getAllFormatted(user.getName(), user.getAllMessage()) + System.lineSeparator() + buff.toString() : getAllFormatted(user.getName(), user.getAllMessage()));
    }

    private String getFormatted(String userName, Message message) {
        TimeDistance timeDistance = new TimeDistance(new Date().getTime() - message.getTimestamp().getTime());

        return userName + " - " + message.getContent() + " " + "("+ timeDistance.getDistance() + " " + timeDistance.getMeasure() + " ago)";
    }

    public String getAllFormatted(String userName, List<Message> messages) {
        if( messages.isEmpty() ) {
            return "";
        }

        if( messages.size() == 1 ) {
            return getFormatted(userName, messages.get(0));
        }

        StringBuffer buff = new StringBuffer() ;

        for( int i = 0; i < messages.size() - 1; i ++ ) {
            buff.append(getFormatted(userName, messages.get(i))).append(System.lineSeparator());
        }

        buff.append(getFormatted(userName, messages.get(messages.size() - 1)));

        return buff.toString();
    }
}
