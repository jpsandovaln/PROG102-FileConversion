/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.controller.response.ErrorResponse;
import org.fundacionjala.converter.controller.response.OkResponse;
import org.fundacionjala.converter.database.entity.User;
import org.fundacionjala.converter.controller.service.UserService;
import org.fundacionjala.converter.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Elizabeth Bravo
 * @version 0.2
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;


    /**
     * Displays list of users
     * @param model - the reference Model
     * @return list - the reference String to list of users
     */
    @GetMapping(value = "/list")
    public String userList(final Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "list";
    }
    /**
     * Saves a new user
     * @param model - the reference Model
     * @param user - the reference to User to save
     * @return save - the reference String to add a user
     */
    @PostMapping(value = "/add")
    public String saveUser(final User user, final Model model) {
        userService.saveUser(user);
        return "User saved";
    }
    /**
     * Edits an user
     * @param id - the reference int of id of the user to edit
     * @param user - the reference to User to edit
     * @param model - the reference Model
     * @return edit - the reference String to edit a user
     */
    @PostMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") final Long id, final User user, final Model model) {
        try {
            userService.updateUser(user, id);
            return "User edited";
        } catch (NonExistentException e) {
            return "The user with Id: " + id + "does not exist";
        }
    }
    /**
     * Deletes an user by id
     * @param id - the reference int of id of the user to delete
     * @param model - the reference Model
     * @return delete
     */
    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable final long id, final Model model) {
        try {
            userService.deleteUser(id);
            return "User deleted";
        } catch (NonExistentException e) {
            return "The user with Id: " + id + "does not exist";
        }
    }

    /**
     * Creates a user
     * @param name - the reference String of user's name
     * @param lastName - the reference String of user's lastname
     * @param username - the reference String of user's username
     * @param password - the reference String of user's password
     * @param rePassword - the reference String of user's rePassword
     * @param attributes - the reference RedirectAttributes of user's attributes
     * @return responseEntity with message to correct or incorrect inputs.
     * @return ResponseEntity - the reference to message to correct inputs if user is created successfully;
     *          displays a message with incorrect inputs otherwise
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public ResponseEntity createUser(final String name, final String lastName, final String username,
                                     final String password, final String rePassword, final RedirectAttributes attributes) {
        if (password.equals(rePassword)) {
            if (userRepo.findUserByUsername(username) == null) {
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setUsername(username);
                user.setRol("user");
                user.setPassword(password);
                userService.saveUser(user);
                return ResponseEntity.ok().body(new OkResponse<Integer>(HttpServletResponse.SC_OK,
                        "Your account was created! Please, login to continue."));
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST,
                        "The username \"" + username + "\" already exists, please change it."));
            }
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST,
                    "Password and Password Confirmation are different."));
        }
    }
}
