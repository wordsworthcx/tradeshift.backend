package tradeshift.foodfacility.model;

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
