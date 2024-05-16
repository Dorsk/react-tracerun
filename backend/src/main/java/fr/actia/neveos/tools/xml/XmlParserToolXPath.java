package fr.actia.neveos.tools.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;

import fr.actia.neveos.tools.xml.modelesXML.BufferNameNode;
import fr.actia.neveos.tools.xml.modelesXML.CelNode;
import fr.actia.neveos.tools.xml.modelesXML.MenuItemNode;
import fr.actia.neveos.tools.xml.modelesXML.SaisieBufferNameNode;
import fr.actia.neveos.tools.xml.modelesXML.TreeNode;

public class XmlParserToolXPath {

    TreeNode tree = new TreeNode();
    Map<String, String> mapBufferValues = new HashMap<>();

    public TreeNode parseXml(String file) {

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);

            // metadatas
            XPath xpathObject = document.createXPath("//Tree");
            Object obj = xpathObject.evaluate(document.getRootElement());
            getOnEndOnBackExit(obj);

            // Header
            xpathObject = document.createXPath("//Header/IFile/FileName");
            obj = xpathObject.evaluate(document.getRootElement());
            getfileName(obj);
            xpathObject = document.createXPath("//Header/FrontPage");
            obj = xpathObject.evaluate(document.getRootElement());
            getTitleAndDescription(obj);

            // List Cels
            xpathObject = document.createXPath("//Tree/cel/*");
            obj = xpathObject.evaluate(document.getRootElement());
            getListCels(obj);

