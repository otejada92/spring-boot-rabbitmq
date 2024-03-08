package org.dev.ordersevices.publisher;

import lombok.extern.slf4j.Slf4j;
import org.dev.ordersevices.dto.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.binding.order.routing.key}")
    private String routingKey;

    private final RabbitTemplate template;

    public OrderProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendOrder(OrderEvent orderEvent) {
        log.info("Order event sent to RabbitMQ => {}", orderEvent.toString());
        template.convertAndSend(exchangeName, routingKey, orderEvent);
    }
}
