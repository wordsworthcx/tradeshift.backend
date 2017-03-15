package tradeshift.foodfacility.translator.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import org.joda.time.DateTime;

/**
 * @author xuch.
 */
public class DateTimeTypeAdapter extends TypeAdapter<DateTime> {
    private String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public void write(JsonWriter out, DateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            // Convert DateTime to string at format "yyyy-MM-dd'T'HH:mm:ss.SSS" by default.
            out.value(value.toString());
        }
    }

    @Override
    public DateTime read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        //return new SimpleDateFormat(DATE_FORMAT).parse(in.nextString());
        // Parse the string at format "yyyy-MM-dd'T'HH:mm:ss.SSS" by default.
        return DateTime.parse(in.nextString());
    }
}

























