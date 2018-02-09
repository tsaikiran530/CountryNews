
package de.com.model.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryAct {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<CountryDetail> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CountryDetail> getRows() {
        return rows;
    }

    public void setRows(List<CountryDetail> rows) {
        this.rows = rows;
    }

}
