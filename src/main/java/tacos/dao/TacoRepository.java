package tacos.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tacos.data.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
