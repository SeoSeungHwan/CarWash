
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Clouds {

    @Json(name = "all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
