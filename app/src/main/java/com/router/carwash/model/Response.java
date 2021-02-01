
package com.router.carwash.model;

import com.squareup.moshi.Json;

public class Response {

    @Json(name = "header")
    private Header header;
    @Json(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
