package it.sergio.arnese.kata.socialnetworking.util;

import java.util.concurrent.TimeUnit;

public class TimeDistance {
    public static final String DAYS = "DD";
    public static final String HOURS = "HH";
    public static final String MINUTES = "MM";
    public static final String SECONDS = "SS";

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
            measure = DAYS;
        } else if (numHours > 0) {
            distance = numHours;
            measure = HOURS;
        } else if (numMinutes > 0) {
            distance = numMinutes;
            measure = MINUTES;
        } else {
            distance = numSeconds;
            measure = SECONDS;
        }
    }

    public long getDistance() {
        return this.distance;
    }

    public String getMeasure() {
        return this.measure;
    }
}
