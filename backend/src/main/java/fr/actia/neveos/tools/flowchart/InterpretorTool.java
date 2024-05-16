package fr.actia.neveos.tools.flowchart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import fr.actia.neveos.tools.xml.modelesXML.BufferNameNode;
import fr.actia.neveos.tools.xml.modelesXML.CelNode;
import fr.actia.neveos.tools.xml.modelesXML.TreeNode;

public class InterpretorTool {

    @Autowired
    private Environment env;

    private List<CelNode> listNodesFinal;
    private Map<String, String> mapBufferValues;

    public InterpretorTool() {
        listNodesFinal = new ArrayList<CelNode>();
        mapBufferValues = new HashMap<>();
    }

    public List<CelNode> navigationBetweenNodes(TreeNode treeNode, CelNode startNode) {

        List<CelNode> nodes = treeNode.getCels().stream()
                .filter(t -> (t.getPosc() == (startNode.getDestc()))
                        && (t.getPosl() == (startNode.getDestl())))
                .collect(Collectors.toList());
        List<CelNode> nodesDestOui = treeNode.getCels().stream()
                .filter(t -> (t.getPosc() == (startNode.getDestouic()))
                        && (t.getPosl() == (startNode.getDestouil())))
                .collect(Collectors.toList());
        List<CelNode> nodesDestNon = treeNode.getCels().stream()
                .filter(t -> (t.getPosc() == (startNode.getDestnonc()))
                        && (t.getPosl() == (startNode.getDestnonl())))
                .collect(Collectors.toList());
        nodes.addAll(nodesDestOui);
        nodes.addAll(nodesDestNon);
        if (nodes.isEmpty()) {
            return nodes;
        } else {
            // TODO traitement selon les cellules/nodes trouvés
            for (CelNode node : nodes) {
                switch (node.getCelType()) {
                    case "Act":
                        if (node.getRequest() != null && !node.getRequest().isEmpty()) {
                            updateExecSODVApiCell(node);
                            listNodesFinal.add(node);
                        }
                        break;
                    case "DiagScreen":
                        updateDiagScreenCell(node);
                        listNodesFinal.add(node);
                        break;
                    case "SubTree":
                        updateSubTreeCell(node);
                        listNodesFinal.add(node);
                        break;
                    case "Test":
                        updateTestCell(node);
                        listNodesFinal.add(node);
                        break;
                    case "QsScreen":
                        updateQsScreenCell(node);
                        listNodesFinal.add(node);
                        break;
                    case "End":
                        listNodesFinal.add(node);
                        break;
                }
                navigationBetweenNodes(treeNode, node);
            }
        }
        return nodes;
    }

    private void updateSubTreeCell(CelNode cellNode) {
        cellNode.setBuffervalue(mapBufferValues.get(cellNode.getBuffername()));
    }

    // TODO
    private void updateTestCell(CelNode cellNode) {
        // check OPERATOR
        // Var1 et Var2

        // find destination Node => Cellule Oui ou Non
        // cellNode.setBuffervalue(mapBufferValues.get(cellNode.getBuffername()));
    }

    private void updateDiagScreenCell(CelNode cellNode) {
        cellNode.setBuffervalue(mapBufferValues.get(cellNode.getBuffername()));
        if (cellNode.getListBufferName() != null) {
            for (BufferNameNode buffer : cellNode.getListBufferName()) {
                buffer.setValue(mapBufferValues.get(buffer.getKey()));
            }
        }
    }

    private void updateQsScreenCell(CelNode cellNode) {
        cellNode.setBuffervalue(mapBufferValues.get(cellNode.getBuffername()));
    }

    private void updateExecSODVApiCell(CelNode cellNode) {
        cellNode.setBuffervalue("");
        mapBufferValues.put(cellNode.getBuffername(), cellNode.getBuffervalue());
        /*
         * try {
         * URL url = new URL("http://localhost:8090" + cellNode.getRequest());
         * HttpClient client = HttpClient.newHttpClient();
         * HttpRequest request = HttpRequest.newBuilder()
         * .uri(url.toURI())
         * .header("Content-Type", "application/json")
         * .build();
         * HttpResponse response = client.send(request,
         * HttpResponse.BodyHandlers.ofString());
         * String responseString = (String) response.body();
         * JSONParser parser = new JSONParser();
         * JSONArray json = (JSONArray) parser.parse(responseString);
         * ArrayList<String> listJson = new ArrayList<String>();
         * if (json != null) {
         * int len = json.size();
         * for (int i = 0; i < len; i++) {
         * listJson.add(json.get(i).toString());
         * }
         * }
         * cellNode.setBuffervalue(listJson.get(0).split("data\":\"")[1].split("\"")[0])
         * ;
         * mapBufferValues.put(cellNode.getBuffername(), cellNode.getBuffervalue());
         * } catch (MalformedURLException e) {
         * e.printStackTrace();
         * } catch (IOException e) {
         * e.printStackTrace();
         * } catch (URISyntaxException e) {
         * e.printStackTrace();
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * } catch (ParseException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
    }

    public List<CelNode> getListNodesFinal() {
        return listNodesFinal;
    }

    public void setListNodesFinal(List<CelNode> listNodesFinal) {
        this.listNodesFinal = listNodesFinal;
    }

    public Map<String, String> getMapBufferValues() {
        return mapBufferValues;
    }

    public void setMapBufferValues(Map<String, String> mapBufferValues) {
        this.mapBufferValues = mapBufferValues;
    }
}
