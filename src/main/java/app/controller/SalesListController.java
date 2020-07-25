package app.controller;

import app.module.entities.SalesList;
import app.repository.SalesListRepository;
import app.services.UpdateClientShoppingList;
import app.services.UpdateSellerSalesIdList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api/saleslist")
public class SalesListController {

    @Autowired
    private final SalesListRepository salesListRepository;

    public SalesListController(SalesListRepository salesListRepository) {
        this.salesListRepository = salesListRepository;
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
        Long idSales = newSale.getId();
        Long idSeller = newSale.getSalespeopleId();
        Long idClient = newSale.getClientId();
        UpdateSellerSalesIdList.byId(idSales, idSeller);
        UpdateClientShoppingList.byId(idSales, idClient);
        return salesList;
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
