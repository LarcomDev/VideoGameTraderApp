package com.larcomlabs.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication
{

    public static void main(String[] args)
    {
        try {
            Thread.sleep(3000);
            SpringApplication.run(EmailServiceApplication.class, args);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
