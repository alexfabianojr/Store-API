package app.module.entities;

import app.module.pojo.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

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

    public ShoppingCart() {
    }

    public ShoppingCart(Long salespeopleId, Long clientId, ArrayList<Sale> sales) {
        this.salespeopleId = salespeopleId;
        this.clientId = clientId;
        this.sales = sales;
    }

    public ShoppingCart(Long id, Long salespeopleId, Long clientId, ArrayList<Sale> sales) {
        this.id = id;
        this.salespeopleId = salespeopleId;
        this.clientId = clientId;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
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

        ShoppingCart shoppingCart = (ShoppingCart) o;

        if (!Objects.equals(id, shoppingCart.id)) return false;
        if (!Objects.equals(salespeopleId, shoppingCart.salespeopleId))
            return false;
        return Objects.equals(clientId, shoppingCart.clientId);
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
