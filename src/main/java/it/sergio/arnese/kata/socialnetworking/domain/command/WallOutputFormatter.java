package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class WallOutputFormatter extends CommandOutputFormatter {

    private MeasureMapper mapperConf = new MeasureMapperConf().getMeasureMapper();

    public String getWallOutput(User user) {
        String followedMessages = getAllFollowedUserMessageFormatted(user.getAllFollowedUser());

        String userMessages = getAllMessageFormatted(user.getName(), user.getAllMessage());

        return ( followedMessages.length() != 0 ? userMessages + System.lineSeparator() + followedMessages : userMessages );
    }

    private String getAllFollowedUserMessageFormatted(List<User> users) {
        if( users.isEmpty() ) {
            return "";
        }

        Collections.reverse(users);

        StringBuffer buff = new StringBuffer();

        for(int i = 0; i < users.size() - 1; i++) {
            User user = users.get(i);

            buff.append(getAllMessageFormatted(user.getName(), user.getAllMessage())).append(System.lineSeparator());
        }

        buff.append(getAllMessageFormatted(users.get(users.size() - 1).getName(), users.get(users.size() - 1).getAllMessage()));


        return buff.toString();
    }

    @Override
    protected String getMessageFormatted(String userName, Message message) {
        TimeDistance timeDistance = new TimeDistance(new Date().getTime() - message.getTimestamp().getTime());

        return userName + " - " + message.getContent() + " " + "("+ timeDistance.getDistance() + " " + this.mapperConf.getMappedValue(timeDistance.getMeasure()) + " ago)";
    }
}
