package com.vanya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.vanya")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
