package app.module.entities;

import app.module.pojo.Sale;
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
@Table(name = "tb_shoppingcarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sc_sellerId")
    private Long salespeopleId;

    @Column(name = "sc_clientId")
    private Long clientId;

    @Column(name = "sc_date")
    LocalDate date;

    @Column(name = "sc_bill")
    private String bill;

    @Column(name = "sc_scIdsList")
    private ArrayList<Sale> sales = new ArrayList<>();
}
