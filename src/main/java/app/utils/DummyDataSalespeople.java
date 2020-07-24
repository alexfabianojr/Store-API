package app.utils;

import app.module.entities.Salespeople;
import app.repository.SalespeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

//@Component
public class DummyDataSalespeople {

    @Autowired
    SalespeopleRepository salespeopleRepository;

    //@PostConstruct
    public void salespeoplePopulation() {

        ArrayList<Salespeople> salespeopleArrayList = new ArrayList<>();

        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();
        ArrayList<Integer> integers3 = new ArrayList<>();
        ArrayList<Integer> integers4 = new ArrayList<>();

        integers1.add(1);
        integers2.add(2);
        integers3.add(3);
        integers4.add(4);

        Salespeople salespeople1 = new Salespeople();
        Salespeople salespeople2 = new Salespeople();
        Salespeople salespeople3 = new Salespeople();
        Salespeople salespeople4 = new Salespeople();

        salespeople1.setName("Vendedor A");
        salespeople1.setEmail("vendedora@blalba.com");
        salespeople1.setPassword("aaaaaa");
        salespeople1.setGenre('M');
        salespeople1.setBirthdate(LocalDate.now());
        salespeople1.setCompanyStartDate(LocalDate.now());
        salespeople1.setSalesIdList(integers1);

        salespeople2.setName("Vendedor B");
        salespeople2.setEmail("vendedorb@blalba.com");
        salespeople2.setPassword("bbbbbbbbb");
        salespeople2.setGenre('M');
        salespeople2.setBirthdate(LocalDate.now());
        salespeople2.setCompanyStartDate(LocalDate.now());
        salespeople2.setSalesIdList(integers2);

        salespeople3.setName("Vendedor C");
        salespeople3.setEmail("vendedorc@blalba.com");
        salespeople3.setPassword("cccccc");
        salespeople3.setGenre('F');
        salespeople3.setBirthdate(LocalDate.now());
        salespeople3.setCompanyStartDate(LocalDate.now());
        salespeople3.setSalesIdList(integers3);

        salespeople4.setName("Vendedor D");
        salespeople4.setEmail("vendedord@blalba.com");
        salespeople4.setPassword("dddddddd");
        salespeople4.setGenre('F');
        salespeople4.setBirthdate(LocalDate.now());
        salespeople4.setCompanyStartDate(LocalDate.now());
        salespeople4.setSalesIdList(integers4);

        salespeopleArrayList.add(salespeople1);
        salespeopleArrayList.add(salespeople2);
        salespeopleArrayList.add(salespeople3);
        salespeopleArrayList.add(salespeople4);

        for (Salespeople salespeople : salespeopleArrayList) {
            Salespeople salespeopleSaved = salespeopleRepository.save(salespeople);
            System.out.println(salespeopleSaved.getId());
        }
    }
}
