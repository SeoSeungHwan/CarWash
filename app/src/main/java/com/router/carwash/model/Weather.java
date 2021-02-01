
package com.router.carwash.model;

import com.squareup.moshi.Json;

public class Weather {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
