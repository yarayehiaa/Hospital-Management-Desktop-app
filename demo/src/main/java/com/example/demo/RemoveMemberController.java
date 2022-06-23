package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.demo.ConnectionEstablish.con;


public class RemoveMemberController implements Initializable {
    @FXML
    private ComboBox removeCombo;
    private ObservableList data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ConnectionEstablish.connect();
            Statement statement = con.createStatement();
            data = FXCollections.observableArrayList();
            String q = "select * from login where not email  = 'moderator@hospital.com'";
            ResultSet set = statement.executeQuery(q);
            while (set.next()) {
                data.add(set.getString("name"));
            }
            removeCombo.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Stage stage;

    @FXML
    public void clickBack(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Moderator.fxml"));
        Parent removeMemberScreen = fxmlLoader.load();
        ModeratorController controller = fxmlLoader.getController();
        Scene scene = new Scene(removeMemberScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clickRemove(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        ConnectionEstablish.connect();
        Statement statement = con.createStatement();
        Statement statement2 = con.createStatement();
        String q = "DELETE from login where Name = '" + (String) removeCombo.getValue() + "' ";
        String q2 = "DELETE from surgerysalary where Doctor = '" + (String) removeCombo.getValue() + "' ";
        statement.executeUpdate(q);
        statement.executeUpdate(q2);
        AlertBox.display("Done", (String) removeCombo.getValue() + " Removed Successfully!");

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Moderator.fxml"));
        Parent removeMemberScreen = fxmlLoader.load();
        ModeratorController controller = fxmlLoader.getController();
        Scene scene = new Scene(removeMemberScreen);
        stage.setScene(scene);
        stage.show();
    }

}
