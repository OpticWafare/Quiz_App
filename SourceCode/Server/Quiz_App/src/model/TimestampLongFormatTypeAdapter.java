package model;

import java.io.IOException;
import java.sql.Timestamp;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TimestampLongFormatTypeAdapter extends TypeAdapter<Timestamp> {

    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        out.value(value.getTime());
    }

    @Override
    public Timestamp read(JsonReader in) throws IOException {
        return new Timestamp(in.nextLong());
    }

}