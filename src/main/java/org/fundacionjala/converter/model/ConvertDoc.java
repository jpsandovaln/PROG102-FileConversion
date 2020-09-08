package org.fundacionjala.converter.model;

import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
public class ConvertDoc {
    /**
     * crate a document word
     * @param docName the name of the doc
     * @param content the content that will have the word
     */
    public void createDocumentWord(final String docName, final String content) {
        try {
            XWPFDocument doc = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(docName + ".docx");
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(content);
            doc.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a document pdf
     * @param docName the path and the name that it will have
     * @param content the content of the document
     */
    public void createDocumentPdf(final String docName, final String content) {
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(docName + ".pdf"));
            doc.open();
            Paragraph paragraph = new Paragraph(content);
            doc.add(paragraph);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
