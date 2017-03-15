package tradeshift.foodfacility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tradeshift.foodfacility.constants.Constants;

/**
 * @author xuch.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private double latitude;
    private double longitude;
}
