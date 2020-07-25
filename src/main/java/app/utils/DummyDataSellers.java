package app.utils;

import app.module.entities.Seller;
import app.repository.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DummyDataSellers {

    @Autowired
    SellersRepository sellersRepository;

    @PostConstruct
    public void salespeoplePopulation() {

        ArrayList<Seller> sellerArrayList = new ArrayList<>();

        ArrayList<Long> integers1 = new ArrayList<>();
        ArrayList<Long> integers2 = new ArrayList<>();
        ArrayList<Long> integers3 = new ArrayList<>();
        ArrayList<Long> integers4 = new ArrayList<>();

        integers1.add((long) 1);
        integers2.add((long) 2);
        integers3.add((long) 3);
        integers4.add((long) 4);

        Seller seller1 = new Seller();
        Seller seller2 = new Seller();
        Seller seller3 = new Seller();
        Seller seller4 = new Seller();

        seller1.setName("Vendedor A");
        seller1.setEmail("vendedora@blalba.com");
        seller1.setPassword("aaaaaa");
        seller1.setGenre('M');
        seller1.setBirthdate(LocalDate.now());
        seller1.setCompanyStartDate(LocalDate.now());
        seller1.setSalesIdList(integers1);

        seller2.setName("Vendedor B");
        seller2.setEmail("vendedorb@blalba.com");
        seller2.setPassword("bbbbbbbbb");
        seller2.setGenre('M');
        seller2.setBirthdate(LocalDate.now());
        seller2.setCompanyStartDate(LocalDate.now());
        seller2.setSalesIdList(integers2);

        seller3.setName("Vendedor C");
        seller3.setEmail("vendedorc@blalba.com");
        seller3.setPassword("cccccc");
        seller3.setGenre('F');
        seller3.setBirthdate(LocalDate.now());
        seller3.setCompanyStartDate(LocalDate.now());
        seller3.setSalesIdList(integers3);

        seller4.setName("Vendedor D");
        seller4.setEmail("vendedord@blalba.com");
        seller4.setPassword("dddddddd");
        seller4.setGenre('F');
        seller4.setBirthdate(LocalDate.now());
        seller4.setCompanyStartDate(LocalDate.now());
        seller4.setSalesIdList(integers4);

        sellerArrayList.add(seller1);
        sellerArrayList.add(seller2);
        sellerArrayList.add(seller3);
        sellerArrayList.add(seller4);

        for (Seller seller : sellerArrayList) {
            Seller sellerSaved = sellersRepository.save(seller);
            System.out.println(sellerSaved.getId());
        }
    }
}
