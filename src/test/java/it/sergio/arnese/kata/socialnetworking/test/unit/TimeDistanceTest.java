package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeDistanceTest {

    private long getMsInOneSecond() {
        return 1000;
    }

    private long getMsInOneMinute() {
        long secondsInaMinute = 60;
        return secondsInaMinute * getMsInOneSecond();
    }

    private long getMsInOneHour() {
        long minutesInAnHour = 60;
        return minutesInAnHour * getMsInOneMinute();
    }

    private long getMsInOneDay() {
        long hoursInADay = 24;
        return hoursInADay * getMsInOneHour();
    }

    @Test
    public void testOneSecond() {
        long oneSecond = 1;

        TimeDistance timeDistance = new TimeDistance(oneSecond * getMsInOneSecond());

        assertEquals(timeDistance.getDistance(), oneSecond);
        assertEquals(timeDistance.getMeasure(), TimeDistance.SECONDS);
    }

    @Test
    public void testTwoSeconds() {
        long twoSeconds = 2;

        TimeDistance timeDistance = new TimeDistance(twoSeconds * getMsInOneSecond());

        assertEquals(timeDistance.getDistance(), twoSeconds);
        assertEquals(timeDistance.getMeasure(), TimeDistance.SECONDS);
    }

    @Test
    public void testOneMinute() {
        long oneMinute = 1;

        TimeDistance timeDistance = new TimeDistance(oneMinute * getMsInOneMinute());

        assertEquals(timeDistance.getDistance(), oneMinute);
        assertEquals(timeDistance.getMeasure(), TimeDistance.MINUTES);
    }

    @Test
    public void testTenMinutes() {
        long tenMinutes = 10;

        TimeDistance timeDistance = new TimeDistance(tenMinutes * getMsInOneMinute());

        assertEquals(timeDistance.getDistance(), tenMinutes);
        assertEquals(timeDistance.getMeasure(), TimeDistance.MINUTES);
    }

    @Test
    public void testOneHour() {
        long oneHour = 1;

        TimeDistance timeDistance = new TimeDistance(oneHour * getMsInOneHour());

        assertEquals(timeDistance.getDistance(), oneHour);
        assertEquals(timeDistance.getMeasure(), TimeDistance.HOURS);
    }

    @Test
    public void testThreeHours() {
        long threeHours = 3;

        TimeDistance timeDistance = new TimeDistance(threeHours * getMsInOneHour());

        assertEquals(timeDistance.getDistance(), threeHours);
        assertEquals(timeDistance.getMeasure(), TimeDistance.HOURS);
    }

    @Test
    public void testOneDay() {
        long oneDay = 1;

        TimeDistance timeDistance = new TimeDistance(oneDay * getMsInOneDay());

        assertEquals(timeDistance.getDistance(), oneDay);
        assertEquals(timeDistance.getMeasure(), TimeDistance.DAYS);
    }

    @Test
    public void testFiftyDays() {
        long fiftyDays = 50;

        TimeDistance timeDistance = new TimeDistance(fiftyDays * getMsInOneDay());

        assertEquals(timeDistance.getDistance(), fiftyDays);
        assertEquals(timeDistance.getMeasure(), TimeDistance.DAYS);
    }

}

