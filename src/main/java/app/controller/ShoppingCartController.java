package app.controller;

import app.module.entities.Client;
import app.module.entities.ShoppingCart;
import app.module.entities.Seller;
import app.repository.ClientRepository;
import app.repository.ShoppingCartRepository;
import app.repository.SellersRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@RestController
@RequestMapping(value = "/store-api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellersRepository sellersRepository;

    private static List<ShoppingCart> shoppingCarts = Collections.synchronizedList(new ArrayList<>());

    public synchronized void cache() {
        if (shoppingCarts.isEmpty() || shoppingCarts.size() != shoppingCartRepository.count()) {
            shoppingCarts.clear();
            shoppingCarts = shoppingCartRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<ShoppingCart> findAll() {
        cache();
        return shoppingCarts;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable("id") Long id) {
        return shoppingCartRepository.findById(id)
                .map(e -> { return ResponseEntity.ok().body(e); })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<ShoppingCart> saveNewSalesList(@RequestBody ShoppingCart shoppingCart) {
        cache();
        if (clientRepository.findById(shoppingCart.getClientId()).isPresent()
                && sellersRepository.findById(shoppingCart.getSalespeopleId()).isPresent()) {
            ShoppingCart newShoppingCart = shoppingCartRepository.save(shoppingCart);
            Client client = clientRepository
                    .findById(newShoppingCart.getClientId())
                    .orElseThrow();
            ArrayList<Long> clientIDs = clientRepository
                    .findById(newShoppingCart.getClientId())
                    .orElseThrow()
                    .getShoppingList();
            clientIDs.add(newShoppingCart.getId());
            client.setShoppingList(clientIDs);
            clientRepository.save(client);
            Seller seller = sellersRepository
                    .findById(newShoppingCart.getSalespeopleId())
                    .orElseThrow();
            ArrayList<Long> sellerIDs = sellersRepository
                    .findById(newShoppingCart.getSalespeopleId())
                    .orElseThrow()
                    .getSalesIdList();
            sellerIDs.add(newShoppingCart.getId());
            seller.setSalesIdList(sellerIDs);
            sellersRepository.save(seller);
            shoppingCarts.add(newShoppingCart);
            return ResponseEntity.ok().body(newShoppingCart);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<ShoppingCart> update(@PathVariable("id") Long id,
                                               @RequestBody ShoppingCart shoppingCart) {
        return shoppingCartRepository.findById(id)
                .map(sc -> {
                    sc.setSalespeopleId(shoppingCart.getSalespeopleId());
                    sc.setSales(shoppingCart.getSales());
                    cache();
                    shoppingCarts.remove(shoppingCartRepository.findById(id).orElseThrow());
                    ShoppingCart update = shoppingCartRepository.save(sc);
                    shoppingCarts.add(Math.toIntExact(id), update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}