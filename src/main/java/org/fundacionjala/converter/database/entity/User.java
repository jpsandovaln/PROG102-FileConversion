/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.database.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<File> files = new HashSet<File>();

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
    public String getName() {
        return name;
    }
    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @return the last password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }
    /**
     * @return Files related the user
     */
    public Set<File> getFiles() {
        return files;
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
     * @param  files to relate
     */
    public void setFiles(final Set<File> files) {
        this.files = files;
    }
    /**
     * method toString
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", lastName=" + lastName + "]";
    }
}
