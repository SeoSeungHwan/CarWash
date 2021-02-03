
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class WeatherInfo {

    @Json(name = "cod")
    private String cod;
    @Json(name = "message")
    private int message;
    @Json(name = "cnt")
    private int cnt;
    @Json(name = "list")
    private java.util.List<List> list = null;
    @Json(name = "city")
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getListitem() {
        return list;
    }

    public void setListitem(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
