package app.services.subservices;

import app.module.entities.ShoppingCart;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class ShoppingCartListById {
    public static List<ShoppingCart> run(List<ShoppingCart> carts, Long id) {
        return carts.stream()
                .filter(e -> e.getSalespeopleId().equals(id))
                .collect(Collectors.toList());
    }
}
