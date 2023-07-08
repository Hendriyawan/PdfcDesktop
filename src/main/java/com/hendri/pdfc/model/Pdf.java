/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.pdfc.model;

/**
 *
 * @author admin
 */
public class Pdf {
    private String fileName;
    private String path;
    private String size;
    private String oldSize;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setOldSize(String oldSize) {
        this.oldSize = oldSize;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public String getSize() {
        return size;
    }

    public String getOldSize(){
        return oldSize;
    }
}
