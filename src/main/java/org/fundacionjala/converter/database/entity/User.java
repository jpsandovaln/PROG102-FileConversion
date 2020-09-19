package org.fundacionjala.converter.database.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String username;
    private String name;
    private String lastName;
    private String password;

    public User() {
    }
    public User(final Long id, final String name, final String lastName, final String password) {
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
     * @param id the id to set
     */
    public void setId(final Long id) {
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
     * method toString
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", lastName=" + lastName + "]";
    }
}
