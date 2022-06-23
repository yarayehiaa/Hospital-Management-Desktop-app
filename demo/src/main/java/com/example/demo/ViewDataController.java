package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.demo.ConnectionEstablish.con;

public class ViewDataController implements Initializable {
    @FXML
    private Label pName;
    @FXML
    private Label pAge;
    @FXML
    private Label pFirstTime;
    @FXML
    private Label pSymptoms;
    @FXML
    private Label pGender;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private Patients p;

    public void sendData(Patients p) throws SQLException {
        this.p = p;
        pName.setText(p.getName());
        pAge.setText(Integer.toString(p.getAge()));
        if (p.getFirstTime() == 1) {
            pFirstTime.setText("Yes");
        } else {
            pFirstTime.setText("No");
        }
        pSymptoms.setText(p.getSymptoms());
        pGender.setText(p.getGender());

    }
}
