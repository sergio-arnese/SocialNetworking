package it.sergio.arnese.kata.socialnetworking.util;

import java.util.concurrent.TimeUnit;

public class TimeDistance {

    public String getAsString(long ms) {
        long numSeconds = TimeUnit.MILLISECONDS.toSeconds(ms);
        long numMinutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        long numHours = TimeUnit.MILLISECONDS.toHours(ms);
        long numDays = TimeUnit.MILLISECONDS.toDays(ms);

        long valueToReturn;
        String quantity;

        if( numDays > 0 ) {
            valueToReturn = numDays;
            quantity = "days";
        } else if (numHours > 0) {
            valueToReturn = numHours;
            quantity = "hours";
        } else if (numMinutes > 0) {
            valueToReturn = numMinutes;
            quantity = "minutes";
        } else {
            valueToReturn = numSeconds;
            quantity = "seconds";
        }

        return valueToReturn + " " + quantity;
    }
}
