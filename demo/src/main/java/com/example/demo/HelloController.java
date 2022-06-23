package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.InterruptedIOException;
import java.sql.*;


import java.io.IOException;

import static com.example.demo.ConnectionEstablish.con;

public class HelloController {
    private Stage stage;

    @FXML
    public void clickForgotPassword(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Forgot_Password.fxml"));
        Parent forgetPasswordScreen = fxmlLoader.load();
        ForgotPasswordController controller = fxmlLoader.getController();
        Scene scene = new Scene(forgetPasswordScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passText;
    @FXML
    private Label loginFailed1;
    @FXML
    private Label loginFailed2;
    @FXML
    private Label loginFailed3;

    public TextField getEmailText() {
        return emailText;
    }


    @FXML
    public void clickLogin(ActionEvent event) throws IOException, SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "Nashwa123412");

        String username = emailText.getText();
        String password = passText.getText();


        try {
            if (username.equals("") || password.equals("")) {
                throw new EmptyLoginException(loginFailed1, loginFailed2);
            }
            Statement statement = con.createStatement();
            String q = "select * from login where email='" + username + "'" + " and password = '" + password + "'";
            ResultSet set = statement.executeQuery(q);

            if (set.next()) {
                String name = set.getString("name");
                if (username.contains("Dr") || username.contains("dr")) {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Doctor.fxml"));
                    Parent doctorScreen = fxmlLoader.load();
                    DoctorController controller = fxmlLoader.getController();
                    controller.sendData(username, set.getString("speciality"));
                    Scene scene = new Scene(doctorScreen);
                    stage.setScene(scene);
                    stage.show();
                    con.close();
                } else if (username.contains("Nurse") || username.contains("nurse")) {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Nurse.fxml"));
                    Parent nurseScreen = fxmlLoader.load();
                    NurseController controller = fxmlLoader.getController();
                    controller.sendData(name);
                    Scene scene = new Scene(nurseScreen);
                    stage.setScene(scene);
                    stage.show();
                    con.close();

                } else if (username.contains("Tech") || username.contains("tech")) {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tech.fxml"));
                    Parent techScreen = fxmlLoader.load();
                    TechController controller = fxmlLoader.getController();
                    controller.sendData(name);
                    Scene scene = new Scene(techScreen);
                    stage.setScene(scene);
                    stage.show();
                    con.close();

                } else if (username.contains("Moderator") || username.contains("moderator")) {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Moderator.fxml"));
                    Parent moderatorScreen = fxmlLoader.load();
                    ModeratorController controller = fxmlLoader.getController();
                    controller.sendData(username);
                    Scene scene = new Scene(moderatorScreen);
                    stage.setScene(scene);
                    stage.show();
                    con.close();
                }
            } else {
                throw new InvalidLoginException(loginFailed3);
            }

        } catch (Exception e) {
//            con.close();
        }

    }
}

