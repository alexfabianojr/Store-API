package app.module.entities;

import app.module.pojo.Address;
import app.module.pojo.Contact;
import app.module.pojo.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cl_person")
    Person person;

    @Column(name = "cl_contact")
    Contact contact;

    @Column(name = "cl_address")
    Address address;

    @Column(name = "cl_shoppingcart_stories")
    private ArrayList<Long> shoppingList = new ArrayList<Long>(); /*remover*/
}
