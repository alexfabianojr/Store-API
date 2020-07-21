package app.module.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "product_quantity")
    private int quantity;

    @Column(name = "product_weight")
    private Double weight;

    @Column(name = "product_dimension")
    private Double dimension;
}
