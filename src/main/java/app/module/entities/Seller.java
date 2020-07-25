package app.module.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tb_sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sp_name")
    private String name;

    @Column(name = "sp_email")
    private String email;

    @Column(name = "sp_password")
    private String password;

    @Column(name = "sp_genre")
    private char genre;

    @Column(name = "sp_birthdate")
    private LocalDate birthdate;

    @Column(name = "sp_companyStartDate")
    private LocalDate companyStartDate;

    @Column(name = "sp_salesIdList")
    private ArrayList<Long> salesIdList = new ArrayList<Long>();

    public Seller() {
    }

    public Seller(String name, String email, String password, char genre,
                  LocalDate birthdate, LocalDate companyStartDate, ArrayList<Long> salesIdList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.genre = genre;
        this.birthdate = birthdate;
        this.companyStartDate = companyStartDate;
        this.salesIdList = salesIdList;
    }

    public Seller(Long id, String name, String email, String password, char genre,
                  LocalDate birthdate, LocalDate companyStartDate, ArrayList<Long> salesIdList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.genre = genre;
        this.birthdate = birthdate;
        this.companyStartDate = companyStartDate;
        this.salesIdList = salesIdList;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", genre=" + genre +
                ", birthdate=" + birthdate +
                ", companyStartDate=" + companyStartDate +
                ", salesIdList=" + salesIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller that = (Seller) o;

        if (genre != that.genre) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(birthdate, that.birthdate)) return false;
        return Objects.equals(companyStartDate, that.companyStartDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) genre;
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (companyStartDate != null ? companyStartDate.hashCode() : 0);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(LocalDate companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public ArrayList<Long> getSalesIdList() {
        return salesIdList;
    }

    public void setSalesIdList(ArrayList<Long> salesIdList) {
        this.salesIdList = salesIdList;
    }
}
