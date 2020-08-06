//package app.utils;
//
//import app.module.entities.Client;
//import app.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//
//@Component
//public class DummyDataClient {
//
//    @Autowired
//    ClientRepository clientRepository;
//
//    @PostConstruct
//    public void clientPopulation() {
//
//        ArrayList<Client> clients = new ArrayList<>();
//
//        Client client1 = new Client();
//        Client client2 = new Client();
//        Client client3 = new Client();
//        Client client4 = new Client();
//
//        ArrayList<Long> integers1 = new ArrayList<>();
//        ArrayList<Long> integers2 = new ArrayList<>();
//        ArrayList<Long> integers3 = new ArrayList<>();
//        ArrayList<Long> integers4 = new ArrayList<>();
//
//        integers1.add((long) 1);
//        integers1.add((long) 2);
//
//        integers2.add((long) 3);
//        integers2.add((long) 4);
//        integers2.add((long) 5);
//
//        integers3.add((long) 6);
//
//        integers4.add((long) 7);
//        integers4.add((long) 8);
//        integers4.add((long) 9);
//        integers4.add((long) 10);
//        integers4.add((long) 11);
//
//        client1.setName("Alex Fabiano");
//        client1.setGenre('M');
//        client1.setEmail("alex@blabla.com");
//        client1.setShoppingList(integers1);
//
//        client2.setName("Frederico Loth");
//        client2.setGenre('M');
//        client2.setEmail("frederico@blabla.com");
//        client2.setShoppingList(integers2);
//
//        client3.setName("Manuela Silva");
//        client3.setGenre('F');
//        client3.setEmail("manuela@blabla.com");
//        client3.setShoppingList(integers3);
//
//        client4.setName("Aline Martin");
//        client4.setGenre('F');
//        client4.setEmail("aline@blabla.com");
//        client4.setShoppingList(integers4);
//
//        clients.add(client1);
//        clients.add(client2);
//        clients.add(client3);
//        clients.add(client4);
//
//        for (Client client: clients) {
//            Client clientSaved = clientRepository.save(client);
//            System.out.println(clientSaved.getId());
//        }
//    }
//}