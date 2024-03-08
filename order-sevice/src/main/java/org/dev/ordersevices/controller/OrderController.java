package org.dev.ordersevices.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.ordersevices.dto.Order;
import org.dev.ordersevices.dto.OrderEvent;
import org.dev.ordersevices.publisher.OrderProducer;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    @PostMapping
    public void placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = modelMapper.map(order, OrderEvent.class);
        producer.sendOrder(orderEvent);

        log.info("Order sent to the RabbitMQ...");

    }
}
