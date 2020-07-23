package app.module.entities;

import app.module.pojo.Sale;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_sales")
public class SalesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "sales_salespeopleId")
    private int salespeopleId;

    @NotNull
    @Column(name = "sales_sales")
    private ArrayList<Sale> sales = new ArrayList<>();
}
