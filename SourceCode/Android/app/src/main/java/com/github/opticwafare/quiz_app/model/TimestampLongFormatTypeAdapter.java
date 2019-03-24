package com.github.opticwafare.quiz_app.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Für GSON
 *
 * Transformation von Timestamp von/zu JSON
 */
public class TimestampLongFormatTypeAdapter extends TypeAdapter<Timestamp> {

    /**
     * Wie soll ein Timestamp aus JSON geschrieben werden?
     */
    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        if(value == null) {
            out.value(-1);
        }
        else {
            out.value(value.getTime());
        }
    }

    /**
     * Wie soll ein Timestamp von JSON in ein Objekt verwandelt werden?
     * @param in
     * @return
     * @throws IOException
     */
    @Override
    public Timestamp read(JsonReader in) throws IOException {
        long value = in.nextLong();
        if(value < 0) {
            return null;
        }
        else {
            return new Timestamp(value);
        }
    }
}