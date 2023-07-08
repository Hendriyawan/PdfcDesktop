/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.pdfc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.hendri.pdfc.controller.PdfController;
import com.hendri.pdfc.controller.PdfListener;
import com.hendri.pdfc.model.Pdf;
import com.hendri.pdfc.util.Utils;

/**
 *
 * @author admin
 */
public class MainMenuFrame extends JFrame implements PdfListener {
    private JPanel contentPanel;

    int width = Utils.getScreenSize().width / 2;
    int height = Utils.getScreenSize().height / 2;
    private PdfController controller;


    public MainMenuFrame() {

        ///PDF CONTROLLER
        controller = new PdfController();
        controller.setOnPdfListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        Toolbar toolbar = new Toolbar();
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(contentPanel);
        toolbar.setOnMenuClicked(position -> {
            switch (position) {
                case 1:
                    showMergePdf();
                    break;
                case 2:
                    showSplitPdf();
                    break;
                case 3:
                    showCompressPdf();
                    break;
                default:
                    showCompressPdf();
                    break;

            }
        });

    }

    /**
     * show merge pdf content
     */
    private void showMergePdf() {
        contentPanel.removeAll();
        contentPanel.setLayout(null);
        JLabel label = new JLabel("Feature not available yet !");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        int labelWidth = width / 2;
        int labelHeight = 60;
        int labelX = (width - labelWidth) / 2;
        int labelY = (height - labelHeight - 50) / 2;
        label.setBounds(labelX, labelY, labelWidth, labelHeight);

        /// ADD COMPONENT
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.revalidate();
        contentPanel.repaint();

    }

    /**
     * show split pdf content
     */
    private void showSplitPdf() {
        contentPanel.removeAll();
        contentPanel.setLayout(null);
        JLabel label = new JLabel("Feature not available yet !");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        int labelWidth = width / 2;
        int labelHeight = 60;
        int labelX = (width - labelWidth) / 2;
        int labelY = (height - labelHeight - 50) / 2;
        label.setBounds(labelX, labelY, labelWidth, labelHeight);

        /// ADD COMPONENT
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * show compress pdf content
     */
    private void showCompressPdf() {
        contentPanel.removeAll();
        contentPanel.setLayout(null);
        JLabel label = new JLabel("Compress PDF File");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        int labelWidth = width / 2 / 2;
        int labelHeight = 60;
        int labelX = (width - labelWidth) / 2;
        int labelY = (height - labelHeight - 100) / 2;
        label.setBounds(labelX, labelY, labelWidth, labelHeight);
        RoundButton buttonSelectPdf = new RoundButton("Select PDF files");
        buttonSelectPdf.setBackground(Color.red);
        buttonSelectPdf.setForeground(Color.white);

        int buttonWidth = width / 2 / 2; // Lebar button diatur menjadi setengah dari setengah lebar container
        int buttonHeight = 60;
        int buttonX = (width - buttonWidth) / 2; // Posisi horizontal button diatur agar berada di tengah container
        int buttonY = (height - buttonHeight) / 2; // Posisi vertikal button diatur agar berada di tengah container
        buttonSelectPdf.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        /// ACTION LISTENER BUTTON SELECT PDF
        buttonSelectPdf.addActionListener(e -> {
            controller.pick(this);
        });

        /// PROGRSS BAR
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(50);
        progressBar.setMaximum(100);
        progressBar.setBackground(Color.red);
        progressBar.setIndeterminate(true);

        /// ADD COMPONENT
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.add(buttonSelectPdf);
        contentPanel.add(progressBar, BorderLayout.SOUTH);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    public void onStartCompress() {

    }

    @Override
    public void onStopCompress() {

    }

    @Override
    public void onCompressed(Pdf pdf) {
        JOptionPane.showMessageDialog(this, pdf.getFileName() + "\nOriginal Size : "+pdf.getOldSize()+"\nNew Size : "+pdf.getSize(), "Compress Success !", JOptionPane.CLOSED_OPTION);
    }

    @Override
    public void onError(String message) {
        JOptionPane.showMessageDialog(this, message, "Process Failed !", JOptionPane.ERROR_MESSAGE);
    }

}