            // check buffers values
            for (int i = 0; i < tree.getCels().size(); i++) {
                if (tree.getCels().get(i).getCelType().contains("Screen")
                        && mapBufferValues.get(tree.getCels().get(i).getBuffername()) != null) {
                    tree.getCels().get(i).setBuffervalue(mapBufferValues.get(tree.getCels().get(i).getBuffername()));
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (document != null)
            document.clearContent();
        return this.tree;
    }

    //
    // find all cells and extract datas
    //
    void getListCels(Object obj) {
        if (obj instanceof List) {
            List<Node> list = (List<Node>) obj;
            for (Node node : list) {
                CelNode cel = new CelNode();
                DefaultElement defaultElementNode = ((DefaultElement) node);

                cel.setCelType(defaultElementNode.getQualifiedName().toString());
                cel.setId(Integer.valueOf(defaultElementNode.attributeValue("ident")));
                cel.setPosc(Integer.valueOf(defaultElementNode.attributeValue("posc")));
                cel.setPosl(Integer.valueOf(defaultElementNode.attributeValue("posl")));
                // Protection pour les END node qui ont pas de destc et destl
                if (defaultElementNode.attributeValue("destc") != null) {
                    cel.setDestc(Integer.valueOf(defaultElementNode.attributeValue("destc")));
                    cel.setDestl(Integer.valueOf(defaultElementNode.attributeValue("destl")));
                }
                // protection pour les cellules Test et QsScreen
                if (defaultElementNode.attributeValue("destyesc") != null) {
                    cel.setDestouic(Integer.valueOf(defaultElementNode.attributeValue("destyesc")));
                    cel.setDestouil(Integer.valueOf(defaultElementNode.attributeValue("destyesl")));
                    cel.setDestnonc(Integer.valueOf(defaultElementNode.attributeValue("destnoc")));
                    cel.setDestnonl(Integer.valueOf(defaultElementNode.attributeValue("destnol")));
                }

                // Parse les cells
                XPath xpathObject = node.createXPath("./Start");
                Object subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                }

                xpathObject = node.createXPath("./ExecSODVAPI");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setRequest(((DefaultElement) (Node) subObj).attributeValue("Request"));
                    cel.setHttpmethod(((DefaultElement) (Node) subObj).attributeValue("HttpMethod"));

                }
                xpathObject = node.createXPath("./ExecSODVAPI/Comment");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setDescriptionbuffername(((Node) subObj).getText());
                }
                xpathObject = node.createXPath("./ExecSODVAPI/NamedBuffer");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setBuffername(((DefaultElement) (Node) subObj).attributeValue("name"));
                }
                // DiugScreen
                xpathObject = node.createXPath("./ReadECUDataScr/NamedBuffer");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setBuffername(((DefaultElement) (Node) subObj).attributeValue("name"));
                }
                if (subObj instanceof List) {
                    List<Node> listMenuItem = (List<Node>) subObj;
                    for (Node nodeMenuItem : listMenuItem) {
                        if (nodeMenuItem instanceof DefaultElement) {
                            if (cel.getListBufferName() == null) {
                                cel.setListBufferName(new ArrayList<BufferNameNode>());
                            }
                            BufferNameNode buffNameNode = new BufferNameNode();
                            buffNameNode.setText(((DefaultElement) (Node) nodeMenuItem).attributeValue("ScreenName"));
                            buffNameNode.setKey(((DefaultElement) (Node) nodeMenuItem).attributeValue("name"));
                            cel.getListBufferName().add(buffNameNode);
                        }
                    }
                }
                xpathObject = node.createXPath("./NamedBuffer");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setBuffername(((DefaultElement) (Node) subObj).attributeValue("name"));
                }

                xpathObject = node.createXPath("./Saisie/Variable");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    if (cel.getListSaisieBufferNameNode() == null) {
                        cel.setListSaisieBufferNameNode(new ArrayList<SaisieBufferNameNode>());
                    }
                    SaisieBufferNameNode saisieBuffer = new SaisieBufferNameNode();
                    saisieBuffer.setKey(((DefaultElement) (Node) subObj).attributeValue("name"));
                    saisieBuffer.setText(((DefaultElement) (Node) subObj).attributeValue("screenText"));
                    saisieBuffer.setValue("");
                    cel.getListSaisieBufferNameNode().add(saisieBuffer);
                }

                xpathObject = node.createXPath("./Navigation");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    DefaultElement subObjnODE = (DefaultElement) subObj;
                    if (subObjnODE.attributeValue("id") != null)
                        cel.setIdNavigation(Integer.valueOf(subObjnODE.attributeValue("id")));
                    if (subObjnODE.attributeValue("toId") != null)
                        cel.setToIdNavigation(Integer.valueOf(subObjnODE.attributeValue("toId")));
                    if (subObjnODE.attributeValue("backToId") != null)
                        cel.setBackToIdNavigation(
                                Integer.valueOf(subObjnODE.attributeValue("backToId")));
                }
                // menuScreen

                xpathObject = node.createXPath("./MenuItem");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof List) {
                    // List<Node> listMenuItem = (List<Node>) subObj;
                    for (Node nodeMenuItem : (List<Node>) subObj) {
                        if (nodeMenuItem instanceof DefaultElement) {
                            if (cel.getListMenuItems() == null) {
                                cel.setListMenuItems(new ArrayList<MenuItemNode>());
                            }
                            MenuItemNode menuItem = new MenuItemNode();
                            menuItem.setIdent(
                                    Integer.valueOf(((DefaultElement) (Node) nodeMenuItem).attributeValue("ident")));
                            XPath xpathFreeText = nodeMenuItem.createXPath(".//LabelContent/Text");
                            Object item = xpathFreeText.evaluate(nodeMenuItem);
                            if (item instanceof DefaultElement) {
                                menuItem.setText(((DefaultElement) (Node) item).getText());
                            }
                            XPath xpathTargetCell = ((DefaultElement) (Node) nodeMenuItem).createXPath(".//TargetCell");
                            Object itemTargetCell = xpathTargetCell.evaluate(nodeMenuItem);
                            if (itemTargetCell instanceof DefaultElement) {
                                menuItem.setDestc(Integer
                                        .valueOf(((DefaultElement) (Node) itemTargetCell).attributeValue("destc")));
                                menuItem.setDestl(Integer
                                        .valueOf(((DefaultElement) (Node) itemTargetCell).attributeValue("destl")));
                            }

                            cel.getListMenuItems().add(menuItem);
                        }
                    }
                }
                // Comment
                xpathObject = node.createXPath("./Comment");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setComment(((DefaultElement) (Node) subObj).getText());
                }
                // SubTree
                if (cel.getCelType().equals("SubTree") && node instanceof DefaultElement) {
                    cel.setFileName(((DefaultElement) (Node) node).attributeValue("FileName"));

                    xpathObject = node.createXPath("./FilePath");
                    subObj = xpathObject.evaluate(node);
                    if (cel.getCelType().equals("SubTree") && subObj instanceof DefaultElement) {
                        cel.setFilePath(((DefaultElement) (Node) subObj).attributeValue("path"));
                    }
                }
                // récupération des info sur les cellules TEST
                // Operation : (EQUAL|NOT EQUAL|SUP|INF)
                xpathObject = node.createXPath("./Test/Operation");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setTestOperation(((DefaultElement) (Node) node).attributeValue("nom"));
                }
                xpathObject = node.createXPath("./Test/Buffer");
                subObj = xpathObject.evaluate(node);
                if (obj instanceof List) {
                    List<Node> listBuffer = (List<Node>) obj;
                    for (Node nodeBuffer : listBuffer) {
                        cel.setTestOperation(((DefaultElement) nodeBuffer).attributeValue("nom"));
                    }
                } else if (subObj instanceof DefaultElement) {
                    cel.setTestOperation(((DefaultElement) (Node) node).attributeValue("nom"));
                }
                xpathObject = node.createXPath("./Test/String");
                subObj = xpathObject.evaluate(node);
                if (obj instanceof List) {
                    List<Node> listString = (List<Node>) obj;
                    for (Node nodeString : listString) {
                        cel.setTestOperation(((DefaultElement) nodeString).attributeValue("nom"));
                    }
                } else if (subObj instanceof DefaultElement) {
                    cel.setTestOperation(((DefaultElement) (Node) node).attributeValue("nom"));
                }
                // QsScreen
                if (defaultElementNode.attributeValue("WindowStyle") != null) {
                    cel.setWindowStyle(defaultElementNode.attributeValue("WindowStyle"));
                }
                // QsScreen : BackBtn (NO|YES) #IMPLIED
                if (defaultElementNode.attributeValue("BackBtn") != null) {
                    cel.setBackBtn(defaultElementNode.attributeValue("BackBtn"));
                }
                // QsScreen : MsgType (QUESTION|INFO|WARNING|ERROR) #IMPLIED
                if (defaultElementNode.attributeValue("MsgType") != null) {
                    cel.setMsgType(defaultElementNode.attributeValue("MsgType"));
                }
                xpathObject = node.createXPath("./ScreenTitle");
                subObj = xpathObject.evaluate(node);
                if (subObj instanceof DefaultElement) {
                    cel.setTitleScreen(((DefaultElement) (Node) node).attributeValue("title"));
                    cel.setDescription(((DefaultElement) (Node) node).attributeValue("description"));
                    cel.setContentMessage(((Node) subObj).getText());
                }

                this.tree.getCels().add(cel);
            }
        }
    }

    void getOnEndOnBackExit(Object obj) {
        if (obj instanceof DefaultElement) {
            Node node = (Node) obj;
            this.tree.setOnEnd(((DefaultElement) node).attributeValue("onEnd"));
            this.tree.setOnBackExit(((DefaultElement) node).attributeValue("onBackExit"));
        }
    }

    void getfileName(Object obj) {
        if (obj instanceof DefaultElement) {
            Node node = (Node) obj;
            this.tree.setName(((DefaultElement) node).attributeValue("name"));
        }
    }

    void getTitleAndDescription(Object obj) {
        if (obj instanceof DefaultElement) {
            Node node = (Node) obj;
            this.tree.setTitle(((DefaultElement) node).attributeValue("title"));
            this.tree.setDescription(((DefaultElement) node).attributeValue("description"));
        }
    }

    void getfileNameSubTree(Object obj) {
        if (obj instanceof DefaultElement) {
            Node node = (Node) obj;
            this.tree.setName(((DefaultElement) node).attributeValue("FileName"));
        }
    }

    /** Exemple de parsing selon le type de node */
    void checkerNode(Object obj) {
        if (obj instanceof List) {
            List<Node> list = (List<Node>) obj;
            for (Node node : list) {
                node.getText();
            }
        } else if (obj instanceof DefaultElement) {
            Node node = (Node) obj;
            node.getText();
            ((DefaultElement) node).attributeValue("name");
        } else if (obj instanceof DefaultAttribute) {
            Node node = (Node) obj;
            node.getText();
        } else if (obj instanceof DefaultText) {
            Node node = (Node) obj;
            node.getText();
        }
    }
}
