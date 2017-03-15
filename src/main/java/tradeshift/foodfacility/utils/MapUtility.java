package tradeshift.foodfacility.utils;

import org.springframework.stereotype.Component;
import tradeshift.foodfacility.constants.MapConstants;
import tradeshift.foodfacility.model.Location;

/**
 * @author xuch.
 */
@Component
public class MapUtility {

    public Location createInitialLocation() {
        Location location = new Location();
        location.setCoordinates(MapConstants.getInitialCoordinates(getCurrentUser()));
        return location;
    }

    public String getCurrentUser() {
        return null;
    }

    public String getInitialMapKey() {
        return MapConstants.getMapKey(MapConstants.MapSourceIdentifier.GOOGLE);
    }
}
