package fr.actia.neveos.tools.xml.modelesXML;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CelNode {

    private String celtype;
    private int id;
    private int posl;
    private int posc;
    private int destc;
    private int destl;
    private int destouic;
    private int destouil;
    private int destnonc;
    private int destnonl;
    // http Sovd call api
    private String request;
    private String httpmethod;
    private String descriptionbuffername;
    private String buffername;
    private String buffervalue;
    // subtree
    private String fileName;
    private String filePath;
    private String comment;
    private String testOperation;
    // diagscreen
    private int idNavigation;
    private int backToIdNavigation;
    private int toIdNavigation;
    // QsSreeen
    private String windowStyle;
    private String backBtn;
    private String MsgType;
    private String titleScreen;
    private String description;
    private String contentMessage;
    // MenuScreen MenuItems
    private List<MenuItemNode> listMenuItems;
    private List<BufferNameNode> listBufferName;
    // Saisie / input utilisateur
    private List<SaisieBufferNameNode> listSaisieBufferNameNode;

    public List<SaisieBufferNameNode> getListSaisieBufferNameNode() {
        return listSaisieBufferNameNode;
    }

    public void setListSaisieBufferNameNode(List<SaisieBufferNameNode> listSaisieBufferNameNode) {
        this.listSaisieBufferNameNode = listSaisieBufferNameNode;
    }

    public List<BufferNameNode> getListBufferName() {
        return listBufferName;
    }

    public void setListBufferName(List<BufferNameNode> listBufferName) {
        this.listBufferName = listBufferName;
    }

    public List<MenuItemNode> getListMenuItems() {
        return listMenuItems;
    }

    public void setListMenuItems(List<MenuItemNode> listMenuItems) {
        this.listMenuItems = listMenuItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleScreen() {
        return titleScreen;
    }

    public void setTitleScreen(String titleScreen) {
        this.titleScreen = titleScreen;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(String backBtn) {
        this.backBtn = backBtn;
    }

    public String getWindowStyle() {
        return windowStyle;
    }

    public void setWindowStyle(String windowStyle) {
        this.windowStyle = windowStyle;
    }

    public int getBackToIdNavigation() {
        return backToIdNavigation;
    }

    public void setBackToIdNavigation(int backToIdNavigation) {
        this.backToIdNavigation = backToIdNavigation;
    }

    public int getIdNavigation() {
        return idNavigation;
    }

    public void setIdNavigation(int idNavigation) {
        this.idNavigation = idNavigation;
    }

    public int getToIdNavigation() {
        return toIdNavigation;
    }

    public void setToIdNavigation(int toIdNavigation) {
        this.toIdNavigation = toIdNavigation;
    }

    public String getTestOperation() {
        return testOperation;
    }

    public void setTestOperation(String testOperation) {
        this.testOperation = testOperation;
    }

    public int getDestnonl() {
        return destnonl;
    }

    public void setDestnonl(int destnonl) {
        this.destnonl = destnonl;
    }

    public int getDestnonc() {
        return destnonc;
    }

    public void setDestnonc(int destnonc) {
        this.destnonc = destnonc;
    }

    public int getDestouil() {
        return destouil;
    }

    public void setDestouil(int destouil) {
        this.destouil = destouil;
    }

    public int getDestouic() {
        return destouic;
    }

    public void setDestouic(int destouic) {
        this.destouic = destouic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosc() {
        return posc;
    }

    public void setPosc(int posc) {
        this.posc = posc;
    }

    public int getPosl() {
        return posl;
    }

    public void setPosl(int posl) {
        this.posl = posl;
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

    public String getCelType() {
        return this.celtype;
    }

    public void setCelType(String celtype) {
        this.celtype = celtype;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getHttpmethod() {
        return httpmethod;
    }

    public void setHttpmethod(String httpmethod) {
        this.httpmethod = httpmethod;
    }

    public String getDescriptionbuffername() {
        return descriptionbuffername;
    }

    public void setDescriptionbuffername(String descriptionbuffername) {
        this.descriptionbuffername = descriptionbuffername;
    }

    public String getBuffername() {
        return buffername;
    }

    public void setBuffername(String buffername) {
        this.buffername = buffername;
    }

    public String getBuffervalue() {
        return this.buffervalue;
    }

    public void setBuffervalue(String buffervalue) {
        this.buffervalue = buffervalue;
    }
}