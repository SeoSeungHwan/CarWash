
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class List {

    @Json(name = "dt")
    private int dt;
    @Json(name = "main")
    private Main main;
    @Json(name = "weather")
    private java.util.List<Weather> weather = null;
    @Json(name = "clouds")
    private Clouds clouds;
    @Json(name = "wind")
    private Wind wind;
    @Json(name = "visibility")
    private int visibility;
    @Json(name = "pop")
    private double pop;
    @Json(name = "sys")
    private Sys sys;
    @Json(name = "dt_txt")
    private String dtTxt;
    @Json(name = "snow")
    private Snow snow;
    @Json(name = "rain")
    private Rain rain;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

}
