package tradeshift.foodfacility.translator;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import tradeshift.foodfacility.constants.FacilityType;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.test.AbstractTestCase;
import tradeshift.foodfacility.utils.Constants;

import java.util.List;

/**
 * Unit test class of MobileFoodFacilityTranslator
 * @author xuch.
 */
public class MobileFoodFacilityTranslatorTest extends AbstractTestCase {
    private static final String DELIMITER = ": ";
    @Before
    public void initialize() {
    }

    @Test
    public void translateHappyCase() {
        String result = mobileFoodFacilityUtility.getOriginalData();
        MobileFoodFacility mobileFoodFacility = mobileFoodFacilityTranslator.translate(result);
        Assert.assertEquals(FacilityType.TRUCK, mobileFoodFacility.getFacilityType());
        Assert.assertEquals(Constants.ADDRESS, mobileFoodFacility.getAddress());
        Assert.assertEquals(Constants.LATITUDE, mobileFoodFacility.getLatitude(), Constants.DELTA);
        Assert.assertEquals(Constants.LONGITUDE, mobileFoodFacility.getLongitude(), Constants.DELTA);
        Assert.assertEquals(Constants.EXPIRATION_DATE.getMillis(), mobileFoodFacility.getExpirationDate().getMillis());
        Assert.assertEquals(String.join(DELIMITER, mobileFoodFacilityUtility.buildFoodItems()), String.join(DELIMITER, mobileFoodFacility.getFoodItems()));
    }

    @Test
    public void translateListHappyCase() {
        String result = mobileFoodFacilityUtility.getOriginalDataList();
        List<MobileFoodFacility> mobileFoodFacilities = mobileFoodFacilityTranslator.translateList(result);
        Assert.assertEquals(Constants.DATA_LIST_SIZE, mobileFoodFacilities.size());
    }

    @Test
    public void reverseHappyCase() {
        MobileFoodFacility mobileFoodFacility = mobileFoodFacilityUtility.buildMobileFoodFacility();

        String expected = mobileFoodFacilityUtility.getExpectedOutput();
        String actual = mobileFoodFacilityTranslator.reverse(mobileFoodFacility);
        Assert.assertEquals(expected, actual);
    }



}
