package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.demo.ConnectionEstablish.con;

public class ModeratorController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private Stage stage;

    @FXML
    public void clickLogout(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent helloScreen = fxmlLoader.load();
        HelloController controller = fxmlLoader.getController();
        Scene scene = new Scene(helloScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clickAddMember(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddMember.fxml"));
        Parent moderatorScreen = fxmlLoader.load();
        AddMemberController controller = fxmlLoader.getController();
        Scene scene = new Scene(moderatorScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void removeMember(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeleteMember.fxml"));
        Parent moderatorScreen = fxmlLoader.load();
        com.example.demo.RemoveMemberController controller = fxmlLoader.getController();
        Scene scene = new Scene(moderatorScreen);
        stage.setScene(scene);
        stage.show();
    }


    private String username;

    public void sendData(String username) {
        this.username = username;
    }

    @FXML
    public void clickReset(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        ConnectionEstablish.connect();
        Statement statement = con.createStatement();
        String q = "update login set VcationDays= 30";
        statement.executeUpdate(q);
        AlertBox.display("Done", "Reset complete!");
    }
}
