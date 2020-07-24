package app.module.entities;

import app.module.pojo.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tb_sales")
public class SalesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_salespeopleId")
    private int salespeopleId;

    @Column(name = "sales_sales")
    private ArrayList<Sale> sales = new ArrayList<>();

    public SalesList() {
    }

    public SalesList(int salespeopleId, ArrayList<Sale> sales) {
        this.salespeopleId = salespeopleId;
        this.sales = sales;
    }

    public SalesList(Long id, int salespeopleId, ArrayList<Sale> sales) {
        this.id = id;
        this.salespeopleId = salespeopleId;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "SalesList{" +
                "id=" + id +
                ", salespeopleId=" + salespeopleId +
                ", sales=" + sales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesList salesList = (SalesList) o;

        if (salespeopleId != salesList.salespeopleId) return false;
        return Objects.equals(id, salesList.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + salespeopleId;
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSalespeopleId() {
        return salespeopleId;
    }

    public void setSalespeopleId(int salespeopleId) {
        this.salespeopleId = salespeopleId;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }
}
