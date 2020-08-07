package app.controller;

import app.module.entities.Seller;
import app.repository.SellersRepository;
import app.services.DocumentValidator;
import app.services.ReturnSellersByGenre;
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
@RequestMapping(value = "/store-api/seller")
public class SellersController {

    @Autowired
    private SellersRepository sellersRepository;

    private List<Seller> sellers = Collections.synchronizedList(new ArrayList<>());

    public synchronized void cache() {
        if (sellers.isEmpty() || sellers.size() != sellersRepository.count()) {
            sellers = sellersRepository.findAll();
        }
    }

    @GetMapping(path = "/findall")
    public List<Seller> findAll() {
        cache();
        return sellers;
    }

    @GetMapping(path = "/findby-id/{id}")
    public ResponseEntity<Seller> findById(@PathVariable("id") Long id) {
        return sellersRepository.findById(id)
                .map(e -> { return ResponseEntity.ok().body(e); })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/findby-email/{email}")
    public ResponseEntity<Seller> findByEmail(@PathVariable("email") String email) {
        cache();
        for (Seller sl : sellers) {
            if (sl.getContact().getEmail().equals(email)) return ResponseEntity.ok().body(sl);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findby-name/{name}")
    public ResponseEntity<Seller> findByName(@PathVariable("name") String name) {
        cache();
        for (Seller sl : sellers) {
            if (sl.getPerson().getName().equals(name)) return ResponseEntity.ok().body(sl);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/findby-genre/{genre}")
    public List<Seller> findByGenre(@PathVariable("genre") char genre) {
        cache();
        return ReturnSellersByGenre.find(sellers, genre);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Seller> save(@RequestBody Seller seller) {
        if (DocumentValidator.isCPF(seller.getPerson().getCpf())) {
            Seller newSeller = sellersRepository.save(seller);
            cache();
            sellers.add(newSeller);
            return ResponseEntity.ok().body(newSeller);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Seller> update(@PathVariable("id") Long id,
                                         @RequestBody Seller seller) {
        return sellersRepository.findById(id)
                .map(s -> {
                    s.setId(id);
                    s.setPerson(seller.getPerson());
                    s.setContact(seller.getContact());
                    s.setAddress(seller.getAddress());
                    s.setEmployee(seller.getEmployee());
                    s.setSalesIdList(sellersRepository.findById(id)
                            .orElseThrow()
                            .getSalesIdList());
                    cache();
                    sellers.remove(sellersRepository.findById(id).orElseThrow());
                    Seller update = sellersRepository.save(s);
                    sellers.add(Math.toIntExact(id), update);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }
}
