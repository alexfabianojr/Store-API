package app.controller;

import app.module.entities.Client;
import app.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private List<Client> clients = new ArrayList<>();

    @GetMapping(path = "/findall")
    public List<Client> findAll() {
        if (clients.isEmpty()) {
            clients = clientRepository.findAll();
        }
            return clients;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        if (clients.isEmpty()) {
            clients = clientRepository.findAll();
        }
        if (clients.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(clients.get(Math.toIntExact(id)));
        } else {
            for (Client c : clients) {
                if (c.getId().equals(id)) {
                    return ResponseEntity.ok().body(c);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findbyemail/{email}")
    public ResponseEntity<Client> findByEmail(@PathVariable("email") String email) {
        if (clients.isEmpty()) {
            clients = clientRepository.findAll();
        }
        for (Client c : clients) {
            if (c.getEmail().equals(email)) {
                return ResponseEntity.ok().body(c);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/newclient")
    public Client save(@RequestBody Client client) {
        Client newClient = clientRepository.save(client);
        clients.add(Math.toIntExact(newClient.getId()), newClient);
        return newClient;
    }

    @PostMapping(value = "/update-alldata/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id,
                                         @RequestBody Client client) {
        int index;
        if (clients.isEmpty()) {
            clients = clientRepository.findAll();
        }
        if (clients.get(Math.toIntExact(id)).getId().equals(id)) {
            index = Math.toIntExact(id);
        } else {
            index = clients.indexOf(clientRepository.findById(id).get());
        }
        return clientRepository.findById(id)
                .map(record -> {
                    record.setName(client.getName());
                    record.setGenre(client.getGenre());
                    record.setEmail(client.getEmail());
                    record.setShoppingList(client.getShoppingList());
                    Client update = clientRepository.save(record);
                    clients.remove(index);
                    clients.add(index, update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/update-email/{id}")
    public ResponseEntity<Client> emailUpdateById(@PathVariable("id") Long id,
                                                  @RequestBody Client newData) {
        int index;
        if (clients.isEmpty()) {
            clients = clientRepository.findAll();
        }
        if (clients.get(Math.toIntExact(id)).getId().equals(id)) {
            index = Math.toIntExact(id);
        } else {
            index = clients.indexOf(clientRepository.findById(id).get());
        }
        if (clientRepository.findById(id).isPresent()) {
            Client oldData = clientRepository.findById(id).get();
            return clientRepository.findById(id)
                    .map(record -> {
                        record.setId(id);
                        record.setName(oldData.getName());
                        record.setGenre(oldData.getGenre());
                        record.setEmail(newData.getEmail());
                        record.setShoppingList(oldData.getShoppingList());
                        Client update = clientRepository.save(record);
                        clients.remove(index);
                        clients.add(index, update);
                        return ResponseEntity.ok().body(update);
                    }).orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
