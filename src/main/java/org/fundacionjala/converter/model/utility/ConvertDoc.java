/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.model.utility;

import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.fundacionjala.converter.model.commons.exception.ConvertDocException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ConvertDoc {
    public static final String DOCX_EXTENSION = ".docx";
    public static final String PDF_EXTENSION = ".pdf";

    /**
     * Creates a document word
     * @param docName the name of the doc
     * @param content the content that will have the word
     */
    public String createDocumentWord(final String docName, final String content) throws ConvertDocException {
        FileOutputStream out = null;
        String pathCreated = docName + DOCX_EXTENSION;
        try {
            XWPFDocument doc = new XWPFDocument();
            out = new FileOutputStream(pathCreated);
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(content);
            doc.write(out);
        } catch (IOException e) {
            throw new ConvertDocException("Error while converting to word");
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                throw new ConvertDocException("Error while converting to word");
            }
        }
        return pathCreated;
    }

    /**
     * Creates a document pdf
     * @param docName the path and the name that it will have
     * @param content the content of the document
     */
    public String createDocumentPdf(final String docName, final String content) throws ConvertDocException {
        Document doc = null;
        String pathCreated = docName + PDF_EXTENSION;
        try {
            doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(pathCreated));
            doc.open();
            Paragraph paragraph = new Paragraph(content);
            doc.add(paragraph);
        } catch (Exception e) {
            throw new ConvertDocException("Error while converting to pdf");
        } finally {
            doc.close();
        }
        return pathCreated;
    }
}
