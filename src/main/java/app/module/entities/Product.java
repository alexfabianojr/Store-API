package app.module.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "tb_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_manufacturer")
    private String manufacturer;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private BigDecimal price;

    @Column(name = "product_quantity")
    private int quantity;

    @Column(name = "product_weight")
    private Double weight;

    @Column(name = "product_dimension")
    private Double dimension;
}
