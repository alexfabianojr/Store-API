package app.module.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_genre")
    private char genre;

    @Column(name = "client_email")
    private String email;

    @Column(name = "client_shoppingList")
    private ArrayList<Integer> shoppingList = new ArrayList<>();
}
