package tacos.data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Nombre debe tener al menos 5 caracteres de largo")
    private String name;

    @NotNull
    @Size(min = 1, message = "Debe elegir al menos 1 ingrediente")
    private List<Ingredient> ingredients;

}
