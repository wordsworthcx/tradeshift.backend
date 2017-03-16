package tradeshift.foodfacility.utils;

import org.springframework.stereotype.Component;

import tradeshift.foodfacility.constants.Constants;

/**
 * @author xuch.
 */
@Component
public class CommonUtility {
    public String generateKey(String left, String right) {
        return left + Constants.Common.COMMON_DELIMITER + right;
    }
}
