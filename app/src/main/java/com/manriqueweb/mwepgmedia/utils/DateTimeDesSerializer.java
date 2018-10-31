package com.manriqueweb.mwepgmedia.utils;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class DateTimeDesSerializer extends StdScalarDeserializer<DateTime> {
    //region private variables
    private static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
    //endregion

    public DateTimeDesSerializer() {
        super(DateTime.class);
    }

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(API_DATE_FORMAT);
        return DateTime.parse(p.getText().trim(), formatter);
    }
}
