package tacos.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.dao.OrderRepository;
import tacos.data.TacoOrder;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/orders")
@Controller
@SessionAttributes("tacoOrder")
public class OrderController {

    OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
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
    public String processOrder(TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
