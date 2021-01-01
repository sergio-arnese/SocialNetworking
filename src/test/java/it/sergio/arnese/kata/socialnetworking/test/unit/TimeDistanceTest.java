package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeDistanceTest {

    private long msInAsecond = 1000;
    private long secondsInaMinute = 60;
    private long minutesInAnHour = 60;
    private long hoursInADay = 24;

    private long getMsInAsecond() {
        return this.msInAsecond;
    }

    private long getMsInAMinute() {
        return this.secondsInaMinute * getMsInAsecond();
    }

    private long getMsInAnHour() {
        return this.minutesInAnHour * getMsInAMinute();
    }

    private long getMsInADay() {
        return this.hoursInADay * getMsInAnHour();
    }

    @Test
    public void testOneSecond() {
        long oneSecond = 1;

        TimeDistance timeDistance = new TimeDistance(oneSecond * getMsInAsecond());

        assertEquals(timeDistance.getDistance(), oneSecond);
        assertEquals(timeDistance.getMeasure(), TimeDistance.SECONDS);
    }

    @Test
    public void testTwoSeconds() {
        long twoSeconds = 2;

        TimeDistance timeDistance = new TimeDistance(twoSeconds * getMsInAsecond());

        assertEquals(timeDistance.getDistance(), twoSeconds);
        assertEquals(timeDistance.getMeasure(), TimeDistance.SECONDS);
    }

    @Test
    public void testOneMinute() {
        long oneMinute = 1;

        TimeDistance timeDistance = new TimeDistance(oneMinute * getMsInAMinute());

        assertEquals(timeDistance.getDistance(), oneMinute);
        assertEquals(timeDistance.getMeasure(), TimeDistance.MINUTES);
    }

    @Test
    public void testTenMinutes() {
        long tenMinutes = 10;

        TimeDistance timeDistance = new TimeDistance(tenMinutes * getMsInAMinute());

        assertEquals(timeDistance.getDistance(), tenMinutes);
        assertEquals(timeDistance.getMeasure(), TimeDistance.MINUTES);
    }

    @Test
    public void testOneHour() {
        long oneHour = 1;

        TimeDistance timeDistance = new TimeDistance(oneHour * getMsInAnHour());

        assertEquals(timeDistance.getDistance(), oneHour);
        assertEquals(timeDistance.getMeasure(), TimeDistance.HOURS);
    }

    @Test
    public void testThreeHours() {
        long threeHours = 3;

        TimeDistance timeDistance = new TimeDistance(threeHours * getMsInAnHour());

        assertEquals(timeDistance.getDistance(), threeHours);
        assertEquals(timeDistance.getMeasure(), TimeDistance.HOURS);
    }

    @Test
    public void testOneDay() {
        long oneDay = 1;

        TimeDistance timeDistance = new TimeDistance(oneDay * getMsInADay());

        assertEquals(timeDistance.getDistance(), oneDay);
        assertEquals(timeDistance.getMeasure(), TimeDistance.DAYS);
    }

    @Test
    public void testFiftyDays() {
        long fiftyDays = 50;

        TimeDistance timeDistance = new TimeDistance(fiftyDays * getMsInADay());

        assertEquals(timeDistance.getDistance(), fiftyDays);
        assertEquals(timeDistance.getMeasure(), TimeDistance.DAYS);
    }

}

