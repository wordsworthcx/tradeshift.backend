package tradeshift.foodfacility.validator;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Component;
import tradeshift.foodfacility.model.Location;

/**
 * @author xuch.
 */
@Component
public class LocationValidator extends BaseValidator<Location> {
    @Override
    public void validate(Location location) {
        Validate.notNull(location, "Location was found null");
        Validate.notNull(location.getCoordinates(), "Location coordinates were found null");
        Validate.notNull(location.getCoordinates().getLatitude(), "Latitude was found null");
        Validate.notNull(location.getCoordinates().getLongitude(), "Latitude was found null");
    }
}
