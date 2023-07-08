/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.pdfc.controller;

import com.hendri.pdfc.model.Pdf;
import com.hendri.pdfc.util.Utils;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfPage;
// import com.itextpdf.kernel.pdf.PdfReader;
// import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.*;

/**
 *
 * @author admin
 */
public class PdfImpl implements PdfDao {
    private Pdf pdf;

    /**
     *
     * @param file
     */
    @Override
    public void compress(File file) throws Exception {
        if (pdf != null) {

            // Mendapatkan nama file tanpa ekstensi
            String fileName = pdf.getFileName().substring(0, pdf.getFileName().lastIndexOf("."));
            // Mendapatkan ekstensi file
            String fileExtension = pdf.getFileName().substring(pdf.getFileName().lastIndexOf("."));

            // Nama file baru dengan menambahkan "_compress" pada nama asli
            String newFileName = fileName + "_compress" + fileExtension;

            try {
                // // Create a PdfDocument instance from the input file
                // PdfDocument sourcePdf = new PdfDocument(new PdfReader(pdf.getFileName()));
                // // Create a compressed PdfWriter that writes to the output file
                // PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(newFileName));
                // pdfWriter.setCompressionLevel(9);
                // // Create a PdfDocument instance from the compressed PdfWriter
                // PdfDocument outputPdf = new PdfDocument(pdfWriter);

                // // Iterate over the pages in the input PdfDocument, copying each page to the
                // // output PdfDocument
                // int numPages = sourcePdf.getNumberOfPages();
                // for (int i = 1; i <= numPages; i++) {
                // PdfPage page = sourcePdf.getPage(i);
                // outputPdf.addPage(page.copyTo(outputPdf));
                // }

                // // Close the output PdfDocument to write the compressed PDF to disk
                // outputPdf.close();

                PdfReader reader = new PdfReader(new FileInputStream(pdf.getFileName()));
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(newFileName));
                stamper.getWriter().setCompressionLevel(9);

                reader.removeFields();
                reader.removeUnusedObjects();

                int total = reader.getNumberOfPages() + 1;
                for (int i = 1; i < total; i++) {
                    reader.setPageContent(i + 1, reader.getPageContent(i + 1));
                }
                try {
                    stamper.setFullCompression();
                    stamper.close();

                } catch (Exception e) {
                    System.out.println();

                }

                Pdf newPdf = new Pdf();
                newPdf.setFileName(newFileName);
                newPdf.setPath(new File(newFileName).getPath());
                newPdf.setSize(Utils.getFileSize(newFileName));
                newPdf.setOldSize(Utils.getFileSize(pdf.getFileName()));
                this.pdf = newPdf;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Pdf getPdf() {
        return pdf;
    }

    @Override
    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

}
