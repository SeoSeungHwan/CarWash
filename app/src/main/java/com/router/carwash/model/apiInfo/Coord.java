
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Coord {

    @Json(name = "lat")
    private double lat;
    @Json(name = "lon")
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}
