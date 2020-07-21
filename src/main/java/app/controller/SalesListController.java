package app.controller;

import app.module.entities.SalesList;
import app.repository.SalesListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
