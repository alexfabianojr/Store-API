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

    public void sanitycheck() {
        if (products.isEmpty() || (products.size() != productRepository.count())) {
            products = productRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<Product> findAll(){
        sanitycheck();
        return products;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        sanitycheck();
        if (products.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(products.get(Math.toIntExact(id)));
        } else {
            for (Product p : products) {
                if (p.getId().equals(id)) return ResponseEntity.ok().body(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findbycode/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
        sanitycheck();
        for (Product p : products) {
            if (p.getCode().equals(code)) return ResponseEntity.ok().body(p);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public Product save(@RequestBody Product product) {
        sanitycheck();
        Product newProduct = productRepository.save(product);
        products.add(newProduct);
        return newProduct;
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id,
                                 @RequestBody Product product) {
        sanitycheck();
        int index = (products.get(Math.toIntExact(id)).getId().equals(id))
                ? Math.toIntExact(id) : products.indexOf(productRepository.findById(id).orElseThrow());
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
