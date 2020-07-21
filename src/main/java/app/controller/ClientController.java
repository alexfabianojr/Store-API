package app.controller;

import app.module.entities.Client;
import app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api-client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(path = "/findall")
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(Long id) {
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public Client save(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(record -> {
                    record.setName(client.getName());
                    record.setGenre(client.getGenre());
                    record.setEmail(client.getEmail());
                    record.setShoppingList(client.getShoppingList());
                    Client update = clientRepository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
