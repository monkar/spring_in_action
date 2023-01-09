package tacos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tacos.dao.OrderRepository;

@Service
public class AdminService {

    @Autowired
    OrderRepository orderRepository;


    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }
}
