package tacos.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import tacos.data.Taco;

public interface TacoRepository extends CrudRepository<Long, Taco> {

    Iterable<Taco> findAll(PageRequest page);
}
