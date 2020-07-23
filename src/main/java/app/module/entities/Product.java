package app.module.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "product_code")
    private String code;

    @NotNull
    @Column(name = "product_name")
    private String name;

    @NotNull
    @Column(name = "product_description")
    private String description;

    @NotNull
    @Column(name = "product_price")
    private Double price;

    @NotNull
    @Column(name = "product_quantity")
    private int quantity;

    @Column(name = "product_weight")
    private Double weight;

    @Column(name = "product_dimension")
    private Double dimension;
}
