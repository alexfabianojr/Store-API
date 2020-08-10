package app.module.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
    private long id;
    private String name;
    private String email;
    private int numberOfSales;
    private Double totalSold;
}
