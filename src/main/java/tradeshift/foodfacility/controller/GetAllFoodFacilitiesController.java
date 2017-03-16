package tradeshift.foodfacility.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import tradeshift.foodfacility.activity.ActivityFactory;
import tradeshift.foodfacility.activity.GetAllMobileFoodFacilitiesActivity;
import tradeshift.foodfacility.activity.GetAllMobileFoodFacilitiesWithinAreaActivity;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesResponse;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesWithinAreaRequest;
import tradeshift.foodfacility.activity.model.GetAllMobileFoodFacilitiesWithinAreaResponse;
import tradeshift.foodfacility.model.Coordinates;
import tradeshift.foodfacility.model.Location;
import tradeshift.foodfacility.model.MobileFoodFacility;
import tradeshift.foodfacility.model.MapExhibitionEvent;
import tradeshift.foodfacility.translator.MapExhibitionEventTranslator;
import tradeshift.foodfacility.utils.GsonUtility;
import tradeshift.foodfacility.utils.MapUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuch.
 */
@Slf4j
@RestController
public class GetAllFoodFacilitiesController {
    @Autowired
    private MapUtility mapUtility;

    @Autowired
    private GsonUtility gsonUtility;

    @Autowired
    private MapExhibitionEventTranslator mapExhibitionEventTranslator;

    @Autowired
    private ActivityFactory activityFactory;

    @Autowired
    private GetAllMobileFoodFacilitiesActivity getAllMobileFoodFacilitiesActivity;

    @Autowired
    private GetAllMobileFoodFacilitiesWithinAreaActivity getAllMobileFoodFacilitiesWithinAreaActivity;

    @RequestMapping(ControllerAndViewMap.ControllerMap.HOME)
    public ModelAndView home() {

        /**
         * Get all mobile food facilities by default.
         */
        /*
        List<MapExhibitionEvent> mapExhibitionEvents = new ArrayList<>();
        GetAllMobileFoodFacilitiesResponse response = getAllMobileFoodFacilitiesActivity.enact(null);
        if (!CollectionUtils.isEmpty(response.getMobileFoodFacilities())) {
            response.getMobileFoodFacilities().forEach(each -> {
                try {
                    MapExhibitionEvent exhibitionEvent = mapExhibitionEventTranslator.translate(each);
                    mapExhibitionEvents.add(exhibitionEvent);
                } catch (Exception e) {
                    log.warn("Translation from MobileFoodFacility:[{}] to MapExhibitionEvent failed", gsonUtility.serialize(each));
                }
            });
        }
        Location location = mapUtility.createInitialLocation();
        ModelMap modelMap = new ModelMap("mapExhibitionEvents", mapExhibitionEvents);
        modelMap.addAttribute("initialLocation", mapUtility.createInitialLocation());
        modelMap.addAttribute("latitude", location.getCoordinates().getLatitude());
        modelMap.addAttribute("longitude", location.getCoordinates().getLongitude());
        */

        ModelAndView modelAndView = new ModelAndView(ControllerAndViewMap.ViewMap.HOME);
        modelAndView.addObject("gooleMapKey", mapUtility.getInitialMapKey());
        return modelAndView;
    }

    @RequestMapping(value = ControllerAndViewMap.ControllerMap.INITIAL_LOCATION, method = RequestMethod.GET)
    @ResponseBody
    public String initialLocation() {
        return gsonUtility.serialize(mapUtility.createInitialLocation());
    }

    @RequestMapping(value = ControllerAndViewMap.ControllerMap.ALL_MOBILE_FOOD_FACILITIES, method = RequestMethod.GET)
    @ResponseBody
    public String getMobileFoodFacilities() {
        List<MapExhibitionEvent> mapExhibitionEvents = new ArrayList<>();
        GetAllMobileFoodFacilitiesResponse response = getAllMobileFoodFacilitiesActivity.enact(null);
        if (!CollectionUtils.isEmpty(response.getMobileFoodFacilities())) {
            for (MobileFoodFacility each : response.getMobileFoodFacilities()) {
                try {
                    MapExhibitionEvent exhibitionEvent = mapExhibitionEventTranslator.translate(each);
                    mapExhibitionEvents.add(exhibitionEvent);
                } catch (Exception e) {
                    log.warn("Translation from MobileFoodFacility:[{}] to MapExhibitionEvent failed", gsonUtility.serialize(each));
                }
            }
            /**
             * Google App Engine cannot support JDK 8.
             */
            /*
            response.getMobileFoodFacilities().forEach(each -> {
                try {
                    MapExhibitionEvent exhibitionEvent = mapExhibitionEventTranslator.translate(each);
                    mapExhibitionEvents.add(exhibitionEvent);
                } catch (Exception e) {
                    log.warn("Translation from MobileFoodFacility:[{}] to MapExhibitionEvent failed", gsonUtility.serialize(each));
                }
            });
            */
        }
        return gsonUtility.serialize(mapExhibitionEvents);
    }

    @RequestMapping(value = ControllerAndViewMap.ControllerMap.AROUND_MOBILE_FOOD_FACILITIES, method = RequestMethod.GET)
    @ResponseBody
    public String getMobileFoodFacilitiesAround(
                        @RequestParam(required = true, value = "latitude") @NonNull Double latitude,
                        @RequestParam(required = true, value = "longitude") @NonNull Double longitude ,
                        @RequestParam(required = true, value = "radius") @NonNull Double radius) {

        GetAllMobileFoodFacilitiesWithinAreaRequest request = new GetAllMobileFoodFacilitiesWithinAreaRequest();
        Location center = new Location();
        center.setCoordinates(new Coordinates(latitude, longitude));
        double ceil = Math.ceil(radius);
        request.setCenter(center);
        request.setRadius(Math.ceil(radius) <= 0.0 ? 0.0 : Math.ceil(radius) * 1000);
        List<MapExhibitionEvent> mapExhibitionEvents = new ArrayList<>();
        GetAllMobileFoodFacilitiesWithinAreaResponse response = getAllMobileFoodFacilitiesWithinAreaActivity.enact(request);
        if (!CollectionUtils.isEmpty(response.getMobileFoodFacilities())) {
            for (MobileFoodFacility each : response.getMobileFoodFacilities()) {
                try {
                    MapExhibitionEvent exhibitionEvent = mapExhibitionEventTranslator.translate(each);
                    mapExhibitionEvents.add(exhibitionEvent);
                } catch (Exception e) {
                    log.warn("Translation from MobileFoodFacility:[{}] to MapExhibitionEvent failed", gsonUtility.serialize(each));
                }
            }
            /**
             * Google App Engine cannot support JDK 8.
             */
            /*
            response.getMobileFoodFacilities().forEach(each -> {
                try {
                    MapExhibitionEvent exhibitionEvent = mapExhibitionEventTranslator.translate(each);
                    mapExhibitionEvents.add(exhibitionEvent);
                } catch (Exception e) {
                    log.warn("Translation from MobileFoodFacility:[{}] to MapExhibitionEvent failed", gsonUtility.serialize(each));
                }
            });
            */
        }
        return gsonUtility.serialize(mapExhibitionEvents);
    }
}
