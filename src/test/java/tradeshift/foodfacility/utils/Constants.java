package tradeshift.foodfacility.utils;

import org.joda.time.DateTime;
import tradeshift.foodfacility.constants.FacilityType;
import tradeshift.foodfacility.constants.FoodFacilityStatus;

/**
 * All constants for unit test.
 * @author xuch.
 */
public class Constants {
    public static final double DELTA = 1e-15;
    public static final String TEST_DATA_FILE = "../data/MobileFoodFacilityOriginalData";
    public static final String EXPECTED_DATA_FILE = "../data/MobileFoodFacilityExpectedOutput";
    public static final String TEST_DATA_LIST_FILE = "../data/MobileFoodFacilityOriginalDataList";
    public static final String EXPECTED_DATA_LIST_FILE = "../data/MobileFoodFacilityExpectedOutputList";

    public static final int DATA_LIST_SIZE = 30;

    public static final String APPLICANT = "Munch A Bunch";
    public static final FacilityType FACILITY_TYPE = FacilityType.TRUCK;
    public static final String CNN = "546000";
    public static final String LOCATION_DESCRIPTION = "12TH ST: ISIS ST to BERNICE ST (332 - 365)";
    public static final String ADDRESS = "333 12TH ST";
    public static final String BLOCKLOT = "3521022";
    public static final String BLOCK = "3521";
    public static final String LOT = "022";
    public static final String PERMIT = "16MFF-0069";
    public static final FoodFacilityStatus FOOD_FACILITY_STATUS = FoodFacilityStatus.APPROVED;
    public static final String[] FOOD_ITEMS = {"Cold Truck", "packaged sandwiches", "pitas", "breakfast", "cold and hot drinks", "snacks"};
    public static final double X = 6008484.985;
    public static final double Y = 2108791.319;
    public static final double LATITUDE = 37.7708182299993;
    public static final double LONGITUDE = -122.414007297597;
    public static final String SCHEDULE_LINK = "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=16MFF-0069&ExportPDF=1&Filename=16MFF-0069_schedule.pdf";
    public static final String DAYS_HORS = "Mo-Fr:9AM-10AM";
    public static final Integer PRIOR_PERMIT = 1;
    public static final DateTime NOI_SENT = new DateTime("2013-07-25T00:00:00.000");
    public static final DateTime APPROVED = new DateTime("2016-03-15T00:00:00.000");
    public static final DateTime RECEIVED = new DateTime("2016-03-15");
    public static final DateTime EXPIRATION_DATE = new DateTime("2017-03-15T00:00:00.000");
}
