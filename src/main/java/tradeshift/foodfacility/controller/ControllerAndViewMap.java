package tradeshift.foodfacility.controller;

/**
 * @author xuch.
 */
public class ControllerAndViewMap {
    public static class ControllerMap {
        public static final String HOME = "/";
        public static final String INITIAL_LOCATION = "/initiallocation";
        public static final String ALL_MOBILE_FOOD_FACILITIES = "/mobilefoodfacilities";
        public static final String AROUND_MOBILE_FOOD_FACILITIES = "/mobilefoodfacilitiesaround";
    }

    public static class ViewMap {
        public static final String HOME = "main";
        public static final String ALL_MOBILE_FOOD_FACILITIES = "mobilefoodfacilities";
        public static final String AROUND_MOBILE_FOOD_FACILITIES = "mobilefoodfacilitiesaround";
    }
}
