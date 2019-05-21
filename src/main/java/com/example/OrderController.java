package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Balance REST controller
 *
 * @author abbas
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public void createOrder(@RequestBody ExchangeOrder order) {
        orderRepository.save(order);
    }

    @GetMapping
    public ExchangeOrder getOrderByOrderId(@RequestParam Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @GetMapping("/burst")
    public String burst() {
        for (int i = 0; i < 10000; i++) {
            ExchangeOrder order = new ExchangeOrder();
            order.setAmount(33.3f);
            order.setUserId(5l);
            order.setOrderId(i);
            orderRepository.save(order);
            orderRepository.findAll();
        }
        orderRepository.deleteAll();
        return "Done";
    }

}
