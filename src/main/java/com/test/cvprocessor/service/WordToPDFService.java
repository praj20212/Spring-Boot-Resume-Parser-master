package com.test.cvprocessor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

/**
 * Service to convert word to pdf
 * 
 */
@Service
public class WordToPDFService {

    /**
     * Convert Word input stream to pdf file using Apache POI APIs
     * 
     * @param fileInputStream
     * @param pdfFilePath
     * @return pdf output stream
     */
    public OutputStream convert(InputStream fileInputStream, String pdfFilePath) {
        OutputStream out = null;
        try {
            XWPFDocument document = new XWPFDocument(fileInputStream);
            PdfOptions options = PdfOptions.create();
            out = new FileOutputStream(new File(pdfFilePath));
            PdfConverter.getInstance().convert(document, out, options);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return out;
    }
}