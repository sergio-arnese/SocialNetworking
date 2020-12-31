package it.sergio.arnese.kata.socialnetworking.util;

import java.util.concurrent.TimeUnit;

public class TimeDistance {
    private final String DAYS_QUANTITY = "DD";
    private final String HOURS_QUANTITY = "HH";
    private final String MINUTES_QUANTITY = "MM";
    private final String SECONDS_QUANTITY = "SS";

    private long distance;
    private String measure;

    public TimeDistance(long ms) {
        init(ms);
    }

    private void init(long ms) {
        long numSeconds = TimeUnit.MILLISECONDS.toSeconds(ms);
        long numMinutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        long numHours = TimeUnit.MILLISECONDS.toHours(ms);
        long numDays = TimeUnit.MILLISECONDS.toDays(ms);

        if( numDays > 0 ) {
            distance = numDays;
            measure = DAYS_QUANTITY;
        } else if (numHours > 0) {
            distance = numHours;
            measure = HOURS_QUANTITY;
        } else if (numMinutes > 0) {
            distance = numMinutes;
            measure = MINUTES_QUANTITY;
        } else {
            distance = numSeconds;
            measure = SECONDS_QUANTITY;
        }
    }

    public long getDistance() {
        return this.distance;
    }

    public String getMeasure() {
        return this.measure;
    }
}
