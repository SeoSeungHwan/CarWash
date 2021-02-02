
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Main {

    @Json(name = "temp")
    private double temp;
    @Json(name = "feels_like")
    private double feelsLike;
    @Json(name = "temp_min")
    private double tempMin;
    @Json(name = "temp_max")
    private double tempMax;
    @Json(name = "pressure")
    private int pressure;
    @Json(name = "sea_level")
    private int seaLevel;
    @Json(name = "grnd_level")
    private int grndLevel;
    @Json(name = "humidity")
    private int humidity;
    @Json(name = "temp_kf")
    private double tempKf;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(int seaLevel) {
        this.seaLevel = seaLevel;
    }

    public int getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(int grndLevel) {
        this.grndLevel = grndLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    public void setTempKf(int tempKf) {
        this.tempKf = tempKf;
    }

}
