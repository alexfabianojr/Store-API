package app.module.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_sales")
public class SalesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sales_salespeopleId")
    private int salespeopleId;

    @Column(name = "sales_sales")
    private String sales;
}
