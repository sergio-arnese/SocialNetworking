package it.sergio.arnese.kata.socialnetworking.test.unit;

import it.sergio.arnese.kata.socialnetworking.domain.MeasureUnitName;
import it.sergio.arnese.kata.socialnetworking.domain.command.MeasureMapper;
import it.sergio.arnese.kata.socialnetworking.domain.configuration.MeasureMapperConf;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasureMapperTest {
    @Test
    public void testUnknownMapping() {
        MeasureMapper measureMapper = new MeasureMapperConf().getMeasureMapper();

        String mappedValue = measureMapper.getMappedValue("Century");

        assertEquals(MeasureMapper.UNKNOWN_MAPPING, mappedValue);
    }

    @Test
    public void testDaysMapping() {
        MeasureMapper measureMapper = new MeasureMapperConf().getMeasureMapper();

        String mappedValue = measureMapper.getMappedValue(TimeDistance.DAYS);

        assertEquals(MeasureUnitName.DAYS, mappedValue);
    }

    @Test
    public void testHoursMapping() {
        MeasureMapper measureMapper = new MeasureMapperConf().getMeasureMapper();

        String mappedValue = measureMapper.getMappedValue(TimeDistance.HOURS);

        assertEquals(MeasureUnitName.HOURS, mappedValue);
    }

    @Test
    public void testMinutesMapping() {
        MeasureMapper measureMapper = new MeasureMapperConf().getMeasureMapper();

        String mappedValue = measureMapper.getMappedValue(TimeDistance.MINUTES);

        assertEquals(MeasureUnitName.MINUTES, mappedValue);
    }

    @Test
    public void testSecondsMapping() {
        MeasureMapper measureMapper = new MeasureMapperConf().getMeasureMapper();

        String mappedValue = measureMapper.getMappedValue(TimeDistance.SECONDS);

        assertEquals(MeasureUnitName.SECONDS, mappedValue);
    }

}
