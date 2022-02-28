package com.larcomlabs.lab1.Configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig
{
    @Value("${larcomlabs.rabbitmq.queue}")
    private String queue;

    @Value("${larcomlabs.rabbitmq.exchange}")
    private String exchange;

    @Value("${larcomlabs.rabbitmq.routingKey}")
    private String routingKey;

    @Bean
    public Queue queue()
    {
        return new Queue(queue, false);
    }

    @Bean
    public DirectExchange exchange()
    {
        return new DirectExchange(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory factory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
