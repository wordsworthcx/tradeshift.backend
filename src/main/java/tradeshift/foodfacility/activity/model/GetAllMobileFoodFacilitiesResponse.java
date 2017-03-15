package tradeshift.foodfacility.activity.model;

import lombok.Data;
import tradeshift.foodfacility.model.MobileFoodFacility;

import java.util.List;

/**
 * @author xuch.
 */
@Data
public class GetAllMobileFoodFacilitiesResponse extends AbstractActivityResponse {
    private List<MobileFoodFacility> mobileFoodFacilities;
}
