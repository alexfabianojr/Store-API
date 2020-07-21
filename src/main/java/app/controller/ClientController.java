package app.controller;

import app.repository.ClientRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store-api-client")
public class ClientController {

    private ClientRepository clientRepository;
}
