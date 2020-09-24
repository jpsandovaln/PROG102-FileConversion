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
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    /**
     * @param path the path to set
     */
    public void setPath(final String path) {
        this.path = path;
    }
    /**
     * @param md5 the md5 to set
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
    }
    /**
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
}
