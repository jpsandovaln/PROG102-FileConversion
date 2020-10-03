package org.fundacionjala.converter.database.entity;

import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class FileTest {

    private static final long LONG_ONE = 1;
    private static final String A_PATH = "inputFiles\\file.txt";
    private static final String AN_MD5 = "line76Ac0nsh78AcFNsh718Aca0h08p1";
    private static final String A_NAME = "Jhon";
    private static final String AN_USERNAME = "jhon1";
    private static final String FILE_EXPECTED_TO_STRING = "File [id=null, md5=" + AN_MD5 + ", path=" + A_PATH + "]";
    private static final String FILE_WITH_USER_EXPECTED_STRING = "File [id=null, md5=" + AN_MD5 + ", path=" + A_PATH + ", user=" + AN_USERNAME + "]";

    @Test
    public void createVoidFileTest() {
        File file = new File();
        Assert.assertNotNull(file);
    }

    @Test
    public void createFileWithPathAndMd5Test() throws NullAttributeException {
        File file = new File(A_PATH, AN_MD5);
        String actual = file.toString();
        String expected = FILE_EXPECTED_TO_STRING;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createFileWithIdAndPathAndMd5Test() throws NullAttributeException {
        File file = new File(LONG_ONE, A_PATH, AN_MD5);
        Assert.assertNotNull(file);
    }

    @Test
    public void createFileWithPathAndMd5AndUSerTest() throws NullAttributeException {
        User user = new User();
        user.setUsername(AN_USERNAME);
        File file = new File(A_PATH, AN_MD5, user);
        String actual = file.toStringWithUser();
        String expected = FILE_WITH_USER_EXPECTED_STRING;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIdTest() throws NullAttributeException {
        File file = new File();
        file.setId(LONG_ONE);
        long actual = file.getId();
        long expected = LONG_ONE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullIdTest() {
        File file = new File();
        Assert.assertNull(file.getId());
    }

    @Test
    public void getPathTest() throws NullAttributeException {
        File file = new File();
        file.setPath(A_PATH);
        String actual = file.getPath();
        String expected = A_PATH;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullPathTest() {
        File file = new File();
        try{
            file.getPath();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"path\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void getMd5Test() throws NullAttributeException {
        File file = new File();
        file.setMd5(AN_MD5);
        String actual = file.getMd5();
        String expected = AN_MD5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNullMd5Test() {
        File file = new File();
        try{
            file.getMd5();
        } catch (NullAttributeException e) {
            String actual = e.getMessage();
            String expected = "The attribute: \"md5\" is null";
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void getUserTest() throws NullAttributeException {
        User user = new User();
        user.setName(A_NAME);
        File file = new File();
        file.setUser(user);
        String actual = file.getUser().getName();
        String expected = A_NAME;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getNullUserTest() {
        File file = new File();
        Assert.assertNull(file.getUser());
    }
}
