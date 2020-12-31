package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.*;

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

    public List<Message> getAllMessage() {
        return this.messages;
    }

    public List<User> getAllFollowedUser() {
        return this.followedUsers;
    }
}
