package com.hendri.pdfc.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class Utils {

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static String getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            long fileSizeBytes = file.length();
            if (fileSizeBytes >= 1048576) { // lebih dari 1 MB
                double fileSizeMB = (double) fileSizeBytes / 1048576;
                return String.format("%.2f MB", fileSizeMB);
            } else {
                double fileSizeKB = (double) fileSizeBytes / 1024;
                return String.format("%.2f KB", fileSizeKB);
            }
        } else {
            return null;
        }
    }
}
