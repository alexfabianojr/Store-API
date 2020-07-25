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
    private Long salespeopleId;

    @Column(name = "sales_clientId")
    private Long clientId;

    @Column(name = "sales_sales")
    private ArrayList<Sale> sales = new ArrayList<>();

    public SalesList() {
    }

    public SalesList(Long salespeopleId, Long clientId, ArrayList<Sale> sales) {
        this.salespeopleId = salespeopleId;
        this.clientId = clientId;
        this.sales = sales;
    }

    public SalesList(Long id, Long salespeopleId, Long clientId, ArrayList<Sale> sales) {
        this.id = id;
        this.salespeopleId = salespeopleId;
        this.clientId = clientId;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "SalesList{" +
                "id=" + id +
                ", salespeopleId=" + salespeopleId +
                ", clientId=" + clientId +
                ", sales=" + sales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesList salesList = (SalesList) o;

        if (!Objects.equals(id, salesList.id)) return false;
        if (!Objects.equals(salespeopleId, salesList.salespeopleId))
            return false;
        return Objects.equals(clientId, salesList.clientId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (salespeopleId != null ? salespeopleId.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalespeopleId() {
        return salespeopleId;
    }

    public void setSalespeopleId(Long salespeopleId) {
        this.salespeopleId = salespeopleId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }
}
