package org.fundacionjala.converter.controller.service;

import java.util.List;

import org.fundacionjala.converter.Application;
import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest(classes = Application.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class FileServiceTest {

    @Autowired
    FileService service;

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateFile() throws NullAttributeException {
        File file = new File();
        service.saveFile(new File("inputFile", "md5"));
        file = service.getFileById(1);
        Long id = file.getId();
        Assertions.assertTrue(id > 0);

    }

    @Test
    @Order(2)
    public void testFindByMd5() throws NullAttributeException {
      File file = service.getFileByMd5("md5");
        Assertions.assertEquals(file.getPath(), "inputFile");
    }

    @Test
    @Order(3)
    public void testListFiles() {
        List<File> files = service.getFileList();
        Assertions.assertTrue(files.size() > 0);
    }
    @Test
    @Rollback(false)
    @Order(4)
    public void testDeleteFileByMd5() throws NonExistentException {
        service.saveFile(new File("inputFile2", "md52"));
        service.deleteFileByMd5("md52");
        File deletedFile = service.getFileByMd5("md52");
        Assertions.assertNull(deletedFile);
    }
}