package org.dev.ordersevices.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.ordersevices.dto.Order;
import org.dev.ordersevices.dto.OrderEvent;
import org.dev.ordersevices.publisher.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public void placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();

        event.setStatus("PENDING");
        event.setMessage("Order is in pending status");
        event.setOrder(order);

        producer.sendOrder(event);

        log.info("Order sent to the RabbitMQ...");

    }
}
