package org.fundacionjala.converter.model.service;

import org.fundacionjala.converter.model.entity.User;
import org.fundacionjala.converter.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Elizabeth Bravo
 * @version 0.2
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Returns all the elements in the table users
     * @return userList - The list of elements, if there is no element, returns an empty list
     */
    public List<User> getUserList() {
        return ((List<User>) userRepository.findAll());
    }

    /**
     * Returns element asociated to the given id
     * @param id - the Long id of element to get
     * @return user - a reference to the user from the table users
     */
    public Optional<User> getUserById(final long id) {
        return userRepository.findById(id);
    }

    /**
     * Saves a user in the database
     * @param user - the reference to the User to save
     */
    public void saveUser(final User user) {
        User userTemp = userRepository.save(user);
        if (userTemp != null) {
            System.out.println("user saved");
        } else {
            System.out.println("null");
        }
    }

    /**
     * Updates a user in the table "users"
     * @param user - the reference to the User to update
     */
    public void updateUser(final User user) {
        if (userRepository.findUserById(user.getId()) != null) {
            User userTemp = userRepository.findUserById(user.getId());
            userTemp.setName(user.getName());
            userTemp.setLastName(user.getLastName());
            userTemp.setPassword(user.getPassword());
            userTemp.setUsername(user.getUsername());
            userRepository.save(userTemp);
        }
    }

    /**
     * Deletes the reference to a user in the table "users"
     * @param id - the reference to the user to delete
     */
    public void deleteUser(final long id) {
        userRepository.deleteById(id);
    }

    /**
     * Deletes the reference to a user in the table "users"
     * @param id - the reference to the user to delete
     */
    public User findUserById(final long id) {
        if (!userRepository.findById(id).isEmpty()) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
