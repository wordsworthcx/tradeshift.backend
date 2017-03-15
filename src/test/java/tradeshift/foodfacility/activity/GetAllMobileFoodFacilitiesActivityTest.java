package tradeshift.foodfacility.activity;

import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesResponse;
import tradeshift.foodfacility.cache.Cache;
import tradeshift.foodfacility.cache.CacheManager;
import tradeshift.foodfacility.exceptions.InvalidInputException;
import tradeshift.foodfacility.test.AbstractTestCase;
import tradeshift.foodfacility.translator.MobileFoodFacilityTranslator;
import tradeshift.foodfacility.utils.Constants;
import tradeshift.foodfacility.utils.RestUtility;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * @author xuch.
 */
public class GetAllMobileFoodFacilitiesActivityTest extends AbstractTestCase {
    @InjectMocks
    private GetAllMobileFoodFacilitiesActivity getAllMobileFoodFacilitiesActivity;

    @Autowired
    private RestUtility restUtility;

    @Autowired
    private CacheManager cacheManager;

    @Mock
    private MobileFoodFacilityTranslator mobileFoodFacilityTranslator;

    @Mock
    private Cache cache;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        reset(cache, cacheManager, restUtility, mobileFoodFacilityTranslator);
        doReturn(mobileFoodFacilityUtility.getOriginalDataList()).when(restUtility).getRestfulResponse(anyString());
        doReturn(mobileFoodFacilityUtility.buildMobileFoodFacilityList(Constants.DATA_LIST_SIZE)).when(mobileFoodFacilityTranslator).translateList(anyString());
    }


    @Test
    public void happyCaseWithCacheNotShoot() {
        doReturn(true).when(cache).isExpired(any());
        doNothing().when(cache).set(any(), any());
        doReturn(cache).when(cacheManager).getCache(anyString());

        GetAllMobileFoodFacilitiesResponse response = getAllMobileFoodFacilitiesActivity.enact(null);

        Assert.assertEquals(Constants.DATA_LIST_SIZE, response.getMobileFoodFacilities().size());
        verify(restUtility, times(1)).getRestfulResponse(anyString());
        verify(mobileFoodFacilityTranslator, times(1)).translateList(anyString());
        verify(cacheManager, times(1)).getCache(anyString());
        verify(cache, times(1)).isExpired(any());
        verify(cache, times(0)).get(any());
        verify(cache, times(1)).set(any(), any());
    }

    @Test
    public void happyCaseWithCacheShoot() {
        doReturn(cache).when(cacheManager).getCache(anyString());
        doReturn(false).when(cache).isExpired(any());
        doReturn(mobileFoodFacilityUtility.buildMobileFoodFacilityList(Constants.DATA_LIST_SIZE)).when(cache).get(anyString());

        GetAllMobileFoodFacilitiesResponse response = getAllMobileFoodFacilitiesActivity.enact(null);

        Assert.assertEquals(Constants.DATA_LIST_SIZE, response.getMobileFoodFacilities().size());
        verify(restUtility, times(0)).getRestfulResponse(anyString());
        verify(mobileFoodFacilityTranslator, times(0)).translateList(anyString());
        verify(cacheManager, times(1)).getCache(anyString());
        verify(cache, times(1)).isExpired(any());
        verify(cache, times(1)).get(any());
        verify(cache, times(0)).set(any(), any());
    }

    @Test(expected = InvalidInputException.class)
    public void invalidJsonWithCacheNotShoot() {
        reset(mobileFoodFacilityTranslator);
        doReturn(true).when(cache).isExpired(any());
        doNothing().when(cache).set(any(), any());
        doReturn(cache).when(cacheManager).getCache(anyString());
        doThrow(new InvalidInputException()).when(mobileFoodFacilityTranslator).translateList(anyString());
        getAllMobileFoodFacilitiesActivity.enact(null);
    }

}
