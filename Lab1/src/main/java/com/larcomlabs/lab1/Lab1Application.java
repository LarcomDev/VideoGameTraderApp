package com.larcomlabs.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab1Application
{
    public static void main(String[] args)
    {
        try{
            Thread.sleep(5000);
            SpringApplication.run(Lab1Application.class, args);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
