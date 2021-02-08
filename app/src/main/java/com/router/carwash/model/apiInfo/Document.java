
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Document {

    @Json(name = "place_name")
    private String placeName;
    @Json(name = "distance")
    private String distance;
    @Json(name = "place_url")
    private String placeUrl;
    @Json(name = "category_name")
    private String categoryName;
    @Json(name = "address_name")
    private String addressName;
    @Json(name = "road_address_name")
    private String roadAddressName;
    @Json(name = "id")
    private String id;
    @Json(name = "phone")
    private String phone;
    @Json(name = "category_group_code")
    private String categoryGroupCode;
    @Json(name = "category_group_name")
    private String categoryGroupName;
    @Json(name = "x")
    private Double x;
    @Json(name = "y")
    private Double y;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getRoadAddressName() {
        return roadAddressName;
    }

    public void setRoadAddressName(String roadAddressName) {
        this.roadAddressName = roadAddressName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategoryGroupCode() {
        return categoryGroupCode;
    }

    public void setCategoryGroupCode(String categoryGroupCode) {
        this.categoryGroupCode = categoryGroupCode;
    }

    public String getCategoryGroupName() {
        return categoryGroupName;
    }

    public void setCategoryGroupName(String categoryGroupName) {
        this.categoryGroupName = categoryGroupName;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }



}
