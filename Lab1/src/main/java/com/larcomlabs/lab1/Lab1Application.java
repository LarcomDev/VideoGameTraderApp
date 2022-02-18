package com.larcomlabs.lab1;

import com.larcomlabs.lab1.Configs.MyUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class Lab1Application
{
    public static void main(String[] args)
    {
        try{
            Thread.sleep(2000);
            SpringApplication.run(Lab1Application.class, args);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
