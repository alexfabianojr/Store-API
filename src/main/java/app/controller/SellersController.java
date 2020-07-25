package app.controller;

import app.module.entities.Seller;
import app.repository.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store-api/sellers")
public class SellersController {

    @Autowired
    private final SellersRepository sellersRepository;

    public SellersController(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @GetMapping(path = "/findall")
    public List<Seller> findAll() {
        return sellersRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Seller> findById(@PathVariable("id") Long id) {
        return sellersRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/save")
    public Seller save(@RequestBody Seller seller) {
        return sellersRepository.save(seller);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Seller> update(@PathVariable("id") Long id,
                                         @RequestBody Seller seller) {
        return sellersRepository.findById(id)
                .map(record -> {
                    record.setName(seller.getName());
                    record.setEmail(seller.getEmail());
                    record.setPassword(seller.getPassword());
                    record.setGenre(seller.getGenre());
                    record.setBirthdate(seller.getBirthdate());
                    record.setCompanyStartDate(seller.getCompanyStartDate());
                    record.setSalesIdList(seller.getSalesIdList());
                    Seller update = sellersRepository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
