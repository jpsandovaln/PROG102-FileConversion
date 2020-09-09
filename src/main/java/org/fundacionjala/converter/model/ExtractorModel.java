package org.fundacionjala.converter.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractorModel {
    /**
     * created by default
     */
    public ExtractorModel() {

    }

    /**
     * choose the format of the document
     * @param source the path of the initial
     * @param target the path that will be crated
     * @param language the language that will be created
     * @param type the type of format
     * @param exec the paht of the binary
     * @return  if the result was succesfull
     */
    public String convertDocument(final String source, final String target, final String language, final String type, final String exec) {
        String[] command = {"cmd"};
        String advertaisment = "", result = "";
        try {
            Process process = Runtime.getRuntime().exec(command);
            PrintWriter stdin = new PrintWriter(process.getOutputStream());
            switch (language) {
                case "español":
                    stdin.println("\"" + exec + "\" \"" + source + "\" \"" + target + "\" -l spa");
                    break;
                case "english":
                    stdin.println("\"" + exec + "\" \"" + source + "\" \"" + target + "\"");
                    break;
                default:
                    stdin.println("\"" + exec + "\" \"" + source + "\" \"" + target + "\"");
                    advertaisment = "Sorry language not supported we are going to do it in english";
            }
            stdin.close();
            process.waitFor();
            result = readAFile(target + ".txt");
            switch (type) {
                case "word":
                    Files.delete(Paths.get(target + ".txt"));
                    new ConvertDoc().createDocumentWord(target, result);
                    result = advertaisment + "\n created succesfull";
                    break;
                case "pdf":
                    Files.delete(Paths.get(target + ".txt"));
                    new ConvertDoc().createDocumentPdf(target, result);
                    result = advertaisment + "\n created succesfull";
                    break;
                case "SS":
                    Files.delete(Paths.get(target + ".txt"));
                    result += advertaisment;
                    break;
                case "text":
                    result = advertaisment + "\n created succesfull txt";
                    break;
                default:
                    Files.delete(Paths.get(target + ".txt"));
                    result = "format not supported please insert a valid format";
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
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
