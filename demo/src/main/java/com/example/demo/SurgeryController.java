package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import static com.example.demo.ConnectionEstablish.con;

public class SurgeryController implements Initializable {
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void clickLogout(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Doctor.fxml"));
        Parent doctorScreen = fxmlLoader.load();
        DoctorController controller = fxmlLoader.getController();
        Scene scene = new Scene(doctorScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private DatePicker dp;
    @FXML
    private TextField duration;
    @FXML
    private TextField price;
    @FXML
    private TextField room;
    @FXML
    private TextField time;

    @FXML
    public void schedule(ActionEvent event) {
        String q3 = "select name from login where email='" + username + "'";
        String name = "";
        if (dp.getValue() == null || duration.getText() == "" || price.getText() == "" || room.getText() == "" || time.getText() == "") {
            AlertBox.display("Error!", "Please fill in all fields");
            return;
        }
        try {
            Time st = Time.valueOf(time.getText());
            LocalDate date = (dp).getValue();
            Time Duration = Time.valueOf(duration.getText());
            int Price = Integer.parseInt(price.getText());
            int Room = Integer.parseInt(room.getText());
            ConnectionEstablish.connect();
            Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(q3);
            while (set.next()) {
                name = set.getString("name");
            }
            String q2 = "UPDATE jdbctest.surgerysalary SET total = total + " + Price + ", numberofsurgeries = numberofsurgeries +1 WHERE doctor = '" + name + "' ;";
            String q = "INSERT INTO `jdbctest`.`surgery` (`starttime`, `duration`, `date`, `room`, `doctor`) VALUES ( '" + st + "',  '" + Duration + "', '" + date + "', '" + Room + "', '" + name + "');";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.executeUpdate();
            AlertBox.display("Successful!", "Booking Successful");
            PreparedStatement pstmt2 = con.prepareStatement(q2);
            pstmt2.executeUpdate();


        } catch (DateTimeParseException x) {
            AlertBox.display("Error", "Please enter a valid date!");
        } catch (SQLIntegrityConstraintViolationException e) {
            AlertBox.display("Error!", "Booking Unavailable, please choose a different room!");
        } catch (Exception e) {
            AlertBox.display("Error!", "Please enter fields in their correct format!");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


    }

    private String username;

    public void sendData2(String username) {
        this.username = username;
    }
}
