package com.github.opticwafare.quiz_app.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class TimestampLongFormatTypeAdapter extends TypeAdapter<Timestamp> {

    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        if(value == null) {
            out.value(-1);
        }
        else {
            out.value(value.getTime());
        }
    }

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