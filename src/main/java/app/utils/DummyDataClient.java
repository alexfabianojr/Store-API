package app.utils;

import app.module.entities.Client;
import app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

//@Component
public class DummyDataClient {

    @Autowired
    ClientRepository clientRepository;

    //@PostConstruct
    public void clientPopulation() {

        ArrayList<Client> clients = new ArrayList<>();

        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Client client4 = new Client();

        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();
        ArrayList<Integer> integers3 = new ArrayList<>();
        ArrayList<Integer> integers4 = new ArrayList<>();

        integers1.add(1);
        integers1.add(2);

        integers2.add(3);
        integers2.add(4);
        integers2.add(5);

        integers3.add(6);

        integers4.add(7);
        integers4.add(8);
        integers4.add(9);
        integers4.add(10);
        integers4.add(11);

        client1.setName("Alex Fabiano");
        client1.setGenre('M');
        client1.setEmail("alex@blabla.com");
        client1.setShoppingList(integers1);

        client2.setName("Frederico Loth");
        client2.setGenre('M');
        client2.setEmail("frederico@blabla.com");
        client2.setShoppingList(integers2);

        client3.setName("Manuela Silva");
        client3.setGenre('F');
        client3.setEmail("manuela@blabla.com");
        client3.setShoppingList(integers3);

        client4.setName("Aline Martin");
        client4.setGenre('F');
        client4.setEmail("aline@blabla.com");
        client4.setShoppingList(integers4);
        
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);

        for (Client client: clients) {
            Client clientSaved = clientRepository.save(client);
            System.out.println(clientSaved.getId());
        }
    }
}