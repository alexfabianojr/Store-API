package app.utils;

import app.module.entities.Seller;
import app.module.pojo.Address;
import app.module.pojo.Contact;
import app.module.pojo.Employee;
import app.module.pojo.Person;
import app.repository.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DummyDataSellers {

    @Autowired
    SellersRepository sellersRepository;

    @PostConstruct
    public void salespeoplePopulation() {

        System.out.println("Initializing seller dummy data");

        ArrayList<Long> longs;
        Person person;
        Contact contact;
        Address address;
        Employee employee;
        Seller seller;

        synchronized (this) {
            System.out.println("Seller list caching for dummy data");
            for (int i = 0; i <= 100; i++) {

                longs = new ArrayList<>();

                longs.add((long) 1);

                person = new Person();
                person.setName("Generic Seller");
                person.setGenre('M');
                person.setCpf("38402385010");
                person.setBirthDate(LocalDate.of(1987, 2, 22));

                contact = new Contact();
                contact.setCellPhone("99878431");
                contact.setHomephone("335285498");
                contact.setEmail("sellertestador@gmail.com");

                address = new Address();
                address.setCountry("Brazil");
                address.setCep("95680970");
                address.setState("Rio Grande Do Sul");
                address.setCity("Canela");
                address.setNeighborhood("Centro");
                address.setStreet("Rua Dona Carlinda");
                address.setComplement("Bloco C");
                address.setNumber("389");
                address.setMainAddress(true);

                employee = new Employee();
                employee.setAccessStatus("1");
                employee.setPassword("123456");
                employee.setRole("Seller");
                employee.setSalary(BigDecimal.valueOf(3500.00));
                employee.setStartedInCompany(LocalDate.now());

                seller = new Seller();
                seller.setPerson(person);
                seller.setContact(contact);
                seller.setAddress(address);
                seller.setEmployee(employee);
                seller.setSalesIdList(longs);

                sellersRepository.save(seller);
            }
        }
        System.out.println("Seller dummy data 100%");
    }
}
