/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Elizabeth Bravo
 * @version 0.1
 */
@Controller
public class HomeController {

    /**
    * Displays home view
    * @return index - the reference String to home view
    */
    @GetMapping(value = {"/", "home"})
    public String home() {
        return "index";
    }
}
