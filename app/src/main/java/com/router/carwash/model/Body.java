
package com.router.carwash.model;

import com.squareup.moshi.Json;

public class Body {

    @Json(name = "dataType")
    private String dataType;
    @Json(name = "items")
    private Items items;
    @Json(name = "pageNo")
    private int pageNo;
    @Json(name = "numOfRows")
    private int numOfRows;
    @Json(name = "totalCount")
    private int totalCount;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
