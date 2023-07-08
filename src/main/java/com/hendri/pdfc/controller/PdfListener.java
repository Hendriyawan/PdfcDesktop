package com.hendri.pdfc.controller;

import com.hendri.pdfc.model.Pdf;

public interface PdfListener {


    void onStartCompress();

    void onStopCompress();

    void onCompressed(Pdf pdf);

    void onError(String message);
}
