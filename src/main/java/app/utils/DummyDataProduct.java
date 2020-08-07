package app.utils;

import app.module.entities.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class DummyDataProduct {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void productPopulation() {

        System.out.println("Initializing product dummy data");

        Product product1;
        Product product2;
        Product product3;
        Product product4;

        synchronized (this) {
            System.out.println("Product list caching for dummy data");
            for (int i = 0; i <= 100; i++) {
                product1 = new Product();
                product2 = new Product();
                product3 = new Product();
                product4 = new Product();

                product1.setCode("AA10");
                product1.setName("Cruiser");
                product1.setManufacturer("Mormain");
                product1.setDescription("A longboard skate, white, from mormai 2020");
                product1.setPrice(BigDecimal.valueOf(356.90));
                product1.setQuantity(40);
                product1.setWeight(1.2);
                product1.setDimension(4.0);

                product2.setCode("AB200");
                product2.setName("Hellraiser 400");
                product2.setManufacturer("Honda");
                product2.setDescription("Light helmet for sports");
                product2.setPrice(BigDecimal.valueOf(75.0));
                product2.setQuantity(500);
                product2.setWeight(0.2);
                product2.setDimension(1.1);

                product3.setCode("CC4");
                product3.setName("Sports X");
                product3.setManufacturer("Monster");
                product3.setDescription("Heavy Helmet for motorcycles");
                product3.setPrice(BigDecimal.valueOf(500.0));
                product3.setQuantity(15);
                product3.setWeight(1.2);
                product3.setDimension(2.0);

                product4.setCode("AAD009");
                product4.setName("Kraken");
                product4.setManufacturer("Razer");
                product4.setDescription("Profissional gamer headset");
                product4.setPrice(BigDecimal.valueOf(760.0));
                product4.setQuantity(5);
                product4.setWeight(0.2);
                product4.setDimension(0.5);

                productRepository.save(product1);
                productRepository.save(product2);
                productRepository.save(product3);
                productRepository.save(product4);
            }
        }
        System.out.println("Product dummy data 100%");
    }
}
