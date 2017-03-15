package tradeshift.foodfacility.constants;

import tradeshift.foodfacility.model.Coordinates;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuch.
 */
public class MapConstants {
    private static final String DEFAULT_INITIAL_COORDINATES_KEY = "default";

    public enum MapSourceIdentifier {
        GOOGLE;
    }

    /**
     * This map stores userId and its initial map location.
     * Anyway, these should be stored in a database in the long term.
     */
    public static final Map<String, Coordinates> INITIAL_COORDINATES_MAPPING = new HashMap<String, Coordinates>() {
        {
            put(DEFAULT_INITIAL_COORDINATES_KEY, new Coordinates(37.76999, -122.44696));
        }
    };

    public static final Map<MapSourceIdentifier, String> MAP_SOURCE_KEY_MAPPING = new HashMap<MapSourceIdentifier, String>() {
        {
            put(MapSourceIdentifier.GOOGLE, "AIzaSyCAYUUcNYItswcpBESb1WAPZcMR_mVcExQ"); //AIzaSyD5vzXtOJxVPzFbyt8p96LYW7Il2qK6kpo
        }
    };

    public static Coordinates getInitialCoordinates(String user) {
        if (INITIAL_COORDINATES_MAPPING.containsKey(user)) {
            return INITIAL_COORDINATES_MAPPING.get(user);
        }
        return getDefaultInitialCoordinates();
    }

    public static Coordinates getDefaultInitialCoordinates() {
        return INITIAL_COORDINATES_MAPPING.get(DEFAULT_INITIAL_COORDINATES_KEY);
    }

    public static String getMapKey(MapSourceIdentifier mapSourceIdentifier) {
        return MAP_SOURCE_KEY_MAPPING.get(mapSourceIdentifier);
    }
}
