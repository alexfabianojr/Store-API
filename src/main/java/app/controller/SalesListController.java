package app.controller;

import app.module.entities.SalesList;
import app.repository.SalesListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api-saleslist")
public class SalesListController {

    @Autowired
    private SalesListRepository salesListRepository;

    public SalesListController(SalesListRepository salesListRepository) {
        this.salesListRepository = salesListRepository;
    }

    @GetMapping(path = "/findall")
    public List<SalesList> findAll() {
        return salesListRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SalesList> findById(Long id) {
        return salesListRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public SalesList save(@RequestBody SalesList salesList) {
        return salesListRepository.save(salesList);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
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
