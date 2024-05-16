package fr.actia.neveos.tools.xml;

import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import fr.actia.neveos.tools.xml.modelesXML.BufferNameNode;
import fr.actia.neveos.tools.xml.modelesXML.CelNode;
import fr.actia.neveos.tools.xml.modelesXML.TreeNode;

public class XmlParserTool {

    TreeNode tree = new TreeNode();
    CelNode cel = new CelNode();

    public TreeNode parseXml(XMLEventReader reader) {
        while (reader.hasNext()) {
            XMLEvent nextEvent;
            try {
                nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "TREE":
                            Attribute onEnd = startElement.getAttributeByName(new QName("onEnd"));
                            if (onEnd != null) {
                                tree.setOnEnd(onEnd.getValue());
                            }
                            Attribute onBackExit = startElement.getAttributeByName(new QName("onBackExit"));
                            if (onBackExit != null) {
                                this.tree.setOnBackExit(onBackExit.getValue());
                            }
                            break;
                        case "FileName":
                            Attribute name = startElement.getAttributeByName(new QName("name"));
                            if (name != null) {
                                this.tree.setName(name.getValue());
                            }
                            break;
                        case "START":
                            nextEvent = reader.nextEvent();
                            break;
                        case "Act":
                            this.cel = new CelNode();
                            this.cel.setCelType("Act");
                            Attribute id = nextEvent.asStartElement().getAttributeByName(new QName("id"));
                            if (id != null) {
                                this.cel.setId(Integer.valueOf(id.getValue()));
                            }
                            Attribute posc = nextEvent.asStartElement().getAttributeByName(new QName("posc"));
                            if (posc != null) {
                                this.cel.setPosc(Integer.valueOf(posc.getValue()));
                            }
                            Attribute posl = nextEvent.asStartElement().getAttributeByName(new QName("posl"));
                            if (posl != null) {
                                this.cel.setPosl(Integer.valueOf(posl.getValue()));
                            }
                            Attribute destc = nextEvent.asStartElement().getAttributeByName(new QName("destc"));
                            if (destc != null) {
                                this.cel.setDestc(Integer.valueOf(destc.getValue()));
                            }
                            Attribute destl = nextEvent.asStartElement().getAttributeByName(new QName("destl"));
                            if (destl != null) {
                                this.cel.setDestl(Integer.valueOf(destl.getValue()));
                            }
                            break;
                        case "ExecSODVAPI":
                            Attribute request = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("Request"));
                            if (request != null) {
                                this.cel.setRequest(request.getValue());
                            }
                            Attribute httpMethod = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("HttpMethod"));
                            if (httpMethod != null) {
                                this.cel.setHttpmethod(httpMethod.getValue());
                            }
                            break;
                        case "NamedBuffer":
                            Attribute namedBuffer = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("NamedBuffer"));
                            if (namedBuffer != null) {
                                this.cel.setBuffername(namedBuffer.getValue());
                            }
                            if (this.cel.getListBufferName() == null) {
                                this.cel.setListBufferName(new ArrayList<BufferNameNode>());
                            }
                            Attribute nameBuff = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("name"));
                            String nameBuffString = "";
                            String nameScreenBuffString = "";
                            if (nameBuff != null) {
                                nameBuffString = nameBuff.getValue();
                            }
                            Attribute screenName = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("ScreenName"));
                            if (screenName != null) {
                                nameScreenBuffString = screenName.getValue();
                            }
                            BufferNameNode buffNameNode = new BufferNameNode();
                            buffNameNode.setText(nameScreenBuffString);
                            buffNameNode.setText(nameBuffString);
                            this.cel.getListBufferName().add(buffNameNode);
                            break;
                        case "Comment":
                            Attribute comment = nextEvent.asStartElement()
                                    .getAttributeByName(new QName("Comment"));
                            if (comment != null) {
                                this.cel.setDescriptionbuffername(comment.getValue());
                            }

                            break;
                        case "DiagScreen":
                            nextEvent = reader.nextEvent();
                            break;
                        case "End":
                            nextEvent = reader.nextEvent();
                            break;
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Act")) {
                        this.tree.getCels().add(this.cel);
                    }
                }
            } catch (XMLStreamException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return this.tree;
    }
}
