package app.controller;

import app.repository.SalesListRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store-api-saleslist")
public class SalesListController {

    private SalesListRepository salesListRepository;
}
