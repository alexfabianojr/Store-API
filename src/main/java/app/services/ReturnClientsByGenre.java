package app.services;

import app.module.entities.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class ReturnClientsByGenre {
    public static List<Client> find(List<Client> clients_input, char genre) {
        List<Client> clients_output = new ArrayList<>();
        for (Client c : clients_input) {
            if (c.getGenre() == genre) {
                clients_output.add(c);
            }
        }
        return clients_output;
    }
}
