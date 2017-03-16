package tradeshift.foodfacility.model;

import tradeshift.foodfacility.constants.Constants;
import tradeshift.foodfacility.constants.FacilityType;
import tradeshift.foodfacility.constants.FoodFacilityStatus;

import java.util.List;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author xuch.
 */
@Data
public class MobileFoodFacility extends FoodFacility {
    private String applicant;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.FacilityType.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.FacilityType.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.FacilityType.ALTERNATE_SECOND})
    private FacilityType facilityType;
    private String cnn;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.LocationDescription.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.LocationDescription.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.LocationDescription.ALTERNATE_SECOND})
    private String locationDescription;
    private String address;
    private String blocklot;
    private String block;
    private String lot;
    private String permit;
    private FoodFacilityStatus status;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.FoodItems.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.FoodItems.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.FoodItems.ALTERNATE_SECOND})
    private List<String> foodItems;
    private double x;
    private double y;
    private double latitude;
    private double longitude;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.ScheduleLink.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.ScheduleLink.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.ScheduleLink.ALTERNATE_SECOND,
                    Constants.MobileFoodFacilitySerializedName.ScheduleLink.ALTERNATE_THIRD})
    private String scheduleLink;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.DaysHours.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.DaysHours.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.DaysHours.ALTERNATE_SECOND})
    private String daysHours;
    private DateTime noiSent;
    private DateTime approved;
    private DateTime received;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.PriorPermit.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.PriorPermit.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.PriorPermit.ALTERNATE_SECOND})
    private Integer priorPermit;
    @SerializedName(
            value = Constants.MobileFoodFacilitySerializedName.ExpirationDate.VALUE,
            alternate = {
                    Constants.MobileFoodFacilitySerializedName.ExpirationDate.ALTERNATE_FIRST,
                    Constants.MobileFoodFacilitySerializedName.ExpirationDate.ALTERNATE_SECOND})
    private DateTime expirationDate;
}


















