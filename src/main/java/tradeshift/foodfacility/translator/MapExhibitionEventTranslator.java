package tradeshift.foodfacility.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tradeshift.foodfacility.constants.Constants;
import tradeshift.foodfacility.model.MapExhibitionEvent;
import tradeshift.foodfacility.model.MapExhibitionEvent.MapExhibitionEventCode;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.validator.MobileFoodFacilityValidator;

/**
 * @author xuch.
 */
@Component
public class MapExhibitionEventTranslator extends BaseTranslator<MobileFoodFacility, MapExhibitionEvent> {
    @Autowired
    private MobileFoodFacilityValidator mobileFoodFacilityValidator;

    @Override
    public MapExhibitionEvent translate(MobileFoodFacility mobileFoodFacility) {
        mobileFoodFacilityValidator.validate(mobileFoodFacility);
        MapExhibitionEvent mapExhibitionEvent = new MapExhibitionEvent();
        mapExhibitionEvent.setEventName(getMapExhibitionEventName(mobileFoodFacility));
        mapExhibitionEvent.setEventCode(getMapExhibitionEventCode(mobileFoodFacility));
        mapExhibitionEvent.setLatitude(mobileFoodFacility.getLatitude());
        mapExhibitionEvent.setLongitude(mobileFoodFacility.getLongitude());
        mapExhibitionEvent.setDescription(mobileFoodFacility.getLocationDescription() != null ?
                mobileFoodFacility.getLocationDescription() : mobileFoodFacility.getAddress());
        return mapExhibitionEvent;
    }

    @Override
    public MobileFoodFacility reverse(MapExhibitionEvent mapExhibitionEvent)throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Translation from MapExhibitionEvent to MobileFoodFacility is unsupported");
    }

    private String getMapExhibitionEventName(MobileFoodFacility mobileFoodFacility) {
        return Constants.FoodFacilityKeys.EVENT_NAME + Constants.Common.COMMON_DELIMITER +
                mobileFoodFacility.getFacilityType() == null ? Constants.FoodFacilityKeys.UNKNOW : mobileFoodFacility.getFacilityType().name();
    }

    private MapExhibitionEventCode getMapExhibitionEventCode(MobileFoodFacility mobileFoodFacility) {
        switch (mobileFoodFacility.getFacilityType()) {
            case TRUCK:
                return MapExhibitionEventCode.TRUCK;
            case PUSH_CART:
                return MapExhibitionEventCode.PUSH_CART;
            default:
                return MapExhibitionEventCode.FOOD_FACILITY;
                //throw new InvalidInputException(MessageFormatter.format(ErrorMessages.INVALID_INPUT, mobileFoodFacility.getFacilityType().name()).getMessage());
        }
    }
}
