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
    * displays home view
    * @return home view
    */
    @GetMapping(value = {"/","home"})
    public String home() {
        return "home";
    }
}
