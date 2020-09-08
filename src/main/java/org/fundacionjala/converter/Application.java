package org.fundacionjala.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@SpringBootApplication
public class Application {
    private String name;
    public Application() {
        name = "converter project";
    }
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
