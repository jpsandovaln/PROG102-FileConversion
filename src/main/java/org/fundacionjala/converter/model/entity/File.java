package org.fundacionjala.converter.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String md5;

    public File() {
    }

    public File(final String path, final String md5) {
        this.path = path;
        this.md5 = md5;
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
     * method toString
     * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        return "File [id=" + id + ", md5=" + md5 + ", path=" + path + "]";
    }
}
