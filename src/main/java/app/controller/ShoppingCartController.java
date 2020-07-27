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

    @GetMapping(path = "/findall")
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable("id") Long id) {
        return shoppingCartRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public ShoppingCart saveNewSalesList(@RequestBody ShoppingCart shoppingCart) {
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
        return newShoppingCart;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ShoppingCart> update(@PathVariable("id") Long id,
                                               @RequestBody ShoppingCart shoppingCart) {
        return shoppingCartRepository.findById(id)
                .map(record -> {
                    record.setSalespeopleId(shoppingCart.getSalespeopleId());
                    record.setSales(shoppingCart.getSales());
                    ShoppingCart update = shoppingCartRepository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
