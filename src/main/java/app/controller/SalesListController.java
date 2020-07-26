package app.controller;

import app.module.entities.Client;
import app.module.entities.SalesList;
import app.module.entities.Seller;
import app.repository.ClientRepository;
import app.repository.SalesListRepository;
import app.repository.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/store-api/saleslist")
public class SalesListController {

    @Autowired
    private SalesListRepository salesListRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellersRepository sellersRepository;

    public SalesListController() {
    }

    public SalesListController(SalesListRepository salesListRepository) {
        this.salesListRepository = salesListRepository;
    }

    public SalesListController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public SalesListController(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    public SalesListController(SalesListRepository salesListRepository,
                               ClientRepository clientRepository,
                               SellersRepository sellersRepository) {
        this.salesListRepository = salesListRepository;
        this.clientRepository = clientRepository;
        this.sellersRepository = sellersRepository;
    }

    @GetMapping(path = "/findall")
    public List<SalesList> findAll() {
        return salesListRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SalesList> findById(@PathVariable("id") Long id) {
        return salesListRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public SalesList saveNewSalesList(@RequestBody SalesList salesList) {
        SalesList newSale = salesListRepository.save(salesList);
        Long idClient = newSale.getClientId();
        Long idSeller = newSale.getSalespeopleId();
        try {
            if (clientRepository.findById(idClient).isPresent()) {
                Client client = clientRepository
                        .findById(idClient)
                        .get();
                ArrayList<Long> longs = clientRepository
                        .findById(idClient)
                        .get()
                        .getShoppingList();
                longs.add(newSale.getId());
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
                longs.add(newSale.getId());
                seller.setSalesIdList(longs);
                sellersRepository.save(seller);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return newSale;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<SalesList> update(@PathVariable("id") Long id,
                                 @RequestBody SalesList salesList) {
        return salesListRepository.findById(id)
                .map(record -> {
                    record.setSalespeopleId(salesList.getSalespeopleId());
                    record.setSales(salesList.getSales());
                    SalesList update = salesListRepository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
