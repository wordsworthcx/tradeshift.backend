package tradeshift.foodfacility.constants;

/**
 * @author xuch.
 */
public class CacheConstants {
    public static final int ALL_MOBILE_FOOD_FACILITIES_CACHE_SIZE = 1;
    public static final String ALL_MOBILE_FOOD_FACILITIES_CACHE = "mobileFoodFacilities";
    public static final String ALL_MOBILE_FOOD_FACILITIES_CACHE_DEFAULT_KEY = "mobileFoodFacilities";
    public static final String ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE = "mobileFoodFacilitiesAndDistances";
    public static final int ALL_MOBILE_FOOD_FACILITIES_AND_DISTANCES_CACHE_SIZE = 128;
    public static final long FIXED_EXPIRATION = 60 * 60 * 1000;
    public static final long INITIAL_EXPIRATION = 30 * 60 * 1000;
}
