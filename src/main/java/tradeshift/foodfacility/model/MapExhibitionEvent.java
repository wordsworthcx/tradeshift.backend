package tradeshift.foodfacility.model;

import lombok.Data;

/**
 * @author xuch.
 */
@Data
public class MapExhibitionEvent {
    private String eventName;
    private MapExhibitionEventCode eventCode;
    private double latitude;
    private double longitude;
    private String description;

    public enum MapExhibitionEventCode {
        FOOD_FACILITY, TRUCK, PUSH_CART;
    }
}
