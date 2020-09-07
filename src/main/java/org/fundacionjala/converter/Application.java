package org.fundacionjala.converter;

import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
    /**
     *
     * @param fileRepository
     * @return
     */
    @Bean
    public CommandLineRunner fileDemo(final FileService fileService) {
        return (args) -> {

        // create Files
        /*fileService.saveFile(new File("file1", "md51"));
        fileService.saveFile(new File("file2", "md52"));
        fileService.saveFile(new File("file3", "md53"));*/

        // fetch all Files
        System.out.println("Files found with findAll():");
        System.out.println("---------------------------");
        fileService.getFileList();
        System.out.println();

        // fetch File by id
        File file = fileService.getFileById(2);
        System.out.println("File found with findById(2):");
        System.out.println("-----------------------------");
        System.out.println(file.toString());
        System.out.println();

        // fetch all Files that contain keyword `md53`
        System.out.println("Files that contain keyword 'md53':");
        System.out.println("-----------------------------------");
        File fileTemp = fileService.getFileByMd5("md53");
        System.out.println(fileTemp.toString());
        System.out.println();

        // update File path
        File fileUpdate = new File("filePath123", "md51");
        System.out.println("Update File path:");
        System.out.println("------------------");
        fileService.updateFile(fileUpdate);
        System.out.println();
        };
    }
}
