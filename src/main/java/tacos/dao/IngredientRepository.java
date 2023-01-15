package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tacos.data.Ingredient;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource
//@CrossOrigin(origins = "http://localhost:8080")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {


    //List<Ingredient> getIngredientByNameContaining(String name);

}
