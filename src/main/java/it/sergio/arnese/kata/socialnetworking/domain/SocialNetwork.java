package it.sergio.arnese.kata.socialnetworking.domain;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandSN;

import java.util.ArrayList;
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
        return this.users;
    }
}
