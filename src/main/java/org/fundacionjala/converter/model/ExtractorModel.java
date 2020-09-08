package org.fundacionjala.converter.model;

import org.fundacionjala.converter.commons.Commons;

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
    public String convert(final String source, final String target, final String language, final String type, final String exec) {
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
            result = new Commons().readAFile(target + ".txt");
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
}
