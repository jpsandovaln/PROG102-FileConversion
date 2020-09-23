package org.fundacionjala.converter.database.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * @author Elizabeth Bravo Flores
 * @version 0.2
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
        return "User [name=" + name + ", lastName=" + lastName + "]";
    }
}
