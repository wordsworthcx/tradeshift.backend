package tradeshift.foodfacility.translator.model;

import com.google.gson.*;
import tradeshift.foodfacility.model.Coordinates;

import java.lang.reflect.Type;

/**
 * Implementation of customized Gson deserialization for Coordinates.
 * @author xuch.
 */
public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && !json.isJsonNull()) {
            JsonArray jsonArray = json.getAsJsonArray();
            if (jsonArray.size() == 2) {
                Coordinates coordinates = new Coordinates();
                coordinates.setLatitude(jsonArray.get(1).getAsDouble());
                coordinates.setLongitude(jsonArray.get(0).getAsDouble());
                return coordinates;
            }
        }
        return null;
    }
}
