package com.manriqueweb.mwepgmedia.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeSerializer extends StdSerializer<DateTime> {
    //region private variables
    private static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
    //endregion

    protected DateTimeSerializer() {
        super(DateTime.class);
    }

    protected DateTimeSerializer(Class<DateTime> t) {
        super(t);
    }

    protected DateTimeSerializer(JavaType type) {
        super(type);
    }

    protected DateTimeSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected DateTimeSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString(API_DATE_FORMAT));
    }
}
