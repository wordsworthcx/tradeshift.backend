package tradeshift.foodfacility.translator.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementations of customized Gson Serialization and Deserialization for FoodItems.
 * @author xuch.
 */
public class FoodItemsTypeAdapter extends TypeAdapter<List<String>>{
    private static final String DELIMETER = ": ";

    @Override
    public void write(JsonWriter out, List<String> value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(String.join(DELIMETER, value));
        }
    }

    @Override
    public List<String> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return new ArrayList<>(Arrays.asList(in.nextString().split(DELIMETER)));
    }
}
