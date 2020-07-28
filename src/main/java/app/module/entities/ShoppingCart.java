package app.module.entities;

import app.module.pojo.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_shoppingcarts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sc_salespeopleId")
    private Long salespeopleId;

    @Column(name = "sc_clientId")
    private Long clientId;

    @Column(name = "sc_scIdsList")
    private ArrayList<Sale> sales = new ArrayList<>();
}
