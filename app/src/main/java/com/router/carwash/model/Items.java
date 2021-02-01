
package com.router.carwash.model;

import java.util.List;
import com.squareup.moshi.Json;

public class Items {

    @Json(name = "item")
    private List<Item> item = null;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

}
