package tradeshift.foodfacility.activity;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tradeshift.foodfacility.activity.model.AbstractActivity;
import tradeshift.foodfacility.activity.model.ActivityIdentifier;
import tradeshift.foodfacility.exceptions.InvalidInputException;

/**
 * @author xuch.
 */
@Component
public class ActivityFactory {
    @Autowired
    private GetAllMobileFoodFacilitiesActivity getAllMobileFoodFacilitiesActivity;

    @Autowired
    private GetAllMobileFoodFacilitiesWithinAreaActivity getAllMobileFoodFacilitiesWithinAreaActivity;

    public AbstractActivity getInstance(ActivityIdentifier activityIdentifier) throws InvalidInputException {
        switch (activityIdentifier) {
            case ALL:
                return getAllMobileFoodFacilitiesActivity;
            case OPTIONAL:
                return getAllMobileFoodFacilitiesWithinAreaActivity;
            default:
                throw new InvalidInputException(MessageFormatter.format("No activity matches the identifier [{}]", activityIdentifier).getMessage());
        }
    }
}
