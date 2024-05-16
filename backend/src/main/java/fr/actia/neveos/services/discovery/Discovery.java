package fr.actia.neveos.services.discovery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actia.neveos.services.response.Data;
import fr.actia.neveos.services.response.Items;
import fr.actia.neveos.services.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class Discovery {

  // get list items by {entity-collection}
  // Available values for {entity-collection} : Area, Component, App, Function
  @GetMapping(value = "/components", produces = "application/json")
  public List<Response> getItemsComponentsCollection() {

    // Simulation API SOVD

    // Envoie de la réponse
    List<Response> entity = new ArrayList<>();
    List<Items> items = new ArrayList<Items>();
    Items item1 = new Items();
    item1.setId("1");
    item1.setName("AAS");
    Items item2 = new Items();
    item2.setId("2");
    item2.setName("CMM_560");
    Response response = new Response();
    items.add(item1);
    items.add(item2);
    response.setItems(items);
    entity.add(response);

    return entity;
  }

  // get list items by {entity-collection}
  // Available values for {entity-collection} : Area, Component, App, Function
  @GetMapping(value = "/areas", produces = "application/json")
  public List<Response> getItemsAresCollection() {
    List<Response> entity = new ArrayList<>();
    List<Items> items = new ArrayList<Items>();
    Items item1 = new Items();
    item1.setId("1");
    item1.setName("Area 1");
    Items item2 = new Items();
    item2.setId("2");
    item2.setName("Area 2");
    Response response = new Response();
    items.add(item1);
    items.add(item2);
    response.setItems(items);
    entity.add(response);

    return entity;
  }

  // bouchon VIN véhicule
  @GetMapping(value = "/components/abs/data/vin", produces = "application/json")
  public List<Response> getComponentAbsDataVin() {
    List<Response> entity = new ArrayList<>();

    Response response = new Response();
    response.setId("12345678321");
    List<Data> dataList = new ArrayList();
    Data data = new Data();
    data.setVin("12345678321");
    dataList.add(data);
    response.setData(dataList);
    entity.add(response);

    return entity;
  }

  // bouchon VIN2 véhicule
  @GetMapping(value = "/components/abs/data/vin2", produces = "application/json")
  public List<Response> getComponentAbsDataVin2() {
    List<Response> entity = new ArrayList<>();

    Response response = new Response();
    response.setId("22222222222222");
    // response.setData("22222222222222");
    entity.add(response);

    return entity;
  }

  // bouchon VIN3 véhicule
  @GetMapping(value = "/components/abs/data/vin3", produces = "application/json")
  public List<Response> getComponentAbsDataVin3() {
    List<Response> entity = new ArrayList<>();

    Response response = new Response();
    response.setId("3333333333");
    // response.setData("3333333333");
    entity.add(response);

    return entity;
  }

  // bouchon Date_Download_year
  @GetMapping(value = "/components/abs/data/Date_Download_year", produces = "application/json")
  public List<Response> getComponentAbsDataDateDownloadyear() {
    List<Response> entity = new ArrayList<>();

    Response response = new Response();
    response.setId("2023");
    // response.setData("2023");
    entity.add(response);

    return entity;
  }

  /*
   * @GetMapping(value = "/areas/{area-id}/subareas", produces =
   * "application/json", consumes = "application/json")
   * public List<Response> getSubAreasByAreaId(@PathVariable("area-id")
   * List<Response> entityId) {
   * return entityId;
   * }
   * 
   * @GetMapping("/components/{component-id}/subcomponents")
   * public List<Response>
   * getSubComponenstByComponentId(@PathVariable("component-id") List<Response>
   * entityId) {
   * return entityId;
   * }
   * 
   * @GetMapping("/{entity-collection}/{entity-id}")
   * public List<Response> getItemsByEntityId(@PathVariable("entity-id")
   * List<Response> entityId) {
   * return entityId;
   * }
   * 
   */
}
