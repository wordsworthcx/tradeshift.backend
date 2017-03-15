package tradeshift.foodfacility.activity.model;

import java.util.List;

import lombok.Data;
import tradeshift.foodfacility.model.MobileFoodFacility;



/**
 * @author xuch.
 */
@Data
public class GetAllMobileFoodFacilitiesWithinAreaResponse extends AbstractActivityResponse {
    private List<MobileFoodFacility> mobileFoodFacilities;
}
