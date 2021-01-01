package it.sergio.arnese.kata.socialnetworking;

import it.sergio.arnese.kata.socialnetworking.domain.CommandRecognizer;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.command.CommandLine;
import it.sergio.arnese.kata.socialnetworking.domain.configuration.CommandRecognizerConf;

public class Appl {

    public static void main(String[] args) {
        new Appl().run();
    }

    public void run() {
        Console console = new ConsoleReal();
        CommandRecognizer commandRecognizer = new CommandRecognizerConf().getCommandRecognizer();
        SocialNetwork socialNetwork = new SocialNetwork();

        while(true) {
            String line = console.getLine();
            String elaborationOutput = socialNetwork.elaborate(commandRecognizer.recognize(line), new CommandLine(line));
            console.setOutput(elaborationOutput);
        }
    }
}
