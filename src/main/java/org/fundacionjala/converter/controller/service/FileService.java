/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.fundacionjala.converter.controller.exceptions.NonExistentException;
import org.fundacionjala.converter.database.entity.File;
import org.fundacionjala.converter.database.exception.NullAttributeException;
import org.fundacionjala.converter.database.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Laura Montaño, Mirko Romay
 * @version 0.3
 */
@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    /**
     * Returns all the elements in the table files
     * @return fileList - The list of elements, if there is no element, returns an empty list
     */
    public List<File> getFileList() {
        List<File> fileList = new ArrayList<File>();
        for (File file : fileRepository.findAll()) {
            fileList.add(file);
        }
        return fileList;
    }

    /**
     * Returns element asociated to the given id
     * @param index - the Long id of element to show
     * @return file - a reference to the file from the table
     */
    public File getFileById(final int index) {
        Optional<File> file = fileRepository.findById((long) index);
        if (file.isPresent()) {
            return file.get();
        } else {
            return null;
        }
    }

    /**
     * Returns element asociated to the given md5
     * @param md5 - String md5 of element to show
     * @return file - a reference to the file from the table
     */
    public File getFileByMd5(final String md5) {
        File file = fileRepository.findByMd5(md5);
        return file;
    }

    /**
     * Saves a file in the database
     * @param file - the reference to the object File to save
     */
    public void saveFile(final File file) {
        File fileTemp = new File();
        try {
            if (file.getMd5() != null && file.getPath() != null) {
                fileTemp.setPath(file.getPath());
                fileTemp.setMd5(file.getMd5());
                fileTemp.setUser(file.getUser());
                fileRepository.save(fileTemp);
            }
        } catch (NullPointerException | NullAttributeException e) {
            e.printStackTrace();
            System.out.println("NullPointerException occurred");
        }
    }

    /**
     * Updates a file in the table "files"
     * @param file - the reference to the object File to update
     */
    public void updateFile(final File file) throws NullAttributeException, NonExistentException {
        File fileTemp = fileRepository.findByMd5(file.getMd5());
        if (fileTemp != null) {
            if (file.getMd5() != null && file.getPath() != null) {
                fileTemp.setPath(file.getPath());
                fileTemp.setMd5(file.getMd5());
                fileTemp.setUser(file.getUser());
                fileRepository.save(fileTemp);
            }
        } else {
            throw new NonExistentException("update");
        }
    }

    /**
     * Deletes the reference to a file in the table "files"
     * @param file - the reference to the object File to delete
     * @throws NonExistentException
     */
    public void deleteFile(final File file) throws NullAttributeException, NonExistentException {
        File fileToDelete = fileRepository.findByMd5(file.getMd5());
        if (fileToDelete != null) {
            fileRepository.delete(fileToDelete);
        } else {
            throw new NonExistentException("delete");
        }
    }

    /**
     * Deletes a file in the table "files"
     * @param md5 - the String md5 of the file to delete
     * @throws NonExistentException
     */
    public void deleteFileByMd5(final String md5) throws NonExistentException {
        File fileToDelete = fileRepository.findByMd5(md5);
        if (fileToDelete != null) {
            fileRepository.delete(fileToDelete);
        } else {
            throw new NonExistentException("delete");
        }
    }
}
