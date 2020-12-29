package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SocialNetwork {
    private List<User> users = new ArrayList<>();

    public SocialNetwork() {
    }

    public String elaborate(CommandLine commandLine) {
        return "";
    }

    public boolean addUser(User user) {
        Objects.requireNonNull(user);

        return this.users.add(user);
    }

    public boolean hasUser(String userName) {
        for(User user: this.users) {
            if( userName.equals(user.getName()) ) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName) {
        for(User user: this.users) {
            if( userName.equals(user.getName()) ) {
                return user;
            }
        }
        throw new IllegalStateException("no user with name: " + userName);
    }

    public List<User> getAllUser() {
        return this.users;
    }
}
