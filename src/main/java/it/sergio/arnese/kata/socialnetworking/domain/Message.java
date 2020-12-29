package it.sergio.arnese.kata.socialnetworking.domain;

import java.util.Date;
import java.util.Objects;

public class Message {
    private String content;
    private Date timestamp;

    public Message(String content, Date timestamp) {
        this.content = Objects.requireNonNull(content);

        if("".equals(content)) {
            throw new IllegalArgumentException("content must be not empty");
        }

        this.timestamp = Objects.requireNonNull(timestamp);
    }

    public String getContentWithTimestamp() {
        return getContent() + " " + "("+ getTimeDistance() +" ago )";
    }

    private String getTimeDistance() {
        Date now = new Date();
        // TimeUnit.MILLISECONDS.toSeconds()
        return " " + (now.getTime() - getTimestamp().getTime());
    }

    public String getContent() {
        return this.content;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }
}
