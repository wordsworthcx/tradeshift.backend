package tradeshift.foodfacility.activity;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import tradeshift.foodfacility.activity.model.AbstractActivity;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesRequest;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesResponse;
import tradeshift.foodfacility.cache.Cache;
import tradeshift.foodfacility.cache.CachePolicy;
import tradeshift.foodfacility.cache.LRUCache;
import tradeshift.foodfacility.constants.CacheConstants;
import tradeshift.foodfacility.constants.Constants;
import tradeshift.foodfacility.exceptions.DependencyException;
import tradeshift.foodfacility.exceptions.InvalidInputException;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.translator.MobileFoodFacilityTranslator;
import tradeshift.foodfacility.utils.RestUtility;
import tradeshift.foodfacility.cache.CacheManager;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * This class implements the operation that gets all available mobile food facilities.
 *
 * @author xuch.
 */
@Component
@Slf4j
public class GetAllMobileFoodFacilitiesActivity extends AbstractActivity<GetAllMobileFoodFacilitiesRequest, GetAllMobileFoodFacilitiesResponse> {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RestUtility restUtility;

    @Autowired
    private MobileFoodFacilityTranslator mobileFoodFacilityTranslator;

    @Override
    //@Cacheable(value = CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE)
    public GetAllMobileFoodFacilitiesResponse enact(GetAllMobileFoodFacilitiesRequest getAllMobileFoodFacilitiesRequest)
            throws InvalidInputException, DependencyException {
        log.info("Starting retrieving all mobile food facilities");

        List<MobileFoodFacility> mobileFoodFacilities;
        log.info("Trying to read cache for all mobile food facilities");
        Cache cache = cacheManager.getCache(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE);
        if (cache == null) {
            log.info("Cache is not availble, and will create a new cache for all mobile food facilities");
            cache = new LRUCache<>(
                    new CachePolicy(CacheConstants.FIXED_EXPIRATION),
                    CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE_SIZE);
            cacheManager.addCache(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE, cache);
        }

        if (cache.isExpired(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE_DEFAULT_KEY)) {
            log.info("Cache was expired or not shoot. Will retrive data and update it into cache");
            String result = restUtility.getRestfulResponse(Constants.FoodFacilityKeys.FOOD_FACILITY_HTTP_REQUEST_URL);
            mobileFoodFacilities = mobileFoodFacilityTranslator.translateList(result);
            cache.set(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE_DEFAULT_KEY, mobileFoodFacilities);
        } else {
            log.info("Cache was shoot, will return all mobile food facilities from cache");
            mobileFoodFacilities = (List<MobileFoodFacility>)cache.get(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE_DEFAULT_KEY);
        }

        GetAllMobileFoodFacilitiesResponse response = new GetAllMobileFoodFacilitiesResponse();
        if (CollectionUtils.isEmpty(mobileFoodFacilities)) {
            log.warn("No MobileFoodFacility retrieved");
            return response;
        }

        response.setMobileFoodFacilities(mobileFoodFacilities);
        return response;
    }

//    @Scheduled(fixedDelay = CacheConstants.FIXED_DELAY, initialDelay = CacheConstants.INITIAL_DELAY)
//    @CacheEvict(value = CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE, allEntries = true)
//    public void flushCache() {
//        log.info("Flushing cache of {}", CacheConstants.ALL_MOBILE_FOOD_FACILITIES_CACHE);
//    }

}
