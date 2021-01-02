package it.sergio.arnese.kata.socialnetworking;

import it.sergio.arnese.kata.socialnetworking.boundary.Console;
import it.sergio.arnese.kata.socialnetworking.boundary.ConsoleReal;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;

public class Appl {

    public static void main(String[] args) {
        new Appl().run();
    }

    public void run() {
        Console console = new ConsoleReal();
        SocialNetwork socialNetwork = new SocialNetwork();

        while(true) {
            CommandLine commandLine = new CommandLine(console.getLine());
            console.setOutput(socialNetwork.elaborate(commandLine));
        }
    }
}
