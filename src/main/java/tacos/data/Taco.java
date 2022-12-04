package tacos.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Nombre debe tener al menos 5 caracteres de largo")
    private String name;

    @NotNull
    @Size(min = 1, message = "Debe elegir al menos 1 ingrediente")
    @ManyToMany
    private List<Ingredient> ingredients;

    /*no agregare el addingrediente*/
}
