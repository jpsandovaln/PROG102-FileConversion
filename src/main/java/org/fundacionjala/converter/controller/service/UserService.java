package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.entity.User;
import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.fundacionjala.converter.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elizabeth Bravo, Mirko Romay
 * @version 0.3
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final int LEVEL_ENCRYPT = 4;

    /**
     * Returns all the elements in the table users
     * @return userList - The list of elements, if there is no element, returns an empty list
     */
    public List<User> getUserList() {
        return ((List<User>) userRepository.findAll());
    }

    /**
     * Returns element associated to the given id
     * @param id - the Long id of element to get
     * @return user - a reference to the user from the table users
     */
    public User getUserById(final long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    /**
     * Saves a user in the database
     * @param user - the reference to the User to save
     */
    public void saveUser(final User user) {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(LEVEL_ENCRYPT);
        try {
            if (user.getPassword() != null) {
                String passCrypt = bCryptPasswordEncoder.encode(user.getPassword());
                user.setPassword(passCrypt);
            }
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    /**
     * Updates a user in the table "users"
     * @param user - the reference to the User to update
     * @throws NonExistentException
     */
    public void updateUser(final User user, final Long id) throws NonExistentException {
        User userTemp = userRepository.getUserById(id);
        if (userTemp != null) {
            try {
                userTemp.setName(user.getName());
                userTemp.setLastName(user.getLastName());
                userTemp.setUsername(user.getUsername());
                userTemp.setRol(user.getRol());
                userRepository.save(userTemp);
            } catch (NullAttributeException e) {
                e.printStackTrace();
            }
        } else {
            throw new NonExistentException("update");
        }
    }

    /**
     * Deletes the reference to a user in the table "users"
     * @param id - the reference to the user to delete
     * @throws NonExistentException
     */
    public void deleteUser(final long id) throws NonExistentException {
        User userTemp = userRepository.getUserById(id);
        if (userTemp != null) {
            userRepository.deleteById(id);
        } else {
            throw new NonExistentException("delete");
        }
    }

    /**
     * Creates an userDetails
     * @param username- the reference to the user authenticate
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            User user = userRepository.findUserByUsername(username);
            userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    buildGranted(user.getRol()));

        } catch (UsernameNotFoundException | NullAttributeException e) {
            System.out.println("Error: Does not exist users." + e.getMessage());
        }

        return userDetails;
    }

    /**
     * Creates a list<GrantedAuthority> of roles
     * @param rol- the reference to the user authenticate
     * @return auths, a list<GrantedAuthority>
     */
    public List<GrantedAuthority> buildGranted(final String rol) {
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(rol));
        return auths;
    }
}
