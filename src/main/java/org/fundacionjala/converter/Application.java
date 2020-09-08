package org.fundacionjala.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private String name;
    public Application() {
        name = "Converter project";
    }
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
