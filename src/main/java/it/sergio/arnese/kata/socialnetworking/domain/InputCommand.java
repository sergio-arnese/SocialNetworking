package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.Objects;

public class InputCommand {
    public static String POST = "->";
    public static String READ = "";
    public static String FOLLOWS = "follows";
    public static String WALL = "wall";

    public static String UNKNOWN = "";

//    public static InputCommand POST = new InputCommand("->");
//    public static InputCommand READ = new InputCommand("");
//    public static InputCommand FOLLOWS = new InputCommand("follows");
//    public static InputCommand WALL = new InputCommand("wall");
//
//    public static InputCommand UNKNOWN = new InputCommand("");


    private String name;

    private InputCommand(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public int length() {
        return this.name.length();
    }
}
