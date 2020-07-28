package app.controller;

import app.module.entities.Client;
import app.module.entities.ShoppingCart;
import app.module.entities.Seller;
import app.repository.ClientRepository;
import app.repository.ShoppingCartRepository;
import app.repository.SellersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/saleslist")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellersRepository sellersRepository;

    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    @GetMapping(path = "/findall")
    public List<ShoppingCart> findAll() {
        if (shoppingCarts.isEmpty()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        return shoppingCarts;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable("id") Long id) {
        if (shoppingCarts.isEmpty()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        if (shoppingCarts.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(shoppingCarts.get(Math.toIntExact(id)));
        } else {
            for (ShoppingCart sc : shoppingCarts) {
                if (sc.getId().equals(id)) {
                    return ResponseEntity.ok().body(sc);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public ShoppingCart saveNewSalesList(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCarts.isEmpty()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        ShoppingCart newShoppingCart = shoppingCartRepository.save(shoppingCart);
        Long idClient = newShoppingCart.getClientId();
        Long idSeller = newShoppingCart.getSalespeopleId();
        try {
            if (clientRepository.findById(idClient).isPresent()) {
                Client client = clientRepository
                        .findById(idClient)
                        .get();
                ArrayList<Long> longs = clientRepository
                        .findById(idClient)
                        .get()
                        .getShoppingList();
                longs.add(newShoppingCart.getId());
                client.setShoppingList(longs);
                clientRepository.save(client);
            } else {
                throw new Exception();
            }
            if (sellersRepository.findById(idSeller).isPresent()){
                Seller seller = sellersRepository
                        .findById(idSeller)
                        .get();
                ArrayList<Long> longs = sellersRepository
                        .findById(idSeller)
                        .get()
                        .getSalesIdList();
                longs.add(newShoppingCart.getId());
                seller.setSalesIdList(longs);
                sellersRepository.save(seller);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        shoppingCarts.add(Math.toIntExact(newShoppingCart.getId()), newShoppingCart);
        return newShoppingCart;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ShoppingCart> update(@PathVariable("id") Long id,
                                               @RequestBody ShoppingCart shoppingCart) {
        int index;
        if (shoppingCarts.isEmpty()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        if (shoppingCarts.get(Math.toIntExact(id)).getId().equals(id)) {
            index = Math.toIntExact(id);
        } else {
            index = shoppingCarts.indexOf(shoppingCartRepository.findById(id).get());
        }
        return shoppingCartRepository.findById(id)
                .map(record -> {
                    record.setSalespeopleId(shoppingCart.getSalespeopleId());
                    record.setSales(shoppingCart.getSales());
                    ShoppingCart update = shoppingCartRepository.save(record);
                    shoppingCarts.remove(index);
                    shoppingCarts.add(index, update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
