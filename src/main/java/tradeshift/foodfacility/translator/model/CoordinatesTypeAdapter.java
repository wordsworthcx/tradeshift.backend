package tradeshift.foodfacility.translator.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import tradeshift.foodfacility.model.Coordinates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementations of customized Gson Serialization and Deserialization.
 * @Deprecated
 *
 * @author xuch.
 */
@Deprecated
public class CoordinatesTypeAdapter extends TypeAdapter<Coordinates> {
    private static final Gson gson = new Gson();
    @Override
    public void write(JsonWriter out, Coordinates value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(gson.toJson(Arrays.asList(value.getLongitude(), value.getLatitude())));
        }
    }

    @Override
    public Coordinates read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
        } else {
            List<Double> result = gson.fromJson(in.nextString(), ArrayList.class);
            if (result != null && result.size() == 2) {
                Coordinates coordinates = new Coordinates();
                coordinates.setLongitude(result.get(0));
                coordinates.setLatitude(result.get(1));
                return coordinates;
            }
        }
        return null;
    }
}
