//package app.utils;
//
//import app.module.entities.ShoppingCart;
//import app.module.pojo.Sale;
//import app.repository.ShoppingCartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//
//@Component
//public class DummyDataShoppingCart {
//
//    @Autowired
//    ShoppingCartRepository shoppingCartRepository;
//
//    @PostConstruct
//    public void salesListPopulation() {
//
//        ArrayList<ShoppingCart> shoppingCart = new ArrayList<>();
//        ArrayList<Sale> listOfSingleSales = new ArrayList<>();
//
//        ShoppingCart shoppingCart1 = new ShoppingCart();
//        ShoppingCart shoppingCart2 = new ShoppingCart();
//        ShoppingCart shoppingCart3 = new ShoppingCart();
//        ShoppingCart shoppingCart4 = new ShoppingCart();
//
//        Sale sale1 = new Sale();
//        Sale sale2 = new Sale();
//        Sale sale3 = new Sale();
//        Sale sale4 = new Sale();
//
//        sale1.setProductId(1);
//        sale1.setProductQuantity(10);
//
//        sale2.setProductId(2);
//        sale2.setProductQuantity(30);
//
//        sale3.setProductId(3);
//        sale3.setProductQuantity(20);
//
//        sale4.setProductId(4);
//        sale4.setProductQuantity(80);
//
//        listOfSingleSales.add(sale1);
//        listOfSingleSales.add(sale2);
//        listOfSingleSales.add(sale3);
//        listOfSingleSales.add(sale4);
//
//        shoppingCart1.setSalespeopleId((long) 1);
//        shoppingCart1.setClientId((long) 1);
//        shoppingCart1.setSales(listOfSingleSales);
//
//        shoppingCart2.setSalespeopleId((long) 2);
//        shoppingCart2.setClientId((long) 2);
//        shoppingCart2.setSales(listOfSingleSales);
//
//        shoppingCart3.setSalespeopleId((long) 3);
//        shoppingCart3.setClientId((long) 3);
//        shoppingCart3.setSales(listOfSingleSales);
//
//        shoppingCart4.setSalespeopleId((long) 4);
//        shoppingCart4.setClientId((long) 4);
//        shoppingCart4.setSales(listOfSingleSales);
//
//        shoppingCart.add(shoppingCart1);
//        shoppingCart.add(shoppingCart2);
//        shoppingCart.add(shoppingCart3);
//        shoppingCart.add(shoppingCart4);
//
//        for (ShoppingCart shoppingCartData : shoppingCart) {
//            ShoppingCart shoppingCartSaved = shoppingCartRepository.save(shoppingCartData);
//            System.out.println(shoppingCartSaved.getId());
//        }
//    }
//}