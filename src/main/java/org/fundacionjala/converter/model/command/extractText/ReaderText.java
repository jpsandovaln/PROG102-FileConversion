package org.fundacionjala.converter.model.command.extractText;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderText {
    private BufferedReader buffer;

    public ReaderText() {
        buffer = null;
    }
    /**
     * read a file
     * @param fileName the path and name of the file
     * @return the content of the file in a string
     */
    public String readFile(final String fileName) throws Exception{
        String readString = "";
        try {
            String sCurrentLine;
            buffer = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = buffer.readLine()) != null) {
                readString += sCurrentLine;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (Exception ex) {
                throw ex;
            }
        }
        return readString;
    }
}
