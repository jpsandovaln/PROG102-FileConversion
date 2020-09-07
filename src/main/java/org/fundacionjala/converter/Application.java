package org.fundacionjala.converter;

import org.fundacionjala.converter.model.entity.File;
import org.fundacionjala.converter.model.repository.FileRepository;
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
    public CommandLineRunner fileDemo(final FileRepository fileRepository) {
        return (args) -> {

        // create Files
        fileRepository.save(new File("file1", "md51"));
        fileRepository.save(new File("file2", "md52"));
        fileRepository.save(new File("file3", "md53"));

        // fetch all Files
        System.out.println("Files found with findAll():");
        System.out.println("---------------------------");
        for (File file : fileRepository.findAll()) {
            System.out.println(file.toString());
        }
        System.out.println();

        // fetch File by id
        File file = fileRepository.findById(1L).get();
        System.out.println("File found with findById(1):");
        System.out.println("-----------------------------");
        System.out.println(file.toString());
        System.out.println();

        // fetch all Files that contain keyword `md53`
        System.out.println("Files that contain keyword 'md53':");
        System.out.println("-----------------------------------");
        File fileTemp = fileRepository.findByMd5("md53");
        System.out.println(fileTemp.toString());
        System.out.println();

        // update File path
        File fileUpdate = fileRepository.findById(2L).get();
        fileUpdate.setPath("filePath2");
        fileRepository.save(fileUpdate);
        System.out.println("Update File path:");
        System.out.println("------------------");
        System.out.println(fileUpdate.toString());
        System.out.println();

        // total Files in DB
        System.out.println("Total Files in DB:");
        System.out.println("------------------");
        System.out.println(fileRepository.count());
        System.out.println();
        };
    }
}
