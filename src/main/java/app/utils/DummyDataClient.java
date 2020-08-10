//package app.utils;
//
//import app.module.entities.Client;
//import app.module.pojo.Address;
//import app.module.pojo.Contact;
//import app.module.pojo.Person;
//import app.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDate;
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
//        System.out.println("Inicializing client dummy data");
//
//        Client client;
//        Person person;
//        Address address;
//        Contact contact;
//        ArrayList<Long> longs;
//
//        synchronized (this) {
//            System.out.println("Client list caching for dummy data");
//            for (int i = 0; i <= 3; i++) {
//
//                client = new Client();
//
//                person = new Person();
//                person.setName("Generic Client");
//                person.setGenre('M');
//                person.setCpf("43907001036");
//                person.setBirthDate(LocalDate.of(1990,4,11));
//
//                address = new Address();
//                address.setCep("95680971");
//                address.setCity("Canela");
//                address.setState("Rio Grande do Sul");
//                address.setStreet("Rua Dom Luiz Guanella 109");
//                address.setNeighborhood("São José");
//                address.setNumber("150");
//                address.setComplement("Casa");
//                address.setMainAddress(true);
//
//                contact = new Contact();
//                contact.setCellPhone("99879461");
//                contact.setHomephone("3354623");
//                contact.setEmail("emailtestador@gmail.com");
//
//                longs = new ArrayList<>();
//                longs.add((long) 7);
//                longs.add((long) 8);
//                longs.add((long) 9);
//                longs.add((long) 10);
//                longs.add((long) 11);
//
//                client.setPerson(person);
//                client.setAddress(address);
//                client.setContact(contact);
//                client.setShoppingList(longs);
//
//                clientRepository.save(client);
//            }
//        }
//        System.out.println("Client dummy data 100%");
//    }
//}