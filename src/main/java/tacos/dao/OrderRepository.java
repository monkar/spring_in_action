package tacos.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import tacos.data.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {


    //List<TacoOrder> findByDeliveryZip(String deliveryZip);

}
