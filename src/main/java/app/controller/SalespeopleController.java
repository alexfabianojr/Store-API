package app.controller;

import app.module.entities.Salespeople;
import app.repository.SalespeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api-salespeople")
public class SalespeopleController {

    @Autowired
    private SalespeopleRepository salespeopleRepository;

    public SalespeopleController(SalespeopleRepository salespeopleRepository) {
        this.salespeopleRepository = salespeopleRepository;
    }

    @GetMapping(path = "/findall")
    public List<Salespeople> findAll() {
        return salespeopleRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Salespeople> findById(Long id) {
        return salespeopleRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public Salespeople save(@RequestBody Salespeople salespeople) {
        return salespeopleRepository.save(salespeople);
    }
}
