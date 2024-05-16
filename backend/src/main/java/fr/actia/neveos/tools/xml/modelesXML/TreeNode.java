package fr.actia.neveos.tools.xml.modelesXML;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {

    private String name;
    private String onBackExit;
    private String onEnd;
    private String title;
    private String description;
    private List<CelNode> cels = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnBackExit() {
        return onBackExit;
    }

    public void setOnBackExit(String onBackExit) {
        this.onBackExit = onBackExit;
    }

    public String getOnEnd() {
        return onEnd;
    }

    public void setOnEnd(String onEnd) {
        this.onEnd = onEnd;
    }

    public List<CelNode> getCels() {
        return cels;
    }

    public void setCels(List<CelNode> cels) {
        this.cels = cels;
    }

}