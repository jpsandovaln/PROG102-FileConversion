package org.fundacionjala.converter.model;
/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Jhordan Soto
 * @version 1.0
 */
public class ConvertDoc {
    /**
     * crate a document word
     * @param docName the name of the doc
     * @param content the content that will have the word
     */
    public String createDocumentWord(final String docName, final String content) {
        FileOutputStream out = null;
        String pathCreated = docName + ".docx";
        try {
            XWPFDocument doc = new XWPFDocument();
            out = new FileOutputStream(pathCreated);
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(content);
            doc.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathCreated;
    }
    /**
     * Create a document pdf
     * @param docName the path and the name that it will have
     * @param content the content of the document
     */
    public String createDocumentPdf(final String docName, final String content) {
        Document doc = null;
        String pathCreated = docName + ".pdf";
        try {
            doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(pathCreated));
            doc.open();
            Paragraph paragraph = new Paragraph(content);
            doc.add(paragraph);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
        return pathCreated;
    }
}
