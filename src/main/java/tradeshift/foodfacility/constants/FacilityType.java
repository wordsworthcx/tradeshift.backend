package tradeshift.foodfacility.constants;

import com.google.gson.annotations.SerializedName;

/**
 * @author xuch.
 */
public enum FacilityType {
    @SerializedName(
            value = Constants.FacilityTypeSerializedName.Truck.VALUE,
            alternate = {
                    Constants.FacilityTypeSerializedName.Truck.ALTERNATE_FIRST,
                    Constants.FacilityTypeSerializedName.Truck.ALTERNATE_SECOND})
    TRUCK,

    @SerializedName(
            value = Constants.FacilityTypeSerializedName.PushCart.VALUE,
            alternate = {Constants.FacilityTypeSerializedName.PushCart.ALTERNATE_FIRST,
                    Constants.FacilityTypeSerializedName.PushCart.ALTERNATE_SECOND,
                    Constants.FacilityTypeSerializedName.PushCart.ALTERNATE_THIRD})
    PUSH_CART;
}
