
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Wind {

    @Json(name = "speed")
    private double speed;
    @Json(name = "deg")
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

}
