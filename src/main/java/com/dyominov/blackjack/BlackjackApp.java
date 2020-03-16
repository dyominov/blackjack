package com.dyominov.blackjack;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dyominov.blackjack.*")
public class BlackjackApp {

    public static void main(String[] args) {
        SpringApplication.run(BlackjackApp.class, args);
    }
}
