
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Snow {

    @Json(name = "3h")
    private double _3h;

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

}
