package app.controller;

import app.module.entities.Seller;
import app.repository.SellersRepository;
import app.services.ReturnSellersByGenre;
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
@RequestMapping(value = "/store-api/seller")
public class SellersController {

    @Autowired
    private SellersRepository sellersRepository;

    private List<Seller> sellers = new ArrayList<>();

    public void sanitycheck() {
        if (sellers.isEmpty() || sellers.size() != sellersRepository.count()) {
            sellers = sellersRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<Seller> findAll() {
        sanitycheck();
        return sellers;
    }

    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<Seller> findById(@PathVariable("id") Long id) {
        sanitycheck();
        if (sellers.get(Math.toIntExact(id)).getId().equals(id)) {
            return ResponseEntity.ok().body(sellers.get(Math.toIntExact(id)));
        } else {
            for (Seller sl : sellers) {
                if (sl.getId().equals(id)) return ResponseEntity.ok().body(sl);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findbygenre/{genre}")
    public List<Seller> findByGenre(@PathVariable("genre") char genre) {
        sanitycheck();
        return ReturnSellersByGenre.find(sellers, genre);
    }

    @PostMapping(path = "/save")
    public Seller save(@RequestBody Seller seller) {
        sanitycheck();
        Seller newSeller = sellersRepository.save(seller);
        sellers.add(newSeller);
        return newSeller;
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Seller> update(@PathVariable("id") Long id,
                                         @RequestBody Seller seller) {
        sanitycheck();
        int index = (sellers.get(Math.toIntExact(id)).getId().equals(id))
                ? Math.toIntExact(id) : sellers.indexOf(sellersRepository.findById(id).orElseThrow());
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
