package tacos.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Taco {


    @NotNull
    @Size(min = 5, message = "Nombre debe tener al menos 5 caracteres de largo")
    private String name;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 1, message = "Debe elegir al menos 1 ingrediente")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
}
