package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.dao.IngredientRepository;
import tacos.dao.UserRepository;
import tacos.data.Ingredient;
import tacos.data.Ingredient.Type;
import tacos.data.User;

import javax.sql.DataSource;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }


    //CommandLineRunner es una interfaz funcional con un método "run", el cual es llamado inmediatamente despés de que
    //to-do el cableado automático se ha terminado. Opcionalmente se puede utilizar ApplicationRunner para este propósito.
    //Esta forma, a diferencia de data._sql, otorga flexibilidad, puesto que permite agregar bases de datos no relaciones,
    // trabajar con jpa, etc.
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            ingredientRepository.deleteAll();
            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla 2", Type.WRAP));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla 2", Type.WRAP));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef 2", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas 2", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes 2", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce 2", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar 2", Type.CHEESE));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack 2", Type.CHEESE));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa 2", Type.SAUCE));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream 2", Type.SAUCE));


            userRepository.deleteAll();
            userRepository.save(new User("user",encoder.encode("user"),"user","lima","lima","lima","lima","99999"));


            //ingredientRepository.getIngredientByNameContaining("ce").stream().forEach(System.out::print);
        };
    }

}
