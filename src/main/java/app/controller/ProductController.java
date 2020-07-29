package app.controller;

import app.module.entities.Product;
import app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/store-api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products = new ArrayList<>();

    @GetMapping(path = "/findall")
    public List<Product> findAll(){
        if (products.isEmpty()) {
            products = productRepository.findAll();
        } else if (products.size() != productRepository.count()) {
            products = productRepository.findAll();
        }
        return products;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        if (products.isEmpty()) {
            products = productRepository.findAll();
        } else if (products.size() != productRepository.count()) {
            products = productRepository.findAll();
        }
        if (products.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(products.get(Math.toIntExact(id)));
        } else {
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    return ResponseEntity.ok().body(p);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public Product save(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        if (products.isEmpty()) {
            products = productRepository.findAll();
        } else if (products.size() != productRepository.count()) {
            products = productRepository.findAll();
        }
        products.add(newProduct);
        return newProduct;
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody Product product) {
        int index;
        if (products.isEmpty()) {
            products = productRepository.findAll();
        } else if (products.size() != productRepository.count()) {
            products = productRepository.findAll();
        }
        if (products.get(Math.toIntExact(id)).getId().equals(id)) {
            index = Math.toIntExact(id);
        } else {
            index = products.indexOf(productRepository.findById(id).get());
        }
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
                    products.remove(index);
                    products.add(index, update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
