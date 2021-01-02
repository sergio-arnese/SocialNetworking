package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.Message;
import it.sergio.arnese.kata.socialnetworking.util.TimeDistance;

import java.util.Date;
import java.util.List;

public class ReadingOutputFormatter extends CommandOutputFormatter {

    private MeasureMapper mapperConf = new MeasureMapperConf().getMeasureMapper();

    public String getReadingOutput(List<Message> messages) {
        return getAllMessageFormatted(messages);
    }

    @Override
    protected String getFormatted(String userName, Message message) {
        TimeDistance timeDistance = new TimeDistance(new Date().getTime() - message.getTimestamp().getTime());

        return message.getContent() + " " + "("+ timeDistance.getDistance() + " " + this.mapperConf.getMappedValue(timeDistance.getMeasure()) + " ago)";
    }
}
