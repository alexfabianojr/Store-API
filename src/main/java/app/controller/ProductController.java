package app.controller;

import app.module.entities.Product;
import app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private static List<Product> products = Collections.synchronizedList(new ArrayList<>());

    public synchronized void cache() {
        if (products.isEmpty() || (products.size() != productRepository.count())) {
            products.clear();
            products = productRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<Product> findAll() {
        cache();
        return products;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(e -> { return ResponseEntity.ok().body(e); })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/findbycode/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
        cache();
        for (Product p : products) {
            if (p.getCode().equals(code)) return ResponseEntity.ok().body(p);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public Product save(@RequestBody Product product) {
        cache();
        Product newProduct = productRepository.save(product);
        products.add(newProduct);
        return newProduct;
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id,
                                          @RequestBody Product product) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setCode(product.getCode());
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setQuantity(product.getQuantity());
                    p.setWeight(product.getWeight());
                    p.setDimension(product.getDimension());
                    cache();
                    products.remove(productRepository.findById(id).orElseThrow());
                     Product update = productRepository.save(p);
                    products.add(Math.toIntExact(id), update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
