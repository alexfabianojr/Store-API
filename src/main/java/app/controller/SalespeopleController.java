package app.controller;

import app.repository.SalespeopleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store-api-salespeople")
public class SalespeopleController {

    private SalespeopleRepository salespeopleRepository;
}
