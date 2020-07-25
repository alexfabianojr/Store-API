package app.controller;

import app.module.entities.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api/products")
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/findall")
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody Product product) {
        return productRepository.findById(id)
                .map(record -> {
                    record.setCode(product.getCode());
                    record.setName(product.getName());
                    record.setDescription(product.getDescription());
                    record.setPrice(product.getPrice());
                    record.setQuantity(product.getQuantity());
                    record.setWeight(product.getWeight());
                    record.setDimension(product.getDimension());
                    Product update = productRepository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
