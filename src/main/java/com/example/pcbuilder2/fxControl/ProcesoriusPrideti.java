package com.example.pcbuilder2.fxControl;

import com.example.pcbuilder2.utils.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProcesoriusPrideti implements Initializable {
    @FXML
    private TextField gamintojasText;
    @FXML
    private TextField modelisText;
    @FXML
    private TextField jungtisText;
    @FXML
    private TextField daznisText;
    @FXML
    private TextField kainaText;

    private Connection connection;
    private PreparedStatement statement;
    @FXML private javafx.scene.control.Button closeButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void prideti(ActionEvent event) throws SQLException {
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("insert into Procesorius (Gamintojas, Modelis, Procesoriaus_jungtis, Procesoriaus_daznis, Kaina) \n" +
                    "values (?, ?, ?, ?, ?)");
            statement.setString(1, gamintojasText.getText());
            statement.setString(2, modelisText.getText());
            statement.setString(3, jungtisText.getText());
            statement.setInt(4, Integer.parseInt(daznisText.getText()));
            statement.setDouble(5, Double.parseDouble(kainaText.getText()));
            statement.executeUpdate();
        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    private void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
