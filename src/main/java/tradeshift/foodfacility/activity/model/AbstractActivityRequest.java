package tradeshift.foodfacility.activity.model;

import lombok.Data;

/**
 * @author xuch.
 */
@Data
public abstract class AbstractActivityRequest {
    private ActivityIdentifier activityIdentifier;
}
