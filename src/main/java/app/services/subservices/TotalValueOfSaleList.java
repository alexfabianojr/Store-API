package app.services.subservices;

import app.controller.ProductController;
import app.module.entities.Product;
import app.module.pojo.Sale;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class TotalValueOfSaleList {
    public static Double run(List<Sale> sales) {
        ProductController productController = new ProductController();
        List<Product> products = productController.findAll();
        List<Double> doubles = new ArrayList<>();
        for (Sale sl : sales) {
            for (Product p : products) {
                if (p.getId().equals(sl.getProductId())) {
                    doubles.add(p.getPrice() * sl.getProductQuantity());
                }
            }
        }
        return doubles.stream().mapToDouble(d -> d).sum();
    }
}
