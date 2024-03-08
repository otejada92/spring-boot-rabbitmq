package org.dev.emailservice.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.emailservice.dto.Order;
import org.dev.emailservice.dto.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderConsumer {

    private JavaMailSender javaMailSender;

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consume(OrderEvent orderEvent) {
        log.info("Order event received in email service => {}", orderEvent.toString());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(orderEvent.getOrder().getEmail());
        message.setSubject("testing subject");
        Order order = orderEvent.getOrder();
        message.setText(String.format("Hi Mr/Mrs, \n Your %s order has a total of %d items with a price of  %f",
                order.getName(), order.getQty(), order.getPrice()));

        javaMailSender.send(message);
    }

}
