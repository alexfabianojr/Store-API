package app.services;

import app.module.entities.Seller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class ReturnSellersByGenre {
    public static List<Seller> find(List<Seller> sellers, char genre) {
        return sellers.stream()
                .filter(e -> e.getPerson().getGenre() == genre)
                .collect(Collectors.toList());
    }
}
