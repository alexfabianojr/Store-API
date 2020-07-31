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
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/shoppingcart")
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
        if (shoppingCarts.isEmpty() || shoppingCarts.size() != shoppingCartRepository.count()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        return shoppingCarts;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable("id") Long id) {
        if (shoppingCarts.isEmpty() || shoppingCarts.size() != shoppingCartRepository.count()) {
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
    public ResponseEntity<ShoppingCart> saveNewSalesList(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCarts.isEmpty() || shoppingCarts.size() != shoppingCartRepository.count()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
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
        if (shoppingCarts.isEmpty() || shoppingCarts.size() != shoppingCartRepository.count()) {
            shoppingCarts = shoppingCartRepository.findAll();
        }
        int index = (shoppingCarts.get(Math.toIntExact(id)).getId().equals(id))
                ? Math.toIntExact(id) : shoppingCarts.indexOf(shoppingCartRepository.findById(id).orElseThrow());
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