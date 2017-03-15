package tradeshift.foodfacility.model;

import com.google.gson.annotations.SerializedName;
import tradeshift.foodfacility.constants.Constants;
import tradeshift.foodfacility.constants.LocationType;
import lombok.Data;

/**
 * @author xuch.
 */
@Data
public class Location {
    @SerializedName(
            value = Constants.LocationSerializedName.LocationType.VALUE,
            alternate = {
                    Constants.LocationSerializedName.LocationType.ALTERNATE_FIRST,
                    Constants.LocationSerializedName.LocationType.ALTERNATE_SECOND})
    private LocationType locationType;
    private Coordinates coordinates;
}
