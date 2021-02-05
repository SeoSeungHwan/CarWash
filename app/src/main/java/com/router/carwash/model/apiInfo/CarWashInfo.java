
package com.router.carwash.model.apiInfo;

import java.util.List;
import com.squareup.moshi.Json;

public class CarWashInfo {

    @Json(name = "meta")
    private Meta meta;
    @Json(name = "documents")
    private List<Document> documents = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}
