package app.controller;

import app.module.entities.Seller;
import app.repository.SellersRepository;
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
@RequestMapping(value = "/store-api/sellers")
public class SellersController {

    @Autowired
    private SellersRepository sellersRepository;

    private List<Seller> sellers = new ArrayList<>();

    @GetMapping(path = "/findall")
    public List<Seller> findAll() {
        if (sellers.isEmpty()) {
            sellers = sellersRepository.findAll();
        }
        return sellers;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Seller> findById(@PathVariable("id") Long id) {
        if (sellers.isEmpty()) {
            sellers = sellersRepository.findAll();
        }
        if (sellers.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(sellers.get(Math.toIntExact(id)));
        } else {
            for (Seller sl : sellers) {
                if (sl.getId().equals(id)) {
                    return ResponseEntity.ok().body(sl);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/save")
    public Seller save(@RequestBody Seller seller) {
        Seller newSeller = sellersRepository.save(seller);
        sellers.add(Math.toIntExact(newSeller.getId()), newSeller);
        return newSeller;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Seller> update(@PathVariable("id") Long id,
                                         @RequestBody Seller seller) {
        int index;
        if (sellers.isEmpty()) {
            sellers = sellersRepository.findAll();
        }
        if (sellers.get(Math.toIntExact(id)).getId().equals(id)) {
            index = Math.toIntExact(id);
        } else {
            index = sellers.indexOf(sellersRepository.findById(id).get());
        }
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
                    sellers.remove(index);
                    sellers.add(index, update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
