
package com.router.carwash.model.apiInfo;

import java.util.List;
import com.squareup.moshi.Json;

public class SameName {

    @Json(name = "region")
    private List<Object> region = null;
    @Json(name = "keyword")
    private String keyword;
    @Json(name = "selected_region")
    private String selectedRegion;

    public List<Object> getRegion() {
        return region;
    }

    public void setRegion(List<Object> region) {
        this.region = region;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

}
