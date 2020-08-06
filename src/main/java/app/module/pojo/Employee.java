package app.module.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Employee implements Serializable {
    private String password;
    private String accessStatus;
    private String role;
    private LocalDate startedInCompany;
    private BigDecimal salary;
}
