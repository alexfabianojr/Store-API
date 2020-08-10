package app.services.subservices;

import app.controller.ProductController;
import app.module.entities.ShoppingCart;
import app.module.pojo.Sale;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class SaleListBuilder {
    public static List<Sale> run(List<ShoppingCart> carts) {
        List<Sale> out = new ArrayList<>();
        carts.forEach(e -> out.addAll(e.getSales()));
        return out;
    }
}
