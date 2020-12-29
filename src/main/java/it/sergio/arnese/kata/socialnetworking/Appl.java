package it.sergio.arnese.kata.socialnetworking;

import it.sergio.arnese.kata.socialnetworking.domain.CommandLine;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public class Appl {

    public static void main(String[] args) {
        new Appl().go();
    }

    public void go() {
        Console console = new ConsoleReal();
        SocialNetwork socialNetwork = new SocialNetwork();

        while(true) {
            String elaborationOutput = socialNetwork.elaborate(new CommandLine(console.getLine()));

            if( hasElaborationOutput(elaborationOutput) ) {
                console.setOutput(elaborationOutput);
            }
        }
    }

    private boolean hasElaborationOutput(String output) {
        return !"".equals(output);
    }
}
