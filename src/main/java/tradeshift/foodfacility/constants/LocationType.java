package tradeshift.foodfacility.constants;

import com.google.gson.annotations.SerializedName;

/**
 * @author xuch.
 */
public enum LocationType {
    @SerializedName(
            value = Constants.LocationTypeSerializedName.Point.VALUE,
            alternate = {
                    Constants.LocationTypeSerializedName.Point.ALTERNATE_FIRST,
                    Constants.LocationTypeSerializedName.Point.ALTERNATE_SECOND})
    POINT;
}
