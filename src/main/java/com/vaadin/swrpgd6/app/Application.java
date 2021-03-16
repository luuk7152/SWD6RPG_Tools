package com.vaadin.swrpgd6.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{

    public static void main(String[] args)
    {
        System.out.println("SWRPGD6 Tools has started.");

        SpringApplication.run(Application.class, args);

        System.out.println("SWRPGD6 Tools has ended.");
    }

}
