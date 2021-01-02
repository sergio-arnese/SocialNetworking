package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandSN;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SocialNetwork {
    private final CommandRecognizer<CommandSN> commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
    private CommandLine commandLine;
    private List<User> users = new ArrayList<>();

    public SocialNetwork() {
    }

    public CommandLine getCommandLine() {
        return this.commandLine;
    }


    public String elaborate(CommandLine commandLine) {
        this.commandLine = Objects.requireNonNull(commandLine);

        Command command = this.commandRecognizer.recognize(this.commandLine);

        return command.apply(this);
    }

    public boolean addUser(User user) {
        Objects.requireNonNull(user);

        return this.users.add(user);
    }

    public boolean hasUser(String userName) {
        Objects.requireNonNull(userName);

        for(User user: this.users) {
            if( userName.equals(user.getName()) ) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName) {
        Objects.requireNonNull(userName);

        if( !hasUser(userName) ) {
            throw new IllegalStateException("no user with name: " + userName);
        }

        User userToReturn = null;

        for(User user: this.users) {
            if( userName.equals(user.getName()) ) {
                userToReturn = user;
            }
        }

        return userToReturn ;
    }

    public List<User> getAllUser() {
        return new ArrayList<>(this.users);
    }

    public void addFollowedUser(String userName, String followedUserName) {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(followedUserName);

        if( !this.hasUser(userName) || !this.hasUser(followedUserName) ) {
            return;
        }

        User user = this.getUser(userName);
        User followedUser = this.getUser(followedUserName);

        user.addFollowed(followedUser);
    }

    public void addMessageToUser(String userName, String userMessage) {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(userMessage);

        if( !this.hasUser(userName) ) {
            return;
        }

        User user = this.getUser(userName);
        user.addMessage(new Message(userMessage, new Date()));
    }
}
