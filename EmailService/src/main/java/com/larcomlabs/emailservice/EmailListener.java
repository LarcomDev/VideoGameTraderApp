package com.larcomlabs.emailservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener
{
    @RabbitListener(queues = "${larcomlabs.rabbitmq.queue}")
    public void receiveMessage(String msg)
    {
        System.out.println("This is the message: " + msg);
    }
}
