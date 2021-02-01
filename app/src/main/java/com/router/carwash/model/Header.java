
package com.router.carwash.model;

import com.squareup.moshi.Json;

public class Header {

    @Json(name = "resultCode")
    private String resultCode;
    @Json(name = "resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
