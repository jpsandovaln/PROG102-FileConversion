/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Julia Escalante
 * @version 0.1
 */
@RestController
public class HelloController {
    /**
    * displays hello world without param
    * @return hello world
    */
    @RequestMapping(method = RequestMethod.POST, value = "hello")
    public String sayHello() {
        return "Hello world";
    }

    /**
    * displays hello concatenated with the name parameter
    * @param name
    * @return string hello name
    */
    @RequestMapping(method = RequestMethod.POST, value = "hello", params = {"name"})
    public String sayHelloWithName(final String name) {
        return "hello " + name;
    }

    /**
    * displays hello concatenated with name, age and nameFile
    * @param name string
    * @param age integer
    * @param file uploaded
    * @return hello, filename, name and age.
    * @throws Exception
    */
    @RequestMapping(method = RequestMethod.POST, value = "hello/file", params = {"name", "age", "file"})
    public String sayHelloMultipleParams(@RequestParam(required = false) final String name, @RequestParam(required = false) final int age, @RequestParam("file") final MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        return "Hello\n" + "Successfully upload file " + fileName + "\n" + "Name: " + name + "\n" + "age: " + age;
    }
}
