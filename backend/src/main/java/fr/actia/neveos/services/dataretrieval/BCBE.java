package fr.actia.neveos.services.dataretrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import fr.actia.neveos.services.response.Response;
import jakarta.servlet.ServletContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/components/bceb")
public class BCBE {

    @Autowired
    ServletContext context;

    /*
     * <NamedBuffer name="Fournisseur"
     * ScreenName="Nom du fournisseur de la batterie"/>
     * <NamedBuffer name="moteurEV" ScreenName="Type de moteur"/>
     * <NamedBuffer name="techBatterie"
     * ScreenName="Type de technologie de la batterie"/>
     * <NamedBuffer name="typeBetterieConnect"
     * ScreenName="Type de batterie connectée"/>
     * <NamedBuffer name="stateBatterie" ScreenName="Etat de charge batterie"/>
     * <NamedBuffer name="precisionEtatCharge"
     * ScreenName="Précision de la valeur de l'etat de charge batterie"/>
     * <NamedBuffer name="tempBatterie" ScreenName="Température batterie"/>
     * <NamedBuffer name="tensionBatterie" ScreenName="Tension batterie"/>
     * <NamedBuffer name="courantVeilleMini"
     * ScreenName="Courant de veille minimum"/>
     * <NamedBuffer name="compteurChargeDecharge"
     * ScreenName="Compteur de charge et de décharge de la batterie de servitude"/>
     * <NamedBuffer name="codeBK" ScreenName="Code BK"/>
     * </ReadECUDataScr>
     */

    // bouchon Fournisseur
    @GetMapping(value = "/data/fournisseur", produces = "application/json")
    public List<Response> getComponentAbsDataFournisseur() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("VARTA");
        // response.setData("VARTA");
        entity.add(response);

        return entity;
    }

    // bouchon Fournisseur
    @GetMapping(value = "/data/moteurEV", produces = "application/json")
    public List<Response> getComponentAbsDatamoteurEV() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("moteurEV");
        // response.setData("moteur EV ");
        entity.add(response);

        return entity;
    }

    // bouchon Fournisseur
    @GetMapping(value = "/data/techBatterie", produces = "application/json")
    public List<Response> getComponentAbsDatatechBatterie() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("Liquidecyclablerenforc");
        // response.setData("Liquide cyclable renforc\u00E9");
        entity.add(response);

        return entity;
    }

    // bouchon Fournisseur
    @GetMapping(value = "/data/typeBetterieConnect", produces = "application/json")
    public List<Response> getComponentAbstypeBetterieConnect() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("42AmpHeure");
        // response.setData("42 Amp\u00E8re heure");
        entity.add(response);

        return entity;
    }

    // bouchon Fournisseur
    @GetMapping(value = "/data/stateBatterie", produces = "application/json")
    public List<Response> getComponentAbsDatastateBatterie() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("100pourcent");
        // response.setData("100 %");
        entity.add(response);

        return entity;
    }

    // bouchon precisionEtatCharge
    @GetMapping(value = "/data/precisionEtatCharge", produces = "application/json")
    public List<Response> getComponentAbsDataprecisionEtatCharge() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("nominal");
        // response.setData("nominal (< 5%)");
        entity.add(response);

        return entity;
    }

    // bouchon tempBatterie
    @GetMapping(value = "/data/tempBatterie", produces = "application/json")
    public List<Response> getComponentAbsDatatempBatterie() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("20C");
        // response.setData("20 \u00B0C");
        entity.add(response);

        return entity;
    }

    // bouchon tensionBatterie
    @GetMapping(value = "/data/tensionBatterie", produces = "application/json")
    public List<Response> getComponentAbsData() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("12V");
        // response.setData("12 V");
        entity.add(response);

        return entity;
    }

    // bouchon courantVeilleMini
    @GetMapping(value = "/data/courantVeilleMini", produces = "application/json")
    public List<Response> getComponentAbsDatacourantVeilleMini() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("Courant supérieur à 1 A ");
        // response.setData("Courant supérieur à 1 A ");
        entity.add(response);

        return entity;
    }

    // bouchon compteurChargeDecharge
    @GetMapping(value = "/data/compteurChargeDecharge", produces = "application/json")
    public List<Response> getComponentAbsDatacompteurChargeDecharge() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("10Ah");
        // response.setData("10 Ah");
        entity.add(response);

        return entity;
    }

    // bouchon codeBK
    @GetMapping(value = "/data/codeBK", produces = "application/json")
    public List<Response> getComponentAbsDatacodeBK() {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("XX");
        String value = "XX";
        try {
            FileReader reader = new FileReader("D:/Docs/SOVD/tmpCodeBK.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                value = line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // response.setData(value);
        entity.add(response);

        return entity;
    }

    // bouchon codeBK
    @PutMapping(value = "/data/codeBK", produces = "application/json")
    public List<Response> putComponentAbsDatacodeBK(@RequestParam String value) {
        List<Response> entity = new ArrayList<>();

        Response response = new Response();
        response.setId("XX");
        try {
            if (new File("D:/Docs/SOVD/tmpCodeBK.txt").exists())
                new File("D:/Docs/SOVD/tmpCodeBK.txt").delete();
            FileWriter writer = new FileWriter(new File("D:/Docs/SOVD/tmpCodeBK.txt"), true);
            writer.write(value);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // response.setData(value);
        entity.add(response);

        return entity;
    }
}
