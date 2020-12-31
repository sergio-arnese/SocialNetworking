package it.sergio.arnese.kata.socialnetworking;

import java.util.Scanner;

public class ConsoleReal implements Console {
    private Scanner scanner = new Scanner(System.in);

    protected void prompt() {
        System.out.print("> ");
    }

    @Override
    public String getLine() {
        prompt();
        return nextLine();
    }

    @Override
    public void setOutput(String output) {
        if( !"".equals(output) ) {
            System.out.println(output);
        }
    }

    private String nextLine(){
        return scanner.nextLine();
    }
}
