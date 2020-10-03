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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * @author Laura Montaño, Mirko Romay
 * @version 0.3
 */
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String md5;

    @ManyToOne
    @JoinColumn(name = "FK_userId")
    private User user;

    public File() {
    }

    public File(final String path, final String md5) {
        this.path = path;
        this.md5 = md5;
    }

    public File(final String path, final String md5, final User user) {
        this.path = path;
        this.md5 = md5;
        this.user = user;
    }

    public File(final Long id, final String path, final String md5) {
    }

    /**
     * @return the id of the File
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the path of the File
     */
    public String getPath() throws NullAttributeException {
        if (path != null) {
            return path;
        } else {
            throw new NullAttributeException("path");
        }
    }
    /**
     * @return the md5 of the File
     */
    public String getMd5() throws NullAttributeException {
        if (md5 != null) {
            return md5;
        } else {
            throw new NullAttributeException("md5");
        }
    }
    /**
     * @return the user of the File
     */
    public User getUser() {
            return user;
    }

    /**
     *
     * @param id
     */
    public void setId(final long id) {
        this.id = id;
    }
    /**
     * Sets path value
     * @param path the path to set
     */
    public void setPath(final String path) {
        this.path = path;
    }
    /**
     * Sets md5 value
     * @param md5 the md5 to set
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
    }
    /**
     * Sets user value
     * @param user to relate the file
     */
    public void setUser(final User user) {
        this.user = user;
    }
    /**
     * method toString
     * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        return "File [id=" + id + ", md5=" + md5 + ", path=" + path + "]";
    }

    /**
     * method toStringWithUser()
     * @return java.lang.Object#toString() and user information
     */
    public String toStringWithUser() throws NullAttributeException {
        return toString().substring(0, toString().length() - 1) + ", user=" + user.getUsername() + "]";
    }
}
