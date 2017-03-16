package tradeshift.foodfacility.validator;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Component;

import tradeshift.foodfacility.model.MapExhibitionEvent;

/**
 * @author xuch.
 */
@Component
public class MapExhibitionEventValidator extends BaseValidator<MapExhibitionEvent> {
    @Override
    public void validate(MapExhibitionEvent mapExhibitionEvent) {
        Validate.notNull(mapExhibitionEvent, "MapExhibitionEvent was found null");
        Validate.notNull(mapExhibitionEvent.getEventName(), "MapExhibitionEvent name was found null");
        Validate.notNull(mapExhibitionEvent.getLatitude(), "MapExhibitionEvent latitude was found null");
        Validate.notNull(mapExhibitionEvent.getLongitude(), "MapExhibitionEvent longitude was found null");
    }
}
