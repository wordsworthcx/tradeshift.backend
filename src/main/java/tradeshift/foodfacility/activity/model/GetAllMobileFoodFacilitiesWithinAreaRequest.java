package tradeshift.foodfacility.activity.model;

import lombok.Data;
import tradeshift.foodfacility.model.Location;

/**
 * @author xuch.
 */
@Data
public class GetAllMobileFoodFacilitiesWithinAreaRequest extends AbstractActivityRequest {
    private Location center;
    private Double radius;
}
