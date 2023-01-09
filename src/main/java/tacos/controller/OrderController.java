package tacos.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.dao.OrderRepository;
import tacos.dao.UserRepository;
import tacos.data.TacoOrder;
import tacos.data.User;
import tacos.service.AdminService;

import java.security.Principal;


@Slf4j
@RequestMapping("/orders")
@Controller
@SessionAttributes("tacoOrder")
public class OrderController {

    OrderRepository orderRepository;
    UserRepository userRepository;
    AdminService adminService;


    public OrderController(OrderRepository orderRepository, AdminService adminService, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.adminService = adminService;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /* removido para acelerar pruebas
        @PostMapping
        public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){
            if(errors.hasErrors())
                return "orderForm";

            log.info("Order submitted: {}", order);
            sessionStatus.setComplete();
            return "redirect:/";
        }
    */
    @PostMapping
    public String processOrder(TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        log.info("Order submitted: {}", order);

        log.info("principal gentName: "+user);
        //User user = userRepository.findByUsername(principal.getName());

        order.setUser(user);
        orderRepository.save(order);

        sessionStatus.setComplete();
        return "redirect:/";
    }


    //todo: para prueba de seguridad
/*
    @PostMapping("/deleteOrders")
    public String deleteOrders(){
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }



    //todo: permisos para admin y si el taco coincide con el usuario logueado
    @PostAuthorize("hasRole('ADMIN') || " +
            "returnObject.user.username == authentication.name")
    public TacoOrder getOrder(long id) {
          return null;
    }


 */
}
