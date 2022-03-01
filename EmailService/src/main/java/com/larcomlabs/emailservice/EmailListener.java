package com.larcomlabs.emailservice;

import com.larcomlabs.emailservice.Models.EmailObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Component
public class EmailListener
{
    @Autowired
    private JavaMailSender sender;

    @RabbitListener(queues = "${larcomlabs.rabbitmq.queue}")
    public void receiveMessage(Message msg)
    {
        System.out.println(msg.getPayload().getClass().getSimpleName());
        byte[] bytes = (byte[]) msg.getPayload();

        EmailObject obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInput input = new ObjectInputStream(bis);
            obj = (EmailObject) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmailObject? " + obj.getClass().getSimpleName());
        System.out.println("Contents: " + obj.toString());

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
