package app.module.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_salespeople")
public class Salespeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "sp_name")
    private String name;

    @NotNull
    @Column(name = "sp_email")
    private String email;

    @NotNull
    @Column(name = "sp_password")
    private String password;

    @NotNull
    @Column(name = "sp_genre")
    private char genre;

    @NotNull
    @Column(name = "sp_birthdate")
    private LocalDate birthdate;

    @NotNull
    @Column(name = "sp_companyStartDate")
    private LocalDate companyStartDate;

    @NotNull
    @Column(name = "sp_salesIdList")
    private ArrayList<Integer> salesIdList = new ArrayList<>();
}
