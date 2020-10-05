package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.database.repository.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileServiceTest {
  @Mock
  private FileRepository fileRepository;

  @InjectMocks
  private FileService fileService;

  @Test
  public void getFileList() {
    List<File> files = new ArrayList();
    files.add(new File("storage/inputFiles/1nar.jpg","95384efd0209233dde8003713cbdb9c3"));
    files.add(new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0"));

    given(fileRepository.findAll()).willReturn(files);
    List<File> expected = fileService.getFileList();
    assertEquals(2, expected.size());
  }

  @Test
  public void findFileById() {

    File file = new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0");

    when(fileRepository.findById((long) 1)).thenReturn(Optional.of(file));
    final File expected  = fileService.getFileById(1);
    assertThat(expected).isNotNull();
  }

  @Test
  public void findFileByMd5() {
    String md5 = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
    File file = new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0");
    when(fileRepository.findByMd5(md5)).thenReturn(file);
    final File expected  = fileService.getFileByMd5(md5);
    assertThat(expected).isNotNull();
  }

  @Test
  public void deleteFileByMd5() {
    String md5 = "e1b3fab24c8af81c1aa13dbbb4e44ff0";
    Exception exception = Assertions.assertThrows(
            NonExistentException.class,
            () -> {
              fileService.deleteFileByMd5(md5);
            }
    );
  }

  @Test
  public void updateFile() {
    File file = new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0");
    given(fileRepository.save(file)).willReturn(file);
    Exception exception = Assertions.assertThrows(
            Exception.class,
            () -> {
              fileService.updateFile(file);;
            }
    );
  }

  @Test
  public void deleteFile() {
    File file = new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0");
    given(fileRepository.save(file)).willReturn(file);
    Exception exception = Assertions.assertThrows(
            Exception.class,
            () -> {
              fileService.deleteFile(file);;
            }
    );
  }

  @Test
  public void saveFile() {
    File file = new File("storage/inputFiles/test.mp3", "e1b3fab24c8af81c1aa13dbbb4e44ff0");
    given(fileRepository.save(file)).willReturn(file);
    fileService.saveFile(file);
  }
}