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
    private static final String FORMATZIP = ".zip";
    private static final String SLASH = "/";
    private int ex;
    public FileZipped(final int ex) {
        this.ex = ex;
    }

    /**
     *
     * @param modelParameter to get the format attribute that is inside of the modelparameter
     * @param list the list that returns the executor with the path of the files converted
     * @return the path of the zip
     * @throws IOException
     */
    public static String zipper(final ModelParameter modelParameter, final List<String> list) throws IOException {
        String locationPath = Paths.get("").toAbsolutePath() + SLASH;
        String outputPath =  locationPath + modelParameter.getOutputFile() + SLASH + modelParameter.getMd5() + FORMATZIP;
        ZipOutputStream os = new ZipOutputStream(new FileOutputStream(outputPath));
        os.setLevel(Deflater.DEFAULT_COMPRESSION);
        os.setMethod(Deflater.DEFLATED);
        int num = 0;
        for (String file: list) {
            ZipEntry zipEntry = new ZipEntry(num + "." + FilenameUtils.getExtension(file));
            os.putNextEntry(zipEntry);
            FileInputStream fis = new FileInputStream(locationPath + file);
            byte[] buffer = new byte[BYTE];
            int len = 0;
            while (0 < (len = fis.read(buffer))) {
                os.write(buffer, 0, len);
            }
            fis.close();
            num++;
        }
        os.closeEntry();
        os.close();
        File file;
        for (String path:list) {
            file = new File(path);
            file.delete();
        }
        return modelParameter.getOutputFile() + SLASH + modelParameter.getMd5() + FORMATZIP; //modelParameter.getOutputFile() + ".zip";
    }
}
