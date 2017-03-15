package tradeshift.foodfacility.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import org.joda.time.DateTime;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;

import tradeshift.foodfacility.exceptions.ErrorMessages;
import tradeshift.foodfacility.exceptions.InvalidInputException;
import tradeshift.foodfacility.translator.model.DateTimeTypeAdapter;

import java.lang.reflect.Type;

/**
 * A class providing customized json sereliazation and deserialization which are segregated from Gson.
 * Gson from Google is a static class and cannot be mocked during unit test.
 * This class provides convenience for mocking during unit test.
 * @author xuch.
 */
@Component
public class GsonUtility {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter()).create();

    public <T> String serialize(T t, Type typeOfSrc) {
        return gson.toJson(t, typeOfSrc);
    }

    public <T> String serialize(T t) {
        return gson.toJson(t);
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonParseException e) {
            throw new InvalidInputException(MessageFormatter.format(ErrorMessages.INVALID_JSON, json).getMessage(), e);
        }
    }
}
