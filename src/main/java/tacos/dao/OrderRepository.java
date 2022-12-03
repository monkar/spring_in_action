package tacos.dao;

import java.util.Optional;

import tacos.data.TacoOrder;

public interface OrderRepository {

  TacoOrder save(TacoOrder order);

  Optional<TacoOrder> findById(Long id);

}
