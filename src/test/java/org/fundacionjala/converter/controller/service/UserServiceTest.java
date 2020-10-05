package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.entity.User;
import org.fundacionjala.converter.database.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUserList() {
        List<User> users = new ArrayList();
        users.add(new User((long) 1,"J12", "Juan", "cabrera", "123","user"));
        users.add(new User((long) 2,"username", "Carlos", "Sanchez", "abc","user"));
        users.add(new User((long) 3,"alexxx", "alex","perez", "345","user"));

        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.getUserList();
        assertEquals(3, expected.size());
    }

    @Test
    public void findUserById() {

        User user = new User((long) 1,"J12", "Juan", "cabrera", "123","user");

        when(userRepository.findById((long) 1)).thenReturn(Optional.of(user));
        final Optional<User> expected  = Optional.ofNullable(userService.getUserById((long) 1));
        assertThat(expected).isNotNull();
    }
    @Test
    void saveUser() {
        final User user = new User(1L, "J12", "Juan", "cabrera", "123","user");
        given(userRepository.save(user)).willReturn(user);
        userService.saveUser(user);
    }

    @Test
    public void updateUser() throws NonExistentException {
        final User user1 = new User((long) 1,"username", "Carlos", "Sanchez", "abc","user");
        final User user = new User((long) 2,"username", "Carlos", "Sanchez", "abc","user");
        given(userRepository.save(user)).willReturn(user);
        Exception exception = Assertions.assertThrows(
                NonExistentException.class,
                () -> {
                    userService.updateUser(user1, 2L);;
                }
        );
    }

    @Test
    public void deleteUser() {
        final Long userId=1L;
        Exception exception = Assertions.assertThrows(
                NonExistentException.class,
                () -> {
                    userService.deleteUser(userId);
                }
        );
    }
}