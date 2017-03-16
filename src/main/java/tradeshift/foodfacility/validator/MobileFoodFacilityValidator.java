package tradeshift.foodfacility.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.Validate;

import tradeshift.foodfacility.model.MobileFoodFacility;

/**
 * @author xuch.
 */
@Component
public class MobileFoodFacilityValidator extends BaseValidator<MobileFoodFacility> {
    @Autowired
    private LocationValidator locationValidator;

    @Override
    public void validate(MobileFoodFacility mobileFoodFacility) {
        Validate.notNull(mobileFoodFacility, "MobileFoodFacility was found null");
        //Validate.notNull(mobileFoodFacility.getIdentifier(), "Identifier was found null");
        //Validate.notNull(mobileFoodFacility.getFacilityType(), "FacilityType was found null"); // some are empty
        //Validate.notNull(mobileFoodFacility.getAddress(), "Address was found null"); // only one is empty
        locationValidator.validate(mobileFoodFacility.getLocation());
        Validate.notNull(mobileFoodFacility.getLatitude(), "Latitude was found null"); // ok
        Validate.notNull(mobileFoodFacility.getLongitude(), "Longitude was found null"); // ok
        Validate.notNull(mobileFoodFacility.getStatus(), "FacilityStatus was found null"); // ok
        //Validate.notEmpty(mobileFoodFacility.getFoodItems(), "FoodItems was found null");
    }
}
