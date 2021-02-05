
package com.router.carwash.model.apiInfo;

import com.squareup.moshi.Json;

public class Meta {

    @Json(name = "same_name")
    private SameName sameName;
    @Json(name = "pageable_count")
    private int pageableCount;
    @Json(name = "total_count")
    private int totalCount;
    @Json(name = "is_end")
    private boolean isEnd;

    public SameName getSameName() {
        return sameName;
    }

    public void setSameName(SameName sameName) {
        this.sameName = sameName;
    }

    public int getPageableCount() {
        return pageableCount;
    }

    public void setPageableCount(int pageableCount) {
        this.pageableCount = pageableCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

}
