package org.fundacionjala.converter.controller;

import org.fundacionjala.converter.database.entity.User;
import org.fundacionjala.converter.controller.service.UserService;
import org.fundacionjala.converter.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    @PostMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") final Long id, final User user, final Model model) {
        userService.updateUser(user, id);
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

    /**
     *
     * @param name
     * @param lastName
     * @param username
     * @param password
     * @param rePassword
     * @return responseEntity with message to correct or incorrect inputs.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public RedirectView createUser(final String name, final String lastName, final String username, final String password, final String rePassword, final RedirectAttributes attributes) {
        if (password.equals(rePassword)) {
            if (userRepo.findUserByUsername(username) == null) {
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setUsername(username);
                user.setRol("user");
                user.setPassword(password);
                userService.saveUser(user);
                attributes.addFlashAttribute("message", "Your account was created! Please, login to continue.");
                return new RedirectView("/login", true);
            } else {
                attributes.addFlashAttribute("message", "The username \"" + username + "\" already exists, please change it.");
                return new RedirectView("/createAccount", true);
            }
        } else {
            attributes.addFlashAttribute("message", "Password and Password Confirmation are different.");
            return new RedirectView("/createAccount", true);
        }
    }
}
