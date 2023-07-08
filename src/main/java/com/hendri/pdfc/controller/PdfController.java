package com.hendri.pdfc.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.hendri.pdfc.model.Pdf;
import com.hendri.pdfc.util.Utils;

import ch.qos.logback.classic.pattern.Util;
public class PdfController {
    private PdfImpl pdfImpl;
    private PdfListener listener;


    public PdfController() {
        this.pdfImpl = new PdfImpl();
    }
    public void setOnPdfListener(PdfListener listener) {
        this.listener = listener;
    }

    public void selectPdfFile(File file) {
        // Mengambil data dari file PDF
        Pdf pdf = new Pdf();
        pdf.setFileName(file.getAbsolutePath());
        pdf.setPath(file.getAbsolutePath());
        pdf.setSize(Utils.getFileSize(pdf.getFileName()));
        pdf.setOldSize(Utils.getFileSize(pdf.getFileName()));
        // Menghubungkan Pdf dengan PdfImpl
        pdfImpl.setPdf(pdf);
        System.out.println(pdf.getSize());
    }
    ///COMPRESS PDF
    public void compressPdf() {
        Pdf pdf = pdfImpl.getPdf();
        if (pdf != null) {
            // Memanggil metode kompresi pada PdfImpl
            try {
                listener.onStartCompress();
                pdfImpl.compress(new File(pdf.getPath()));
                listener.onStopCompress();
                listener.onCompressed(pdfImpl.getPdf());
            } catch (Exception e) {
                listener.onError(e.getMessage());
            }
        }
    }


    public void pick(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectPdfFile(selectedFile);
            compressPdf();
        }
    }
}
