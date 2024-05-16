package fr.actia.neveos.services.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private String vin;
    private String name;
    private String translation_id;
    private String href;

    // Getters Setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(String translation_id) {
        this.translation_id = translation_id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}