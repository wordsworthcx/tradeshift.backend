package tradeshift.foodfacility.utils;

import tradeshift.foodfacility.exceptions.ErrorMessages;
import tradeshift.foodfacility.model.MobileFoodFacility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * A utility class of MobileFoodFacility.
 * Use this utility class to get original test data and create an object of MobileFoodFacility.
 *
 * @author xuch.
 */
@Slf4j
@Component
public class MobileFoodFacilityUtility {

    public MobileFoodFacility buildMobileFoodFacility() {
        MobileFoodFacility mobileFoodFacility = new MobileFoodFacility();
        mobileFoodFacility.setApplicant(Constants.APPLICANT);
        mobileFoodFacility.setFacilityType(Constants.FACILITY_TYPE);
        mobileFoodFacility.setCnn(Constants.CNN);
        mobileFoodFacility.setLocationDescription(Constants.LOCATION_DESCRIPTION);
        mobileFoodFacility.setAddress(Constants.ADDRESS);
        mobileFoodFacility.setBlocklot(Constants.BLOCKLOT);
        mobileFoodFacility.setBlock(Constants.BLOCK);
        mobileFoodFacility.setLot(Constants.LOT);
        mobileFoodFacility.setPermit(Constants.PERMIT);
        mobileFoodFacility.setStatus(Constants.FOOD_FACILITY_STATUS);
        mobileFoodFacility.setFoodItems(buildFoodItems());
        mobileFoodFacility.setX(Constants.X);
        mobileFoodFacility.setY(Constants.Y);
        mobileFoodFacility.setLatitude(Constants.LATITUDE);
        mobileFoodFacility.setLongitude(Constants.LONGITUDE);
        mobileFoodFacility.setScheduleLink(Constants.SCHEDULE_LINK);
        mobileFoodFacility.setDaysHours(Constants.DAYS_HORS);
        mobileFoodFacility.setNoiSent(Constants.NOI_SENT);
        mobileFoodFacility.setApproved(Constants.APPROVED);
        mobileFoodFacility.setReceived(Constants.RECEIVED);
        mobileFoodFacility.setExpirationDate(Constants.EXPIRATION_DATE);
        mobileFoodFacility.setPriorPermit(Constants.PRIOR_PERMIT);
        return mobileFoodFacility;
    }

    public List<String> buildFoodItems() {
        return new ArrayList<>(Arrays.asList(Constants.FOOD_ITEMS));
    }

    public String getOriginalData() {
        return read(Constants.TEST_DATA_FILE);
    }

    public String getOriginalDataList() {
        return read(Constants.TEST_DATA_LIST_FILE);
    }

    public String getExpectedOutput() {
        return read(Constants.EXPECTED_DATA_FILE);
    }

    public String getExpectedOutputList() {
        return read(Constants.EXPECTED_DATA_LIST_FILE);
    }

    private String read(String file) {
        try (Scanner scanner = new Scanner(MobileFoodFacilityUtility.class.getResource(file).openStream())) {
            return scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            log.error(ErrorMessages.READ_FILE_ERROR, MobileFoodFacilityUtility.class.getResource(file).getPath(), e);
        }
        return null;
    }


    public List<MobileFoodFacility> buildMobileFoodFacilityList(final int size) {
        List<MobileFoodFacility> mobileFoodFacilities = new ArrayList<>();
        for (int i = 0; i < size; mobileFoodFacilities.add(buildMobileFoodFacility()), i++);
        return mobileFoodFacilities;
    }

}
