package tradeshift.foodfacility.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Component;

import tradeshift.foodfacility.model.Coordinates;

/**
 * @author xuch.
 */
@Component
public class GeoUtility {
    public double getGeoDistance(Coordinates pointA, Coordinates pointB) {
        return getGeoDistance(pointA.getLatitude(), pointA.getLongitude(), pointB.getLatitude(), pointB.getLongitude());
    }

    /**
     * Get the ellipsoidal distance.
     *
     * @return ellipsoidal distance in meters
     */
    public double getGeoDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        return new GeodeticCalculator()
                .calculateGeodeticCurve(
                        Ellipsoid.WGS84,
                        new GlobalPosition(latitudeA, longitudeA, 0.0),
                        new GlobalPosition(latitudeB, longitudeB, 0.0))
                .getEllipsoidalDistance();
    }
}
