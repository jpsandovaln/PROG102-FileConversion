/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
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
