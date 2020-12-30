package it.sergio.arnese.kata.socialnetworking.commandline;

public class ReadingCommand implements Recognizable {

    @Override
    public boolean isKnown(String line) {
        String[] args = line.split(" ");

        if( args.length != 1 ) {
            return false;
        }

        boolean isArgEmpty = "".equals(line.trim());

        return !isArgEmpty;
    }
}
