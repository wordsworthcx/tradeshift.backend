package tradeshift.foodfacility.model;

import tradeshift.foodfacility.constants.FacilityType;
import tradeshift.foodfacility.constants.FoodFacilityStatus;
import lombok.Data;

/**
 * @author xuch.
 */
@Data
public abstract class FoodFacility {
    private String identifier;
    private Location location;
    private String locationIdentifier;
}
