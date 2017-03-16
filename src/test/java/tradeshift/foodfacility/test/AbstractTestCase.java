package tradeshift.foodfacility.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tradeshift.foodfacility.translator.MobileFoodFacilityTranslator;
import tradeshift.foodfacility.utils.GsonUtility;
import tradeshift.foodfacility.utils.MobileFoodFacilityUtility;

/**
 * Base class for test cases, loads the unit testing spring configuration
 * context and does some other setup before execution, then cleans up
 * afterwards. It also contains a few helper methods for creating unit tests.
 *
 * @author xuch.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/unit-testing-context.xml"})
public abstract class AbstractTestCase {
    @Autowired
    protected GsonUtility gsonUtility;

    @Autowired
    protected MobileFoodFacilityTranslator mobileFoodFacilityTranslator;

    @Autowired
    protected MobileFoodFacilityUtility mobileFoodFacilityUtility;

    @Before
    public void initialize() {
        Mockito.reset();
    }
}
