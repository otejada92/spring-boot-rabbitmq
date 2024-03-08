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
    private String orderRoutingKey;

    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailRoutingKey;

    private final RabbitTemplate template;

    public OrderProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendOrder(OrderEvent event) {
        log.info("Order event sent to RabbitMQ => {}", event.toString());
        template.convertAndSend(exchangeName, orderRoutingKey, event);

        template.convertAndSend(exchangeName, emailRoutingKey, event);

        log.info("email sent to => {}", "test email");
    }
}
