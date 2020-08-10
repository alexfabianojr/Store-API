package app.controller;

import app.module.entities.Client;
import app.module.pojo.Contact;
import app.repository.ClientRepository;
import app.services.DocumentValidator;
import app.services.ReturnClientsByGenre;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private static List<Client> clients = Collections.synchronizedList(new ArrayList<>());

    public synchronized void cache() {
        if (clients.isEmpty() || clients.size() != clientRepository.count()) {
            clients.clear();
            clients = clientRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<Client> findAll() {
        cache();
        return clients;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return clientRepository.findById(id)
                .map(e -> { return ResponseEntity.ok().body(e); })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/findbyemail/{email}")
    public ResponseEntity<Client> findByEmail(@PathVariable("email") String email) {
        cache();
        for (Client c : clients) {
            if (c.getContact().getEmail().equals(email)) return ResponseEntity.ok().body(c);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findbygenre/{genre}")
    public List<Client> findByGenre(@PathVariable("genre") char genre) {
        cache();
        return ReturnClientsByGenre.find(clients, genre);
    }

    @GetMapping(path = "/findbyname/{name}")
    public ResponseEntity<Client> findByName(@PathVariable("name") String name) {
        cache();
        for (Client c : clients) {
            if (c.getPerson().getName().equals(name)) {
                ResponseEntity.ok().body(c);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        if (DocumentValidator.isCPF(client.getPerson().getCpf())) {
            Client newClient = clientRepository.save(client);
            cache();
            clients.add(newClient);
            return ResponseEntity.ok().body(newClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/update-alldata/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id,
                                         @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(c -> {
                    c.setId(id);
                    c.setPerson(client.getPerson());
                    c.setAddress(client.getAddress());
                    c.setContact(client.getContact());
                    c.setShoppingList(clientRepository.findById(id)
                            .orElseThrow()
                            .getShoppingList());
                    int index = (clients.get(Math.toIntExact(id)).getId().equals(id))
                            ? Math.toIntExact(id) : clients.indexOf(clientRepository.findById(id).orElseThrow());
                    Client update = clientRepository.save(c);
                    cache();
                    clients.remove(index);
                    clients.add(index, update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/update-email/{id}")
    public ResponseEntity<Client> emailUpdateById(@PathVariable("id") Long id,
                                                  @RequestBody Contact contactWithNewEmail) {
        if (clientRepository.findById(id).isPresent()) {
            Client oldData = clientRepository.findById(id).get();
            Contact contact = new Contact();
            contact.setCellPhone(oldData.getContact().getCellPhone());
            contact.setEmail(contactWithNewEmail.getEmail());
            contact.setHomephone(oldData.getContact().getHomephone());
            return clientRepository.findById(id)
                    .map(c -> {
                        c.setId(id);
                        c.setPerson(oldData.getPerson());
                        c.setContact(contact);
                        c.setAddress(oldData.getAddress());
                        c.setShoppingList(oldData.getShoppingList());
                        cache();
                        clients.remove(clientRepository.findById(id).orElseThrow());
                        Client update = clientRepository.save(c);
                        clients.add(Math.toIntExact(id), update);
                        return ResponseEntity.ok().body(update);
                    }).orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
