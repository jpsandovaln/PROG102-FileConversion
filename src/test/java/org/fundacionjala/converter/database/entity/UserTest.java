package org.fundacionjala.converter.database.entity;

import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class UserTest {

    private static final long LONG_ONE = 1;
    private static final String AN_USERNAME = "jhon1";
    private static final String A_NAME = "Jhon";
    private static final String A_LASTNAME = "Smith";
    private static final String A_PASSWORD = "p@55uu0Rd";
    private static final String A_ROL = "common";
    private static final String USER_EXPECTED_TO_STRING = "User [id=" + LONG_ONE + ", username=" + AN_USERNAME + "]";

    @Test
    public void createVoidUserTest () {
        User user = new User();
        Assert.assertNotNull(user);
    }

    @Test
    public void createVoidUserWithAttributesTest () {
        User user = new User(LONG_ONE, AN_USERNAME, A_NAME, A_LASTNAME, A_PASSWORD, A_ROL);
        Assert.assertNotNull(user);
    }

    @Test
    public void getIdTest() throws NullAttributeException {
        User user = new User();
        user.setId(LONG_ONE);
        long actual = user.getId();
        long expected = LONG_ONE;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getNullIdTest() {
        User user = new User();
        Assert.assertNull(user.getId());
    }

    @Test
    public void getUsernameTest() throws NullAttributeException {
        User user = new User();
        user.setUsername(AN_USERNAME);
        String actual = user.getUsername();
        String expected = AN_USERNAME;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullUsernameTest() {
        User user = new User();
        try{
            user.getUsername();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"username\" is null";
            Assert.assertEquals(expected, actual);
        }
    }
    @Test
    public void getNameTest() throws NullAttributeException {
        User user = new User();
        user.setName(A_NAME);
        String actual = user.getName();
        String expected = A_NAME;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullNameTest() {
        User user = new User();
        try{
            user.getName();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"name\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void getLastNameTest() throws NullAttributeException {
        User user = new User();
        user.setLastName(A_LASTNAME);
        String actual = user.getLastName();
        String expected = A_LASTNAME;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullLastNameTest() {
        User user = new User();
        try{
            user.getLastName();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"lastName\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void getPasswordTest() throws NullAttributeException {
        User user = new User();
        user.setPassword(A_PASSWORD);
        String actual = user.getPassword();
        String expected = A_PASSWORD;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullPasswordTest() {
        User user = new User();
        try{
            user.getPassword();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"password\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void getRolTest() throws NullAttributeException {
        User user = new User();
        user.setRol(A_ROL);
        String actual = user.getRol();
        String expected = A_ROL;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullRolTest() {
        User user = new User();
        try{
            user.getRol();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"rol\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void userToStringTest() {
        User user = new User();
        user.setId(LONG_ONE);
        user.setUsername(AN_USERNAME);
        String actual = user.toString();
        String expected = USER_EXPECTED_TO_STRING;
        Assert.assertEquals(expected, actual);
    }
}
