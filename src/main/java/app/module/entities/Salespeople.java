package app.module.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_salespeople")
public class Salespeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sp_name")
    private String name;

    @Column(name = "sp_email")
    private String email;

    @Column(name = "sp_password")
    private String password;

    @Column(name = "sp_genre")
    private char genre;

    @Column(name = "sp_birthdate")
    private Date birthdate;

    @Column(name = "sp_companyStartDate")
    private Date companyStartDate;

    @Column(name = "sp_salesIdList")
    private String salesIdList;
}
