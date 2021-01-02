package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.command.MeasureUnitName;
import it.sergio.arnese.kata.socialnetworking.domain.command.MeasureMapper;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

public class MeasureMapperConf {

    public MeasureMapper getMeasureMapper() {
        MeasureMapper measureMapper = new MeasureMapper();

        measureMapper.addMapping(TimeDistance.DAYS, MeasureUnitName.DAYS);
        measureMapper.addMapping(TimeDistance.HOURS, MeasureUnitName.HOURS);
        measureMapper.addMapping(TimeDistance.MINUTES, MeasureUnitName.MINUTES);
        measureMapper.addMapping(TimeDistance.SECONDS, MeasureUnitName.SECONDS);

        return measureMapper;
    }
}
