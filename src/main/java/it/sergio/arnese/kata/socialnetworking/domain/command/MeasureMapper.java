package it.sergio.arnese.kata.socialnetworking.domain.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MeasureMapper {
    public static final String UNKNOWN_MAPPING = "unknown";

    private final Map<String, String> mappedValues = new HashMap<>();

    public void addMapping(String key, String value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        this.mappedValues.put(key, value);
    }

    public String getMappedValue(String key) {
        Objects.requireNonNull(key);

        if( !this.mappedValues.containsKey(key) ) {
            return UNKNOWN_MAPPING;
        }

        return this.mappedValues.get(key);
    }
}
