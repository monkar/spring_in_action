package tacos.data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient implements Persistable<String> {

    //se agrega Persistable para poder gestionar de forma personalizada el estado de nuevo / existente
    //para indicarle a spring cuando usar insert o update, al persistir con save()

    @Id
    private String id;
    private String name;
    private Type type;

    @Override
    public boolean isNew() {
        return true;
    }


    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }
}
