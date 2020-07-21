package app.controller;

import app.module.entities.Products;
import app.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api-products")
public class ProductController {

    @Autowired
    private ProductsRepository productsRepository;

    public ProductController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping(path = "/findall")
    public List<Products> findAll(){
        return productsRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Products> findById(@PathVariable Long id) {
        return productsRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}
