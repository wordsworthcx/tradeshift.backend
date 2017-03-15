package tradeshift.foodfacility.activity;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import tradeshift.foodfacility.activity.model.AbstractActivity;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesWithinAreaRequest;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesWithinAreaResponse;
import tradeshift.foodfacility.cache.Cache;
import tradeshift.foodfacility.cache.LRUCache;
import tradeshift.foodfacility.cache.CachePolicy;
import tradeshift.foodfacility.cache.CacheManager;
import tradeshift.foodfacility.constants.CacheConstants;
import tradeshift.foodfacility.exceptions.DependencyException;
import tradeshift.foodfacility.exceptions.InvalidInputException;
import tradeshift.foodfacility.model.Location;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.utils.CommonUtility;
import tradeshift.foodfacility.utils.GeoUtility;
import tradeshift.foodfacility.validator.LocationValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import com.google.appengine.repackaged.com.google.common.base.Pair;

/**
 * This class implements the operation that gets all mobile food facilities within an area with a specific radius around a center location.
 *
 * @author xuch.
 */
@Component
@Slf4j
public class GetAllMobileFoodFacilitiesWithinAreaActivity extends AbstractActivity<GetAllMobileFoodFacilitiesWithinAreaRequest, GetAllMobileFoodFacilitiesWithinAreaResponse>{
    @Autowired
    private GetAllMobileFoodFacilitiesActivity getAllMobileFoodFacilitiesActivity;

    @Autowired
    private GeoUtility geoUtility;

    @Autowired
    private LocationValidator locationValidator;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CommonUtility commonUtility;

    @Override
    public GetAllMobileFoodFacilitiesWithinAreaResponse enact(GetAllMobileFoodFacilitiesWithinAreaRequest request)
            throws InvalidInputException, DependencyException {
        Validate.notNull(request, "GetAllMobileFoodFacilitiesWithinAreaRequest was found null");
        Validate.notNull(request.getRadius(), "Radius was found null");
        locationValidator.validate(request.getCenter());

        Location center = request.getCenter();
        GetAllMobileFoodFacilitiesWithinAreaResponse response = new GetAllMobileFoodFacilitiesWithinAreaResponse();
        log.info("Starting retrieving all mobile food facilities within area " +
                "with radius [{}] around center location whose latitude is [{}] and longitude is [{}].",
                request.getRadius(), center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude());

        PriorityQueue<Pair<Double, MobileFoodFacility>> orderedMobileFoodFacilityAround = getAllMobileFoodFacilitiesAndDistances(center);
        if (CollectionUtils.isEmpty(orderedMobileFoodFacilityAround)) {
            log.warn("No MobileFoodFacility retrieved");
            return response;
        }

        /**
         * TODO
         * Google App Engine has a {@code restriction of usage of java.util.stream.Stream}
         */
        /*
        List<MobileFoodFacility> result = orderedMobileFoodFacilityAround
                .stream()
                .filter((each) -> each.getFirst().compareTo(request.getRadius()) < 1)
                .map(each -> each.getSecond())
                .collect(Collectors.toList());
        */
        List<MobileFoodFacility> result = new ArrayList<>();
        orderedMobileFoodFacilityAround.forEach(each -> {
                    if (each.getFirst().compareTo(request.getRadius()) < 1) {
                        result.add(each.getSecond());
                    }
        });

        if (result.isEmpty()) {
            log.warn("No MobileFoodFacility was found around location with latitude:[{}] and longitude:[{}] within radius of [{}]",
                    center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude(), request.getRadius());
            return response;
        }

        response.setMobileFoodFacilities(result);
        return response;
    }

    /**
     * TODO implement the comparable of Location: getCenter so that the key comparable can work
     * @return
     */
//    @Cacheable(
//            value = CacheConstants.ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE,
//            key = "String.valueOf(#center.getCoordinates().getLatitude()).concat(String.valueOf(#center.getCoordinates().getLongitude()))")
    private PriorityQueue getAllMobileFoodFacilitiesAndDistances(Location center) {

        PriorityQueue<Pair<Double, MobileFoodFacility>> orderedMobileFoodFacilityAround;

        log.info("Trying to read cache for mobile food facilities around location[latitude:{}, longitude:{}]",
                center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude());
        Cache cache = cacheManager.getCache(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE);
        if (cache == null) {
            log.info("Cache is not availble, and will create a new cache for all mobile food facilities around location[latitude:{}, longitude:{}]",
                    center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude());
            cache = new LRUCache<>(
                    new CachePolicy(CacheConstants.FIXED_EXPIRATION),
                    CacheConstants.ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE_SIZE);
            cacheManager.addCache(CacheConstants.ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE, cache);
        }

        String cacheKey = commonUtility.generateKey(
                String.valueOf(center.getCoordinates().getLatitude()),
                String.valueOf(center.getCoordinates().getLongitude()));
        if (cache.isExpired(cacheKey)) {
            log.info("Cache was expired or not shoot. Will retrive data and update it into cache");
            List<MobileFoodFacility> mobileFoodFacilities = getAllMobileFoodFacilitiesActivity.enact(null).getMobileFoodFacilities();
            orderedMobileFoodFacilityAround = new PriorityQueue<>(
                    (Pair<Double, MobileFoodFacility> first, Pair<Double, MobileFoodFacility> second)
                            -> first.getFirst().compareTo(second.getFirst()));
            if (!CollectionUtils.isEmpty(mobileFoodFacilities)) {
                mobileFoodFacilities.forEach(each -> {
                    Double distance = geoUtility.getGeoDistance(
                            each.getLatitude(), each.getLongitude(),
                            center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude());
                    orderedMobileFoodFacilityAround.add(Pair.of(distance, each));
                });
            }
            cache.set(cacheKey, orderedMobileFoodFacilityAround);
        } else {
            log.info("Cache was shoot, will return all mobile food facilities from cache");
            orderedMobileFoodFacilityAround = (PriorityQueue<Pair<Double, MobileFoodFacility>>)cache.get(cacheKey);
        }

        return orderedMobileFoodFacilityAround;
    }
}
