package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

import java.util.Date;
import java.util.List;

public class ReadingCommand implements Command, Recognizable {

    @Override
    public boolean isKnown(String line) {
        String[] args = line.split(" ");

        if( args.length != 1 ) {
            return false;
        }

        boolean isArgEmpty = "".equals(line.trim());

        return !isArgEmpty;
    }

    @Override
    public String apply(SocialNetwork socialNetwork, CommandLine commandLine) {
        if( socialNetwork.hasUser(commandLine.getLine().trim()) ) {
            User user = socialNetwork.getUser(commandLine.getLine().trim());

            return getAllFormatted(user.getAllMessage());
        }

        return "";
    }

    private String getFormatted(Message message) {
        TimeDistance timeDistance = new TimeDistance(new Date().getTime() - message.getTimestamp().getTime());

        return message.getContent() + " " + "("+ timeDistance.getDistance() + " " + timeDistance.getMeasure() + " ago)";
    }

    public String getAllFormatted(List<Message> messages) {
        if( messages.isEmpty() ) {
            return "";
        }

        if( messages.size() == 1 ) {
            return getFormatted(messages.get(0));
        }

        StringBuffer buff = new StringBuffer() ;

        for( int i = 0; i < messages.size() - 1; i ++ ) {
            buff.append(getFormatted(messages.get(i))).append(System.lineSeparator());
        }

        buff.append(getFormatted(messages.get(messages.size() - 1)));

        return buff.toString();
    }
}
