package tacos.data;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.UUID;

@Data
public class TacoOrder implements Serializable {

    private static final long serialVersionUID =1L;

    @PrimaryKey
    private UUID id = Uuids.timeBased();

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

    //@CreditCardNumber(message = "Número de CC invàlido")
    private String ccNumber;

    //@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    //@Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(TacoUDT taco){
        tacos.add(taco);
    }

    public void addTaco(Taco taco){
        tacos.add(new TacoUDT(taco.getName(), taco.getIngredients()));
    }

}
