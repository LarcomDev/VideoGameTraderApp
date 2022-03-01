package com.larcomlabs.lab1.Configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class EmailConfig
{
    @Autowired
    AmqpAdmin admin;

    @Value("${larcomlabs.rabbitmq.queue}")
    private String queue;

    @Value("${larcomlabs.rabbitmq.exchange}")
    private String exchange;

    @Value("${larcomlabs.rabbitmq.routingKey}")
    private String routingKey;

    private Queue emailQueue;
    private DirectExchange emailExchange;
    private Binding exchangeBinding;

    @PostConstruct
    public void createQueue()
    {
        this.emailQueue = new Queue(queue, true);
        this.emailExchange = new DirectExchange(exchange);
        this.exchangeBinding = BindingBuilder.bind(emailQueue).to(emailExchange).with(routingKey);
        admin.declareExchange(emailExchange);
        admin.declareQueue(emailQueue);
        admin.declareBinding(exchangeBinding);
    }

    @Bean
    public Queue queue()
    {
        return emailQueue;
    }

    @Bean
    public DirectExchange exchange()
    {
        return emailExchange;
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange)
    {
        return exchangeBinding;
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory factory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
