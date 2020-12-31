package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

import java.util.Date;
import java.util.List;

public class WallOutputFormatter extends CommandOutputFormatter {

    public String getWallOutput(User user) {
        String followedMessages = getAllFollowedUserMessageFormatted(user.getAllFollowedUser());

        String userMessages = getAllMessageFormatted(user.getName(), user.getAllMessage());

        return ( followedMessages.length() != 0 ? userMessages + System.lineSeparator() + followedMessages : userMessages );
    }

    public String getAllFollowedUserMessageFormatted(List<User> users) {
        if( users.isEmpty() ) {
            return "";
        }

        StringBuffer buff = new StringBuffer();

        for(int i = users.size() - 1; i > 0; i--) {
            User user = users.get(i);

            buff.append(getAllMessageFormatted(user.getName(), user.getAllMessage())).append(System.lineSeparator());
        }

        buff.append(getAllMessageFormatted(users.get(0).getName(), users.get(0).getAllMessage()));


        return buff.toString();
    }

    public String getFormatted(String userName, Message message) {
        TimeDistance timeDistance = new TimeDistance(new Date().getTime() - message.getTimestamp().getTime());

        return userName + " - " + message.getContent() + " " + "("+ timeDistance.getDistance() + " " + timeDistance.getMeasure() + " ago)";
    }
}
