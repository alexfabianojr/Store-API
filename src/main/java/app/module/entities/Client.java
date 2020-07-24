package app.module.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_genre")
    private char genre;

    @Column(name = "client_email")
    private String email;

    @Column(name = "client_shoppingList")
    private ArrayList<Integer> shoppingList = new ArrayList<>();

    public Client() {
    }

    public Client(String name, char genre, String email, ArrayList<Integer> shoppingList) {
        this.name = name;
        this.genre = genre;
        this.email = email;
        this.shoppingList = shoppingList;
    }

    public Client(Long id, String name, char genre, String email, ArrayList<Integer> shoppingList) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.email = email;
        this.shoppingList = shoppingList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", email='" + email + '\'' +
                ", shoppingList=" + shoppingList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (genre != client.genre) return false;
        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(name, client.name)) return false;
        return Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) genre;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<Integer> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
