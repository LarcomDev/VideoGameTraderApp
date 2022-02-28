package com.larcomlabs.lab1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailSender
{
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${larcomlabs.rabbitmq.exchange}")
    private String exchange;

    @Value("${larcomlabs.rabbitmq.routingKey}")
    private String routingKey;

    @Scheduled
    public void send(String message)
    {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
