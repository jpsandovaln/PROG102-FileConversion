package org.fundacionjala.converter.controller.service;

import org.fundacionjala.converter.model.parameter.multimedia.AudioParameter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipped {
    private static final int BYTE = 1024;
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
    public static String zipper(final AudioParameter modelParameter, final List<String> list) throws IOException {
        ZipOutputStream os = new ZipOutputStream(new FileOutputStream(modelParameter.getOutputFile() + "descarga.zip"));
        os.setLevel(Deflater.DEFAULT_COMPRESSION);
        os.setMethod(Deflater.DEFLATED);
        int num = 0;
        for (String file: list) {
            ZipEntry entrada = new ZipEntry(num + modelParameter.getFormat());
            os.putNextEntry(entrada);
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
        return modelParameter.getOutputFile() + "descarga.zip"; //modelParameter.getOutputFile() + ".zip";
    }
}
