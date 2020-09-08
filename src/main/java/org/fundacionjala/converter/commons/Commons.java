package org.fundacionjala.converter.commons;

import java.io.BufferedReader;
import java.io.FileReader;

public class Commons {

    /**
     * read a file
     * @param fileName the path and name of the file
     * @return the content of the file in a string
     */
    public String readAFile(final String fileName) {
        BufferedReader buffer = null;
        String readString = "";
        try {
            String sCurrentLine;
            buffer = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = buffer.readLine()) != null) {
                readString = readString + sCurrentLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return readString;
    }

}
