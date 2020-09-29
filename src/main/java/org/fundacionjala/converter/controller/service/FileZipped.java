package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.model.parameter.ModelParameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipped {
    private static final int BYTE = 1024;
    private static final String FORMATZIP = ".zip";
    private int ex;
    public FileZipped(final int ex) {
     this.ex = ex;
    }

    /**
     *
     * @param modelParameter to get the format attribute that is inside of the audiomodel
     * @param list the list that returns the executor with the path of the files converted
     * @return the path of the zip
     * @throws IOException
     */
    public static String zipper(final ModelParameter modelParameter, final List<String> list) throws IOException {
        ZipOutputStream os = new ZipOutputStream(new FileOutputStream(modelParameter.getOutputFile() + modelParameter.getMd5() + FORMATZIP));
        os.setLevel(Deflater.DEFAULT_COMPRESSION);
        os.setMethod(Deflater.DEFLATED);
        int num = 1;
        for (String file: list) {
            ZipEntry entry = new ZipEntry(num + modelParameter.getFormat());
            os.putNextEntry(entry);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[BYTE];
            int leido = 0;
            while (0 < (leido = fis.read(buffer))) {
                os.write(buffer, 0, leido);
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
        return modelParameter.getOutputFile() + modelParameter.getMd5() + FORMATZIP; //modelParameter.getOutputFile() + ".zip";
    }
}
