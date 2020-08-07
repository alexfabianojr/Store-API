package app.utils;

import app.module.entities.ShoppingCart;
import app.module.pojo.Sale;
import app.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DummyDataShoppingCart {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @PostConstruct
    public void salesListPopulation() {

        System.out.println("Inicializing shopping cart dummy data");

        ArrayList<Sale> listOfSingleSales;
        ShoppingCart shoppingCart1;
        ShoppingCart shoppingCart2;
        ShoppingCart shoppingCart3;
        ShoppingCart shoppingCart4;
        Sale sale1;
        Sale sale2;
        Sale sale3;
        Sale sale4;

        synchronized (this) {
            System.out.println("Shopping cart list caching for dummy data");
            for (int i = 0; i <= 100; i++) {

                listOfSingleSales = new ArrayList<>();
                shoppingCart1 = new ShoppingCart();
                shoppingCart2 = new ShoppingCart();
                shoppingCart3 = new ShoppingCart();
                shoppingCart4 = new ShoppingCart();
                sale1 = new Sale();
                sale2 = new Sale();
                sale3 = new Sale();
                sale4 = new Sale();

                sale1.setProductId(1);
                sale1.setProductQuantity(10);

                sale2.setProductId(2);
                sale2.setProductQuantity(30);

                sale3.setProductId(3);
                sale3.setProductQuantity(20);

                sale4.setProductId(4);
                sale4.setProductQuantity(80);

                listOfSingleSales.add(sale1);
                listOfSingleSales.add(sale2);
                listOfSingleSales.add(sale3);
                listOfSingleSales.add(sale4);

                shoppingCart1.setSalespeopleId((long) 1);
                shoppingCart1.setClientId((long) 1);
                shoppingCart1.setDate(LocalDate.now());
                shoppingCart1.setBill("a8a98sdsa2222sdsad96");
                shoppingCart1.setSales(listOfSingleSales);

                shoppingCart2.setSalespeopleId((long) 2);
                shoppingCart2.setClientId((long) 2);
                shoppingCart2.setDate(LocalDate.now());
                shoppingCart2.setBill("he5211sdaaw8982sda");
                shoppingCart2.setSales(listOfSingleSales);

                shoppingCart3.setSalespeopleId((long) 3);
                shoppingCart3.setClientId((long) 3);
                shoppingCart3.setDate(LocalDate.now());
                shoppingCart3.setBill("fsfasda574w1dad51");
                shoppingCart3.setSales(listOfSingleSales);

                shoppingCart4.setSalespeopleId((long) 4);
                shoppingCart4.setClientId((long) 4);
                shoppingCart4.setDate(LocalDate.now());
                shoppingCart4.setBill("asdsad11fw87wd1a5s");
                shoppingCart4.setSales(listOfSingleSales);

                shoppingCartRepository.save(shoppingCart1);
                shoppingCartRepository.save(shoppingCart2);
                shoppingCartRepository.save(shoppingCart3);
                shoppingCartRepository.save(shoppingCart4);
            }
        }
        System.out.println("Shopping cart dummy data 100%");
    }
}