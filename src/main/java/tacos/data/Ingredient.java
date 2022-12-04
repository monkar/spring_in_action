package tacos.data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Ingredient{

    @Id
    private String id;
    private String name;
    private Type type;



    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }
}
