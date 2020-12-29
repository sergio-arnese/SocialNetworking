package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private List<Message> messages = new ArrayList<>();
    private List<User> followedUsers = new ArrayList<>();

    public User(String name) {
        this.name = Objects.requireNonNull(name);

        if( "".equals(name) ) {
            throw new IllegalArgumentException("name must be not empty");
        }
    }

    public boolean addMessage(Message message) {
        Objects.requireNonNull(message);

        return this.messages.add(message);
    }

    public boolean addFollowed(User user) {
        Objects.requireNonNull(user);

        if( user.getName().equals(this.name) ) {
            return false;
        }

        return this.followedUsers.add(user);
    }

    public String getName() {
        return this.name;
    }

    public String writeAll() {
        // TODO mi suona strano
        String allMessages = getAllMessageWithTimestamp();
        String allFollowed = getAllFollowedUserMessageWithTimestamp();

        return allMessages + (!allFollowed.isEmpty() ?  System.lineSeparator() : "") + allFollowed;
    }

    private boolean hasMessages() {
        return !this.messages.isEmpty();
    }

    public String getAllMessageWithTimestamp() {
        if( hasMessages() ) {
            return allMessage();
        } else {
            return noMessages();
        }
    }

    private String noMessages() {
        return "";
    }

    private String allMessage() {
        StringBuffer buff = new StringBuffer() ;

        for( int i = 0; i < this.messages.size() - 1; i ++ ) {
            buff.append(this.name + " - " + this.messages.get(i).getContentWithTimestamp()).append(System.lineSeparator());
        }

        buff.append(this.name + " - " + this.messages.get(this.messages.size() - 1).getContentWithTimestamp());

        return buff.toString();
    }


    public String getAllFollowedUserMessageWithTimestamp() {
        StringBuffer buff = new StringBuffer() ;

        for(User user: this.followedUsers) {
            buff.append(user.getAllMessageWithTimestamp());
        }
        return buff.toString();
    }
}
