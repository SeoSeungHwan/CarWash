
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class City {

    @Json(name = "id")
    private int id;
    @Json(name = "name")
    private String name;
    @Json(name = "coord")
    private Coord coord;
    @Json(name = "country")
    private String country;
    @Json(name = "population")
    private int population;
    @Json(name = "timezone")
    private int timezone;
    @Json(name = "sunrise")
    private int sunrise;
    @Json(name = "sunset")
    private int sunset;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

}
