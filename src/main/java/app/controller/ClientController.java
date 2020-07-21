package app.controller;

import app.module.entities.Client;
import app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
