package tradeshift.foodfacility.translator;

import tradeshift.foodfacility.exceptions.ErrorMessages;
import tradeshift.foodfacility.exceptions.InvalidInputException;
import tradeshift.foodfacility.model.Coordinates;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.translator.model.CoordinatesDeserializer;
import tradeshift.foodfacility.translator.model.DateTimeTypeAdapter;
import tradeshift.foodfacility.translator.model.FoodItemsTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
/**
 * @author xuch.
 */
@Slf4j
@Component
public class MobileFoodFacilityTranslator extends BaseTranslator<String, MobileFoodFacility> {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
            .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
            .registerTypeAdapter(new TypeToken<List<String>>(){}.getType(), new FoodItemsTypeAdapter())
            .create();

    @Override
    public MobileFoodFacility translate(String s) throws InvalidInputException {
        try {
            return gson.fromJson(s, MobileFoodFacility.class);
        } catch (JsonParseException e) {
            log.error(ErrorMessages.INVALID_JSON, s);
            throw new InvalidInputException(MessageFormatter.format(ErrorMessages.INVALID_JSON, s).getMessage(), e);
        }
    }

    @Override
    public String reverse(MobileFoodFacility mobileFoodFacility) {
        return gson.toJson(mobileFoodFacility);
    }

    public List<MobileFoodFacility> translateList(String s) throws InvalidInputException {
        try {
            return gson.fromJson(s, new TypeToken<List<MobileFoodFacility>>() {}.getType());
        } catch (JsonParseException e) {
            log.error(ErrorMessages.INVALID_JSON, s);
            throw new InvalidInputException(MessageFormatter.format(ErrorMessages.INVALID_JSON, s).getMessage(), e);
        }
    }

    public String reverseList(List<MobileFoodFacility> mobileFoodFacilities) {
        return gson.toJson(mobileFoodFacilities);
    }
}
