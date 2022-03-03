package com.larcomlabs.emailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larcomlabs.emailservice.Models.EmailObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailListener
{
    @Autowired
    private JavaMailSender sender;

    @RabbitListener(queues = "${larcomlabs.rabbitmq.queue}")
    public void receiveMessage(String msg)
    {
        //deserialize json from string in msg
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmailObject obj = mapper.readValue(msg, EmailObject.class);
            System.out.println("Object type: " + obj.getClass().getSimpleName());
            System.out.println("Contents:" + obj.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        String msgBody = getBody(obj);
//
//        for(String email: obj.getEmails()) {
//            //TODO: Loop over each email to send.
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setFrom("larcomlabs@gmail.com");
//            msg.setTo(email);
//            msg.setSubject(getSubject(obj));
//            msg.setText(msgBody);
//
//            sender.send(msg);
//        }
    }

    //decide what body to send based on what is received (type)
    public String getBody(EmailObject obj)
    {
        switch (obj.getType()) {
            case PASSWORD_RESET:
                return "Password has been reset.\n Your new temporary password is" + obj.getNewPass();
            case OFFER_CREATED:
                return "Offer has been created.";
            case OFFER_ACCEPTED:
                return "Offer has been accepted.";
            case OFFER_REJECTED:
                return "Offer has been rejected.";
        }
        return "No Type Provided via RabbitMQ message";
    }

    public String getSubject(EmailObject obj)
    {
        switch (obj.getType()) {
            case PASSWORD_RESET:
                return "Password Reset Request";
            case OFFER_CREATED:
                return "Offer Created";
            case OFFER_ACCEPTED:
                return "Offer Accepted";
            case OFFER_REJECTED:
                return "Offer Rejected";
        }
        return "No Type Provided via RabbitMQ message";
    }
}
