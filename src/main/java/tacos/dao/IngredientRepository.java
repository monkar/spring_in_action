package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.data.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {


    List<Ingredient> getIngredientByNameContaining(String name);

}
