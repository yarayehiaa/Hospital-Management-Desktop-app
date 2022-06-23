package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmptyLoginException extends Exception {


    public EmptyLoginException(Label label1, Label label2) {
        label1.setText("*");
        label2.setText("*");
    }
}
