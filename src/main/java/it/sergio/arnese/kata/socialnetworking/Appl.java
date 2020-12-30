package it.sergio.arnese.kata.socialnetworking;

import it.sergio.arnese.kata.socialnetworking.domain.command.Command;
import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;

public class Appl {

    public static void main(String[] args) {
        new Appl().run();
    }

    public void run() {
        Console console = new ConsoleReal();
        CommandRecognizer commandRecognizer = new CommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        while(true) {
            String line = console.getLine();
            String elaborationOutput = socialNetwork.elaborate((Command) commandRecognizer.recognize(line), new CommandLine(line));

            if( hasElaborationOutput(elaborationOutput) ) {
                console.setOutput(elaborationOutput);
            }
        }
    }

    private boolean hasElaborationOutput(String output) {
        return !"".equals(output);
    }
}
