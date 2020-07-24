package app.utils;

import app.module.entities.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

//@Component
public class DummyDataProduct {

    @Autowired
    ProductRepository productRepository;

    //@PostConstruct
    public void productPopulation() {

        ArrayList<Product> products = new ArrayList<>();

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        product1.setCode("AA10");
        product1.setName("Mormai Cruiser");
        product1.setDescription("A longboard skate, white, from mormai 2020");
        product1.setPrice(356.90);
        product1.setQuantity(40);
        product1.setWeight(1.2);
        product1.setDimension(4.0);

        product2.setCode("AB200");
        product2.setName("Hellraiser 400");
        product2.setDescription("Light helmet for sports");
        product2.setPrice(75.0);
        product2.setQuantity(500);
        product2.setWeight(0.2);
        product2.setDimension(1.1);

        product3.setCode("CC4");
        product3.setName("Honda Sports X");
        product3.setDescription("Heavy Helmet for motorcycles");
        product3.setPrice(500.0);
        product3.setQuantity(15);
        product3.setWeight(1.2);
        product3.setDimension(2.0);

        product4.setCode("AAD009");
        product4.setName("Razer Kraken");
        product4.setDescription("Profissional gamer headset");
        product4.setPrice(760.0);
        product4.setQuantity(5);
        product4.setWeight(0.2);
        product4.setDimension(0.5);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        for (Product product: products) {
            Product productSaved = productRepository.save(product);
            System.out.println(productSaved.getId());
        }
    }
}
