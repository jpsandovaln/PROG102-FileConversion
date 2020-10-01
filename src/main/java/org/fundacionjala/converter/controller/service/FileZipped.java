/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.controller.service;

import org.apache.commons.io.FilenameUtils;
import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipped {
    private static final int BYTE = 1024;
    private static final String FORMAT_ZIP = ".zip";
    private static final String SLASH = "/";
    private int ex;
    public FileZipped(final int ex) {
        this.ex = ex;
    }

    /**
     * Compresses the generated files
     * @param modelParameter to get the format attribute that is inside of the modelparameter
     * @param list the list that returns the executor with the path of the files converted
     * @return the path of the zip
     * @throws IOException
     */
    public static String zipper(final ModelParameter modelParameter, final List<String> list) throws IOException {
        String locationPath = Paths.get("").toAbsolutePath() + SLASH;
        String outputPath =  locationPath + modelParameter.getOutputFile() + SLASH + modelParameter.getMd5() + FORMAT_ZIP;
        ZipOutputStream os = new ZipOutputStream(new FileOutputStream(outputPath));
        os.setLevel(Deflater.DEFAULT_COMPRESSION);
        os.setMethod(Deflater.DEFLATED);
        for (String file: list) {
            ZipEntry entry = new ZipEntry(FilenameUtils.getName(file));
            os.putNextEntry(entry);
            FileInputStream fis = new FileInputStream(locationPath + file);
            byte[] buffer = new byte[BYTE];
            int len = 0;
            while (0 < (len = fis.read(buffer))) {
                os.write(buffer, 0, len);
            }
            fis.close();
        }
        os.closeEntry();
        os.close();
        File file;
        for (String path:list) {
            file = new File(path);
            file.delete();
        }
        return modelParameter.getOutputFile() + SLASH + modelParameter.getMd5() + FORMAT_ZIP; //modelParameter.getOutputFile() + ".zip";
    }
}
