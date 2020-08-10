package app.module.entities;

import app.module.pojo.Address;
import app.module.pojo.Contact;
import app.module.pojo.Employee;
import app.module.pojo.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "tb_sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Person person;

    Contact contact;

    Address address;

    Employee employee;

    @Column(name = "sp_salesIdList")
    private ArrayList<Long> salesIdList = new ArrayList<Long>(); /*remover*/
}
