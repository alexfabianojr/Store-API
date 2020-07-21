package app.controller;

import app.module.entities.Salespeople;
import app.repository.SalespeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api-salespeople")
public class SalespeopleController {

    @Autowired
    private SalespeopleRepository salespeopleRepository;

    public SalespeopleController(SalespeopleRepository salespeopleRepository) {
        this.salespeopleRepository = salespeopleRepository;
    }

    @GetMapping
    public List<Salespeople> findAll() {
        return salespeopleRepository.findAll();
    }
}
