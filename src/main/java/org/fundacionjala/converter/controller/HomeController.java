package org.fundacionjala.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @GetMapping(value = {"/","home"})
    //@RequestMapping(value = {"/","home"})
    public String home() {
        return "home";
    }
}
