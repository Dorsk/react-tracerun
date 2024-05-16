package fr.actia.neveos.services.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actia.neveos.tools.flowchart.InterpretorTool;
import fr.actia.neveos.tools.xml.XmlParserToolXPath;
import fr.actia.neveos.tools.xml.modelesXML.CelNode;
import fr.actia.neveos.tools.xml.modelesXML.MenuNode;
import fr.actia.neveos.tools.xml.modelesXML.TreeNode;
import jakarta.servlet.ServletContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class Home {

    @Autowired
    ServletContext context;

    private List<CelNode> listNodesFinal = new ArrayList<>();

    // Menu de démarage
    @GetMapping(value = "/menu/start", produces = "application/json")
    public MenuNode getItemsfirstMenu() {

        MenuNode menuNode = new MenuNode();
        TreeNode treeNode = new TreeNode();

        // TODO : charger dynamiquement le menu depuis l'espace resource
        // String absolutePath = context.getRealPath("resources/menus");

        treeNode = new XmlParserToolXPath()
                .parseXml("D:/GIT/fullstackNeveos/fullstack-neveos/backend/src/main/resources/menus/menu000_ui2.s");

        menuNode.setTreeNode(treeNode);
        return menuNode;
    }

    @GetMapping(value = "/menu/start/dserver", produces = "application/json")
    public MenuNode getItemsfirstMenuDServer() {

        MenuNode menuNode = new MenuNode();
        TreeNode treeNode = new TreeNode();

        // TODO : charger dynamiquement le menu depuis l'espace resource
        // String absolutePath = context.getRealPath("resources/menus");

        treeNode = new XmlParserToolXPath()
                .parseXml(
                        "D:/GIT/fullstackNeveos/fullstack-neveos/backend/src/main/resources/menus/menu000_ui2_dserver.s");

        menuNode.setTreeNode(treeNode);
        return menuNode;
    }

    // Navigation entre les flowcharts
    @GetMapping(value = "/menu", produces = "application/json")
    public MenuNode getMenu(@RequestParam String path) {

        MenuNode menuNode = new MenuNode();
        TreeNode treeNode = new TreeNode();

        // TODO : charger dynamiquement le menu depuis l'espace resource
        // String absolutePath = context.getRealPath("resources/menus");
        treeNode = new XmlParserToolXPath().parseXml( // "D:/GIT/fullstackNeveos/fullstack-neveos/backend/src/main/resources/menus/diagnostic/menu_vin.s"
                "D:/GIT/fullstackNeveos/fullstack-neveos/backend/src/main/resources/menus/" + path);
        // lecture
        // Find START cell
        List<CelNode> startNode = treeNode.getCels().stream()
                .filter(t -> t.getCelType().equals("Start"))
                .collect(Collectors.toList());
        // Find all cell and contents
        if (!startNode.isEmpty()) {
            listNodesFinal = new ArrayList<CelNode>();
            InterpretorTool interpretTool = new InterpretorTool();
            interpretTool.navigationBetweenNodes(treeNode, startNode.get(0));
            interpretTool.getListNodesFinal().add(startNode.get(0));
            treeNode.setCels(interpretTool.getListNodesFinal());
        }
        // chemin
        // menuNode.setTreeNode(treeNodeFinal);
        menuNode.setTreeNode(treeNode);
        return menuNode;
    }

}
