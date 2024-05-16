package fr.actia.neveos.services.response;

import java.util.ArrayList;
import java.util.List;
import fr.actia.neveos.services.response.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Items> items = new ArrayList<Items>();
    private String id;
    private List<Data> data = new ArrayList<Data>();

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
