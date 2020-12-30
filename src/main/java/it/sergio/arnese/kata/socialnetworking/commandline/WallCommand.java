package it.sergio.arnese.kata.socialnetworking.commandline;

public class WallCommand implements Recognizable {
    private final String WALL_COMMAND_REPRESENTATION = "wall";

    @Override
    public boolean isKnown(String line) {
        if(line == null) {
            return false;
        }

        boolean isExpectedCommandContained = line.contains(WALL_COMMAND_REPRESENTATION);

        if( !isExpectedCommandContained ) {
            return false;
        }

        int indexOfExpectedCommand = line.indexOf(WALL_COMMAND_REPRESENTATION);

        String firstArg = line.substring(0,indexOfExpectedCommand).trim();
        String thirdArg = line.substring(indexOfExpectedCommand + WALL_COMMAND_REPRESENTATION.length()).trim();

        boolean isFirstArgEmpty = "".equals(firstArg);
        boolean isThirdArgEmpty = "".equals(thirdArg);

        return (!isFirstArgEmpty && isThirdArgEmpty);
    }
}
