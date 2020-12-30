package it.sergio.arnese.kata.socialnetworking.commandline;

import it.sergio.arnese.kata.socialnetworking.domain.SNCommand;
import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;
import it.sergio.arnese.kata.socialnetworking.domain.User;

public class ReadingCommand extends CommandWithOutput implements SNCommand {

    @Override
    public boolean isKnown(String line) {
        String[] args = line.split(" ");

        if( args.length != 1 ) {
            return false;
        }

        boolean isArgEmpty = "".equals(line.trim());

        return !isArgEmpty;
    }

    @Override
    public void apply(SocialNetwork socialNetwork, String line) {
        cleanOutput();

        if( socialNetwork.hasUser(getUserName(line)) ) {
            User user = socialNetwork.getUser(getUserName(line));

            setOutput(user.getAllMessageWithTimestamp());
        }
    }

    private String getUserName(String line) {
        return line.trim();
    }
}
