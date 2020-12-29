package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.Objects;

public class CommandLine {
    private String line;
    private String userName;
    private String followedUserName;
    private String message;
    private String userCommand;

    public CommandLine(String line) {
        this.line = Objects.requireNonNull(line);

        init();
    }

    private void init() {
        if( lineContains(InputCommand.FOLLOWS) ) {
            this.userName = this.line.substring(0,this.line.indexOf(InputCommand.FOLLOWS)).trim();
            this.userCommand = InputCommand.FOLLOWS;
            this.followedUserName = this.line.substring(this.line.indexOf(InputCommand.FOLLOWS) + InputCommand.FOLLOWS.length()).trim();
            this.message = "";
        } else if(lineContains(InputCommand.WALL)) {
            this.userName = this.line.substring(0,this.line.indexOf(InputCommand.WALL)).trim();
            this.userCommand = InputCommand.WALL;
            this.followedUserName = "";
            this.message = "";
        } else if(lineContains(InputCommand.POST)) {
            this.userName = this.line.substring(0,this.line.indexOf(InputCommand.POST)).trim();
            this.userCommand = InputCommand.POST;
            this.followedUserName = "";
            this.message = this.line.substring(this.line.indexOf(InputCommand.POST) + InputCommand.POST.length()).trim();
        } else {
            this.userName = this.line.trim();
            this.userCommand = InputCommand.READ;
            this.followedUserName = "";
            this.message = "";
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserMessage() {
        return this.message;
    }

    public String getUserCommand() {
        return this.userCommand;
    }

    private boolean lineContains(String commandStr) {
        return this.line.contains(commandStr);
    }

    public String getFollowedUserName() {
        return this.followedUserName;
    }
}
