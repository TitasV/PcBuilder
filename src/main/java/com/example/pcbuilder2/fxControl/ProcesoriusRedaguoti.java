package com.example.pcbuilder2.fxControl;

import com.example.pcbuilder2.model.ProcesoriausID;
import com.example.pcbuilder2.utils.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
//decimal lygiavimas
//Filtra susiaurinti
//Kompoiterio lentele
public class ProcesoriusRedaguoti implements Initializable {
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

    ProcesoriausID data = ProcesoriausID.getInstance();
    private Connection connection;
    private PreparedStatement statement;
    @FXML private javafx.scene.control.Button closeButton;
    @FXML
    private Label ausintuvas;
    @FXML
    private Label plokste;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Procesorius WHERE Procesoriaus_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                gamintojasText.setText(rs.getString(2));
                modelisText.setText(rs.getString(3));
                jungtisText.setText(rs.getString(4));
                daznisText.setText(rs.getString(5));
                kainaText.setText(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        papildomiDuomenys();
    }
    @FXML
    void atnaujinti(ActionEvent event) throws SQLException {
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Procesorius SET Gamintojas = ?, Modelis = ?, Procesoriaus_jungtis = ?, Procesoriaus_daznis = ?, Kaina = ? WHERE Procesoriaus_ID = ?");
            statement.setString(1, gamintojasText.getText());
            statement.setString(2, modelisText.getText());
            statement.setString(3, jungtisText.getText());
            statement.setString(4, daznisText.getText());
            statement.setString(5, kainaText.getText());
            statement.setInt(6, data.getID());
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
    private void papildomiDuomenys(){
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT Procesoriaus_ausintuvas.Gamintojas, Procesoriaus_ausintuvas.Modelis FROM Procesorius, Procesoriaus_ausintuvas\n" +
                    "WHERE Procesoriaus_ausintuvas.Procesoriaus_lizdo_tipas = Procesorius.Procesoriaus_jungtis\n" +
                    "AND Procesorius.Procesoriaus_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            ausintuvas.setText("Tinkami aušintuvai: ");
            while(rs.next()) {
                ausintuvas.setText(ausintuvas.getText() + rs.getString(1) + " " + rs.getString(2) + ", ");
            }
        } catch (SQLException e) {
            ausintuvas.setText("Neturime tinkamu aušintuvu");
        }
        DbOperations.disconnectFromDb(connection, statement);
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT Pagrindine_plokste.Gamintojas, Pagrindine_plokste.Modelis FROM Procesorius, Pagrindine_plokste\n" +
                    "WHERE Pagrindine_plokste.Procesoriaus_lizdo_tipas = Procesorius.Procesoriaus_jungtis\n" +
                    "AND Procesorius.Procesoriaus_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            plokste.setText("Tinkamos pagrindinės plokštės: ");
            while(rs.next()) {
                plokste.setText(plokste.getText() + rs.getString(1) + " " + rs.getString(2) + ", ");
            }
        } catch (SQLException e) {
            plokste.setText("Neturime tinkamu pagrindiniu plokščių");
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
}
