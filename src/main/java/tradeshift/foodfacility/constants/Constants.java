package tradeshift.foodfacility.constants;

/**
 * @author xuch.
 */
public class Constants {
    public static class Common {
        public static final double EARTH_RADIUS_KM = 6378.137;
        public static final String INPUT_BOUNDARY = "\\A";
        public static final String COMMON_DELIMITER = "-";
        private static final double DEFAULT_MAX_RANGE_RADIUS = Double.MAX_VALUE; // 8.0 km
    }

    public static class FoodFacilityKeys {
        public static final String FOOD_FACILITY_HTTP_REQUEST_URL = "https://data.sfgov.org/resource/6a9r-agq8.json";
        public static final String EVENT_NAME = "Food Facility";
        public static final String UNKNOW = "Unknown facility type";
    }

    public static class LocationSerializedName {
        public static class LocationType {
            public static final String VALUE = "locationType";
            public static final String ALTERNATE_FIRST = "locationtype";
            public static final String ALTERNATE_SECOND = "type";
        }
    }

    public static class FacilityTypeSerializedName {
        public static class Truck {
            public static final String VALUE = "Truck";
            public static final String ALTERNATE_FIRST = "TRUCK";
            public static final String ALTERNATE_SECOND = "truck";
        }

        public static class PushCart {
            public static final String VALUE = "Push Cart";
            public static final String ALTERNATE_FIRST = "PUSH CART";
            public static final String ALTERNATE_SECOND = "PUSH_CART";
            public static final String ALTERNATE_THIRD = "Push cart";
        }
    }

    public static class LocationTypeSerializedName {
        public static class Point {
            public static final String VALUE = "Point";
            public static final String ALTERNATE_FIRST = "POINT";
            public static final String ALTERNATE_SECOND = "point";
        }
    }

    public static class MobileFoodFacilitySerializedName {
        public static class FacilityType {
            public static final String VALUE = "facilityType";
            public static final String ALTERNATE_FIRST = "FacilityType";
            public static final String ALTERNATE_SECOND = "facilitytype";
        }

        public static class LocationDescription {
            public static final String VALUE = "locationDescription";
            public static final String ALTERNATE_FIRST = "LocationDescription";
            public static final String ALTERNATE_SECOND = "locationdescription";
        }

        public static class FoodItems {
            public static final String VALUE = "foodItems";
            public static final String ALTERNATE_FIRST = "FoodItems";
            public static final String ALTERNATE_SECOND = "fooditems";
        }

        public static class ScheduleLink {
            public static final String VALUE = "scheduleLink";
            public static final String ALTERNATE_FIRST = "ScheduleLink";
            public static final String ALTERNATE_SECOND = "schedulelink";
            public static final String ALTERNATE_THIRD = "schedule";
        }

        public static class DaysHours {
            public static final String VALUE = "daysHours";
            public static final String ALTERNATE_FIRST = "DaysHours";
            public static final String ALTERNATE_SECOND = "dayshours";
        }

        public static class PriorPermit {
            public static final String VALUE = "priorPermit";
            public static final String ALTERNATE_FIRST = "PriorPermit";
            public static final String ALTERNATE_SECOND = "priorpermit";
        }

        public static class ExpirationDate {
            public static final String VALUE = "expirationDate";
            public static final String ALTERNATE_FIRST = "ExpirationDate";
            public static final String ALTERNATE_SECOND = "expirationdate";
        }
    }
}
