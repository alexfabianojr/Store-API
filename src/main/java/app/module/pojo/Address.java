package app.module.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Address implements Serializable {
    private String cep;
    private String country;
    private String state;
    private String city;
    private String street;
    private String neighborhood;
    private String complement;
    private String number;
    private boolean mainAddress;
}
