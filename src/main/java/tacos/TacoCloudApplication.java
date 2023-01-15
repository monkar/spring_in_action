package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.dao.IngredientRepository;
import tacos.dao.TacoRepository;
import tacos.dao.UserRepository;
import tacos.data.Ingredient;
import tacos.data.Ingredient.Type;
import tacos.data.Taco;
import tacos.data.User;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }


    //CommandLineRunner es una interfaz funcional con un método "run", el cual es llamado inmediatamente despés de que
    //to-do el cableado automático se ha terminado. Opcionalmente se puede utilizar ApplicationRunner para este propósito.
    //Esta forma, a diferencia de data._sql, otorga flexibilidad, puesto que permite agregar bases de datos no relaciones,
    // trabajar con jpa, etc.
    @Bean
    @Profile("!prod") // "test"      {"test","qa"}       "!prod"
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository,
                                        PasswordEncoder encoder, TacoRepository tacoRepository){
        log.info("Cargando ingredientes a base de datos");
        return args -> {
            ingredientRepository.deleteAll();

            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Type.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Type.SAUCE);
            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(tomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);


            userRepository.deleteAll();
            userRepository.save(new User("user",encoder.encode("user"),"user","lima","lima","lima","lima","99999"));


            userRepository.save(new User("habuma", encoder.encode("password"),
                    "Craig Walls", "123 North Street", "Cross Roads", "TX",
                    "76227", "123-123-1234"));

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            tacoRepository.save(taco3);

            //ingredientRepository.getIngredientByNameContaining("ce").stream().forEach(System.out::print);
        };
    }

    /* los perfiles también pueden usarse en toda una clase de configuracion

    @Profile({"!prod", "!qa"})
    @Configuration
    public class DevelopmentConfig {

      @Bean
      public CommandLineRunner dataLoader(IngredientRepository repo,
            UserRepository userRepo, PasswordEncoder encoder) {

        ...

      }

    }

     */

}
