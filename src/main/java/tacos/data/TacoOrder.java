package tacos.data;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
public class TacoOrder implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message = "El Delivery Name es requerido")
    private String deliveryName;

    @NotBlank(message = "El Delivery Street es requerido")
    private String deliveryStreet;

    @NotBlank(message = "El Delivery City es requerido")
    private String deliveryCity;

    @NotBlank(message = "El Delivery State es requerido")
    private String deliveryState;

    @NotBlank(message = "El Delivery Zip Code es requerido")
    private String deliveryZip;

    @CreditCardNumber(message = "Número de CC invàlido")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

}
