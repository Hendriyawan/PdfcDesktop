/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.pdfc.view;

import com.hendri.pdfc.util.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author admin
 */
public class Toolbar extends JToolBar {
    private int selectedMenu = 3;
    private RoundButton mergePdfButton = new RoundButton("MERGE PDF");
    private RoundButton splitPdfButton = new RoundButton("SPLIT PDF");
    private RoundButton compressPdfButton = new RoundButton("COMPRESS PDF");
    JLabel logoLabel = new JLabel("<html><h2 style='font-size: 14px; font-style: bold; color:black;'>PDFC</h2></html>");

    private OnMenuClicked onMenuClicked;

    void setOnMenuClicked(OnMenuClicked onMenuClicked) {
        this.onMenuClicked = onMenuClicked;
    }
    public interface OnMenuClicked {
        void onClick(int position);
    }

    public Toolbar() {
        mergePdfButton.setMargin(new Insets(5, 5, 5, 5));
        mergePdfButton.setBackground(new Color(0xFFB0BEC5));
        mergePdfButton.setForeground(Color.RED);
        mergePdfButton.setActive(false);


        splitPdfButton.setMargin(new Insets(5, 5, 5, 5));
        splitPdfButton.setBackground(new Color(0xFFB0BEC5));
        splitPdfButton.setForeground(Color.RED);
        splitPdfButton.setActive(false);


        compressPdfButton.setMargin(new Insets(5, 5, 5, 5));
        compressPdfButton.setBackground(new Color(0xFFB0BEC5));
        compressPdfButton.setForeground(Color.RED);
        compressPdfButton.setActive(true);



        mergePdfButton.addActionListener(e -> {
            selectedMenu = 1;
            mergePdfButton.setActive(true);
            splitPdfButton.setActive(false);
            compressPdfButton.setActive(false);
            onMenuClicked.onClick(selectedMenu);

        });
        splitPdfButton.addActionListener(e -> {
            selectedMenu = 2;
            mergePdfButton.setActive(false);
            splitPdfButton.setActive(true);
            compressPdfButton.setActive(false);
            onMenuClicked.onClick(selectedMenu);

        });
        compressPdfButton.addActionListener(e -> {
            selectedMenu = 3;
            mergePdfButton.setActive(false);
            splitPdfButton.setActive(false);
            compressPdfButton.setActive(true);
            onMenuClicked.onClick(selectedMenu);

        });

        /// set height prefered size for Toolbar
        setPreferredSize(new Dimension(Utils.getScreenSize().width, 50));
        setBackground(new Color(0xFFB0BEC5));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(logoLabel);
        add(Box.createHorizontalGlue());
        add(mergePdfButton);
        add(Box.createHorizontalStrut(5));
        add(splitPdfButton);
        add(Box.createHorizontalStrut(5));
        add(compressPdfButton);
        add(Box.createHorizontalStrut(5));
    }
}
