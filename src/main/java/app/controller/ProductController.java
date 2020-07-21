package app.controller;

import app.repository.ProductsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store-api-product")
public class ProductController {

    private ProductsRepository productsRepository;
}
