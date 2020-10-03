/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.executor.Executor;
import org.fundacionjala.converter.executor.exception.CommandListEmptyException;
import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.commons.exception.ConvertDocException;
import org.fundacionjala.converter.model.commons.exception.InvalidDataException;
import org.fundacionjala.converter.model.commons.exception.ReadFileException;
import org.fundacionjala.converter.model.commons.validation.FormatValidation;
import org.fundacionjala.converter.model.configPath.ConfigPath;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.fundacionjala.converter.model.utility.ConvertDoc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Angela Martinez
 * @version 1.0
 */
public class ExtractTextFacade {
    private ChecksumMD5 checksumMD5;
    private ReaderText reader;
    private ConvertDoc convertDoc;
    private ExtractTextModel extractor;
    private Executor executor;
    private List<String> resultList;

    public ExtractTextFacade() {
        checksumMD5 = new ChecksumMD5();
        reader = new ReaderText();
        convertDoc = new ConvertDoc();
        extractor = new ExtractTextModel();
        executor = new Executor();
        resultList = new ArrayList<>();
    }

    /**
     * Creates a document
     * @param parameter - the parameter to execute the conversion using tesseract
     */
    public List<String> extractText(final ExtractTextParameter parameter) throws ReadFileException, InvalidDataException,
            InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, ConvertDocException, CommandListEmptyException {
        parameter.validate();
        String format = parameter.getFormat();
        parameter.setOutputFile(ConfigPath.getConvertedFilesPath());
        parameter.setFileName(checksumMD5.getMD5(parameter.getInputFile()));
        if (format.equals(FormatValidation.FORMAT_TXT)) {
            String newName = executor.executeCommandsList(extractor.createCommand(parameter)).get(0) + FormatValidation.FORMAT_TXT;
            List<String> listResult = new ArrayList<>();
            listResult.add(newName);
            return listResult;
        } else {
            convertExtractTextTo(parameter);
        }
        return resultList;
    }

    /**
     * Converts extracted text to a format specific
     * @param parameter
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws ReadFileException
     * @throws ConvertDocException
     */
    private void convertExtractTextTo(final ExtractTextParameter parameter) throws InterruptedException, ExecutionException, IOException, ReadFileException, ConvertDocException, CommandListEmptyException {
        String format = parameter.getFormat();
        executor.executeCommandsList(extractor.createCommand(parameter));
        String outputFile = parameter.getOutputFile() + parameter.getFileName();
        String result = reader.readFile(outputFile + FormatValidation.FORMAT_TXT);
        if (format.equals(FormatValidation.FORMAT_PDF)) {
            String outputFileChanged = extractor.changeName(parameter.getOutputFile(), parameter.getFileName(), FormatValidation.FORMAT_PDF);
            resultList.add(convertDoc.createDocumentPdf(parameter.getOutputFile() + outputFileChanged, result));
        }
        if (format.equals(FormatValidation.FORMAT_DOCX)) {
            String outputFileChanged = extractor.changeName(parameter.getOutputFile(), parameter.getFileName(), FormatValidation.FORMAT_DOCX);
            resultList.add(convertDoc.createDocumentWord(parameter.getOutputFile() + outputFileChanged, result));
        }
        eraseDocument(format, outputFile);
    }

    /**
     * Erases the txt when the type is word or pdf
     * @param format
     * @param outputFile
     */
    private void eraseDocument(final String format, final String outputFile) throws IOException {
        if (format.equals(FormatValidation.FORMAT_DOCX) || format.equals(FormatValidation.FORMAT_PDF) || format.equals(FormatValidation.FORMAT_TXT)) {
            Files.delete(Paths.get(outputFile + FormatValidation.FORMAT_TXT));
        }
    }
}
