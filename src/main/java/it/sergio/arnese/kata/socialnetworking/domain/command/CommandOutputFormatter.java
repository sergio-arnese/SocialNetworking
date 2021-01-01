package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;

import java.util.List;

public abstract class CommandOutputFormatter {

    public String getAllMessageFormatted(String userName, List<Message> messages) {
        if( messages.isEmpty() ) {
            return "";
        }

        StringBuffer buff = new StringBuffer();

        for( int i = 0; i < messages.size() - 1; i ++ ) {
            buff.append(getFormatted(userName, messages.get(i))).append(System.lineSeparator());
        }

        buff.append(getFormatted(userName, messages.get(messages.size() - 1)));

        return buff.toString();
    }

    public String getAllMessageFormatted(List<Message> messages) {
        return getAllMessageFormatted("", messages);
    }

    protected abstract String getFormatted(String userName, Message message);
}
