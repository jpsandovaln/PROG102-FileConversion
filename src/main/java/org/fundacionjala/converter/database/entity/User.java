/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.database.entity;

import org.fundacionjala.converter.database.exception.NullAttributeException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * @author Elizabeth Bravo Flores, Mirko Romay
 * @version 0.3
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String rol;

    public User() {
    }

    public User(final Long id, final String username, final String name, final String lastName, final String password, final String rol) {
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() throws NullAttributeException {
        if (name != null) {
            return name;
        } else {
            throw new NullAttributeException("name");
        }
    }
    /**
     * @return the last name
     */
    public String getLastName() throws NullAttributeException {
        if (lastName != null) {
            return lastName;
        } else {
            throw new NullAttributeException("lastName");
        }
    }
    /**
     * @return the last password
     */
    public String getPassword() throws NullAttributeException {
        if (password != null) {
            return password;
        } else {
            throw new NullAttributeException("password");
        }
    }
    /**
     * @return the username
     */
    public String getUsername() throws NullAttributeException {
        if (username != null) {
            return username;
        } else {
            throw new NullAttributeException("username");
        }
    }
    /**
     * @return the rol
     */
    public String getRol() throws NullAttributeException {
        if (rol != null) {
            return rol;
        } else {
            throw new NullAttributeException("rol");
        }
    }
    /**
     * @param id the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }
    /**
     * @param rol the username to set
     */
    public void setRol(final String rol) {
        this.rol = rol;
    }
    /**
     * method toString
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + "]";
    }
}
