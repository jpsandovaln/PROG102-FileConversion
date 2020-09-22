package org.fundacionjala.converter.facade;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.command.extractText.DocType;
import org.fundacionjala.converter.model.command.extractText.ExtractTextModel;
import org.fundacionjala.converter.model.command.extractText.ReaderText;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.fundacionjala.converter.model.utility.ConvertDoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtractTextFacade {
    private ChecksumMD5 checksumMD5;
    private ReaderText reader;
    private ConvertDoc convertDoc;
    private ConfigPath configPath;
    private ExtractTextModel extractor;
    private Executor executor;
    private List<String> resultList;

    public ExtractTextFacade() {
        checksumMD5 = new ChecksumMD5();
        reader = new ReaderText();
        convertDoc = new ConvertDoc();
        configPath = new ConfigPath();
        extractor = new ExtractTextModel();
        executor = new Executor();
        resultList = new ArrayList<>();
    }

    /**
     * Create a document
     * @param parameter - the parameter to execute the conversion using tesseract
     */
    public List<String> extractText(ExtractTextParameter parameter) throws Exception {
        DocType type = parameter.getType();
        parameter.setOutputFile(configPath.getConvertedFilesPath());
        parameter.setFileName(checksumMD5.getMD5(parameter.getInputFile()));
        if (type.equals(DocType.TXT)) {
            return executor.executeCommandsList(extractor.createCommand(parameter));
        } else {
            executor.executeCommandsList(extractor.createCommand(parameter));
            String outputFile = parameter.getOutputFile() + parameter.getFileName();
            String result = reader.readFile(outputFile + ExtractTextModel.TXT_EXTENSION);
            if (type.equals(DocType.PDF)) {
                resultList.add(convertDoc.createDocumentPdf(outputFile, result));
            }
            if (type.equals(DocType.DOCX)) {
                resultList.add(convertDoc.createDocumentWord(outputFile, result));
            }
            eraseDocument(type, outputFile);
            return resultList;
        }
    }

    /**
     * Erase the txt when the type is word or pdf
     * @param type
     * @param outputFile
     */
    private void eraseDocument(final DocType type, final String outputFile) throws Exception {
        if (type.equals(DocType.DOCX) || type.equals(DocType.PDF) || type.equals(DocType.TXT)) {
            Files.delete(Paths.get(outputFile + ExtractTextModel.TXT_EXTENSION));
        }
    }
}
