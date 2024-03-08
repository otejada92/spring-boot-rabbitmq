package org.dev.stockservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.dev.stockservice.dto.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.order.name}"})
    public void consume(OrderEvent orderEvent) {

        log.info("Order event recieved => {}", orderEvent.toString());
    }
}
