package app.module.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "sp_birthdate")
    private LocalDate birthdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "sp_companyStartDate")
    private LocalDate companyStartDate;

    @Column(name = "sp_salesIdList")
    private ArrayList<Long> salesIdList = new ArrayList<Long>();
}
