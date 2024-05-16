package fr.actia.neveos.tools.xml.modelesXML;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemNode {

    private int ident;
    private String text;
    private int destc;
    private int destl;

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDestc() {
        return destc;
    }

    public void setDestc(int destc) {
        this.destc = destc;
    }

    public int getDestl() {
        return destl;
    }

    public void setDestl(int destl) {
        this.destl = destl;
    }

}