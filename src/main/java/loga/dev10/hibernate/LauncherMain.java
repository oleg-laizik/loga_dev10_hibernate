package loga.dev10.hibernate;

import loga.dev10.hibernate.properties.Client;
import loga.dev10.hibernate.properties.Planet;
import loga.dev10.hibernate.service.ClientCrudService;
import loga.dev10.hibernate.service.PlanetCrudService;
import loga.dev10.hibernate.utils.HibernateUtil;
import loga.dev10.hibernate.utils.MigrationUtil;

public class LauncherMain {

    public static void main(String[] args) {
        MigrationUtil.main(new String[]{});

        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();

        Client client1 = new Client("John");
        Client client2 = new Client("Alice");
        Client client3 = new Client("Bob");
        planetCrudService.createPlanet(new Planet("MARS", "Mars"));
        planetCrudService.createPlanet(new Planet("ERTH", "Earth"));
        clientCrudService.createClient(new Client("Client 12"));
        System.out.println(clientCrudService.getClientById(11));
        System.out.println("Client 1: " + clientCrudService.getClientById(1));
        System.out.println("Client 2: " + clientCrudService.getClientById(2));
        System.out.println("Client 3: " + clientCrudService.getClientById(3));
        System.out.println("Planet MARS: " + planetCrudService.getPlanetById("MARS"));
        System.out.println("Planet ERTH: " + planetCrudService.getPlanetById("ERTH"));
        clientCrudService.updateClientById(1, "Updated John");
        clientCrudService.updateClientById(3, "Updated Bob");
        planetCrudService.updatePlanetById("MARS", "Updated Mars");
        clientCrudService.deleteClientById(2);
        planetCrudService.deletePlanetById("ERTH");
        System.out.println("All Clients: " + clientCrudService.getAll());
        System.out.println("All Planets: " + planetCrudService.getAll());
    }
}
