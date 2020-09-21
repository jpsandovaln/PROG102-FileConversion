/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.command.extractText;

import org.fundacionjala.converter.model.ChecksumMD5;
import org.fundacionjala.converter.model.parameter.extractText.ExtractTextParameter;
import org.fundacionjala.converter.model.utility.ConvertDoc;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ConverterExtractedText {
    private ChecksumMD5 checksumMD5;
    private ExtractTextModel extract;
    private ReaderText reader;
    private ConvertDoc convertDoc;

    public ConverterExtractedText() {
        checksumMD5 = new ChecksumMD5();
        reader = new ReaderText();
        convertDoc = new ConvertDoc();
    }

    /**
     * Create a document
     * @param parameter - the parameter to execute the conversion using tesseract
     */
    public void createDocument(final ExtractTextParameter parameter) throws Exception {
        DocType type = parameter.getType();
        String outputFile = parameter.getOutputFile() + parameter.getFileName();
        String result = reader.readFile(outputFile + ExtractTextModel.TXT_EXTENSION);
        if (!type.equals(DocType.TXT)) {
            eraseDocument(type, outputFile);
        }
        if (type.equals(DocType.PDF)) {
            convertDoc.createDocumentPdf(outputFile, result);
        }
        if (type.equals(DocType.DOCX)) {
            convertDoc.createDocumentWord(outputFile, result);
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
