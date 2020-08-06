package app.services;

import app.module.entities.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class ReturnClientsByGenre {
    public static List<Client> find(List<Client> clients, char genre) {
       return clients.stream()
               .filter(e -> e.getPerson().getGenre() == genre)
               .collect(Collectors.toList());
    }
}
