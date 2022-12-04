package tacos.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Table
@Data
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Nombre debe tener al menos 5 caracteres de largo")
    private String name;

    @NotNull
    @Size(min = 1, message = "Debe elegir al menos 1 ingrediente")
    private List<IngredientRef> ingredients;

}
