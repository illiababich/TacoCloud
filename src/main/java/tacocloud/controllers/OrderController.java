package tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacocloud.TacoOrder;
import tacocloud.User;
import tacocloud.data.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute TacoOrder tacoOrder) {

        System.out.println(tacoOrder.getTacos());

        if (tacoOrder.getDeliveryName() == null) {
            tacoOrder.setDeliveryName(user.getFullname());
        }
        if (tacoOrder.getDeliveryStreet() == null) {
            tacoOrder.setDeliveryStreet(user.getStreet());
        }
        if (tacoOrder.getDeliveryCity() == null) {
            tacoOrder.setDeliveryCity(user.getCity());
        }
        if (tacoOrder.getDeliveryState() == null) {
            tacoOrder.setDeliveryState(user.getState());
        }
        if (tacoOrder.getDeliveryZip() == null) {
            tacoOrder.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);

        System.out.println(order.toString());

        sessionStatus.setComplete();

        return "redirect:/orders/current/success";
    }
}