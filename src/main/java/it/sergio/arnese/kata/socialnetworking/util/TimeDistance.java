package it.sergio.arnese.kata.socialnetworking.util;

import java.util.concurrent.TimeUnit;

public class TimeDistance {
    private final String DAYS_QUANTITY = "days";
    private final String HOURS_QUANTITY = "hours";
    private final String MINUTES_QUANTITY = "minutes";
    private final String SECONDS_QUANTITY = "seconds";

    public String getAsString(long ms) {
        long numSeconds = TimeUnit.MILLISECONDS.toSeconds(ms);
        long numMinutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        long numHours = TimeUnit.MILLISECONDS.toHours(ms);
        long numDays = TimeUnit.MILLISECONDS.toDays(ms);

        long valueToReturn;
        String quantity;

        if( numDays > 0 ) {
            valueToReturn = numDays;
            quantity = DAYS_QUANTITY;
        } else if (numHours > 0) {
            valueToReturn = numHours;
            quantity = HOURS_QUANTITY;
        } else if (numMinutes > 0) {
            valueToReturn = numMinutes;
            quantity = MINUTES_QUANTITY;
        } else {
            valueToReturn = numSeconds;
            quantity = SECONDS_QUANTITY;
        }

        return valueToReturn + " " + quantity;
    }
}
