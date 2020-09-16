package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.model.entity.User;
import org.fundacionjala.converter.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * displays list of users
     * @param model
     * @return view
     */
    @GetMapping(value = "/list")
    public String userList(final Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "list";
    }
    /**
     * Save a new user
     * @param model
     * @param user, new user to save
     * @return view
     */
    @PostMapping(value = "/add")
    public String saveUser(final User user, final Model model) {
        userService.saveUser(user);
        return "save";
    }
    /**
     * edit an user
     * @param model
     * @param user to edit
     * @return view
     */
    @PostMapping(value = "/edit")
    public String editUser(final User user, final Model model) {
        userService.updateUser(user);
        return "edit";
    }
    /**
     * delete an user by id
     * @param model
     * @param id, identify of user to delete
     * @return view
     */
    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable final long id, final Model model) {
        userService.deleteUser(id);
        return "delete";
    }
}
