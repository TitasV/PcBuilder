package com.example.pcbuilder2.fxControl;
import com.example.pcbuilder2.model.ProcesoriausID;
import com.example.pcbuilder2.model.TableParams;
import com.example.pcbuilder2.utils.DbOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Procesorius implements Initializable {
    ProcesoriausID data = ProcesoriausID.getInstance();

    @FXML
    private TableView<TableParams> procesoriusTable;
    @FXML
    private TableColumn<TableParams, String> gamintojasCol;
    @FXML
    private TableColumn<TableParams, String> modelisCol;
    @FXML
    private TableColumn<TableParams, String> procesoriausJungtisCol;
    @FXML
    private TableColumn<TableParams, Integer> procesoriausDaznisCol;
    @FXML
    private TableColumn<TableParams, Double> kainaCol;

    private ArrayList<Integer> procesoriusID = new ArrayList<Integer>();

    private Connection connection;
    private PreparedStatement statement;

    @FXML
    private TextField gamintojasFilter;
    @FXML
    private TextField modelisFilter;
    @FXML
    private TextField jungtisFilter;
    @FXML
    private TextField daznisFilter;
    @FXML
    private TextField kainaNuoFilter;
    @FXML
    private TextField kainaIkiFilter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamintojasCol.setCellValueFactory(new PropertyValueFactory<TableParams, String>("gamintojas"));
        modelisCol.setCellValueFactory(new PropertyValueFactory<TableParams, String>("modelis"));
        procesoriausJungtisCol.setCellValueFactory(new PropertyValueFactory<TableParams, String>("procesoriausJungtis"));
        procesoriausDaznisCol.setCellValueFactory(new PropertyValueFactory<TableParams, Integer>("procesoriausDaznis"));
        kainaCol.setCellValueFactory(new PropertyValueFactory<TableParams, Double>("kaina"));
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void refresh() throws SQLException {
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("SELECT * FROM Procesorius");
        ResultSet rs = statement.executeQuery();
        procesoriusID.clear();
        while (rs.next()){
            procesoriusID.add(rs.getInt(1));
            TableParams tableParams = new TableParams(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6));
            ObservableList<TableParams> observableList = procesoriusTable.getItems();
            observableList.add(tableParams);
            procesoriusTable.setItems(observableList);
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
    @FXML
    void istrinti(ActionEvent event) throws SQLException {
        int selectedID = procesoriusTable.getSelectionModel().getSelectedIndex();
        procesoriusTable.getItems().remove(selectedID);
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("DELETE FROM Procesorius WHERE Procesoriaus_ID=?");
        statement.setInt(1, selectedID);
        statement.executeUpdate();
        DbOperations.disconnectFromDb(connection, statement);
        procesoriusID.remove(selectedID);
    }
    @FXML
    void redaguoti(ActionEvent event){
        int selectedID=procesoriusTable.getSelectionModel().getSelectedIndex();
        data.setID(selectedID+1);
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("procesoriusRedaguoti.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Redaguoti");
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.NONE);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Nepavyko atidaryti lango");
        }
    }
    @FXML
    void filtruoti(ActionEvent event) throws SQLException {
        procesoriusTable.getItems().clear();
        String gamintojjasFilterDummy = gamintojasFilter.getText();
        String modelisFilterDummy = modelisFilter.getText();
        String jungtisFilterDummy = jungtisFilter.getText();
        String daznisFilterDummy = daznisFilter.getText();
        String kainaNuoFilterDummy = kainaNuoFilter.getText();
        String kainaIkiFilterDummy = kainaIkiFilter.getText();
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("SELECT * FROM Procesorius WHERE (Gamintojas  = ? OR ? IS NULL) " +
                "AND (Modelis = ? OR ? IS NULL) " +
                "AND (Procesoriaus_jungtis = ? OR ? IS NULL) " +
                "AND (Procesoriaus_daznis = ? OR ? IS NULL) " +
                "AND (Kaina >= ? OR ? IS NULL) " +
                "AND (Kaina <= ? OR ? IS NULL)");
        if (gamintojjasFilterDummy == ""){
            statement.setString(1, null);
            statement.setString(2, null);
        } else {
            statement.setString(1, gamintojjasFilterDummy);
            statement.setString(2, gamintojjasFilterDummy);
        }
        if (modelisFilterDummy == ""){
            statement.setString(3, null);
            statement.setString(4, null);
        } else {
            statement.setString(3, modelisFilterDummy);
            statement.setString(4, modelisFilterDummy);
        }
        if (jungtisFilterDummy == ""){
            statement.setString(5, null);
            statement.setString(6, null);
        } else {
            statement.setString(5, jungtisFilterDummy);
            statement.setString(6, jungtisFilterDummy);
        }
        if (daznisFilterDummy == ""){
            statement.setString(7, null);
            statement.setString(8, null);
        } else {
            statement.setString(7, daznisFilterDummy);
            statement.setString(8, daznisFilterDummy);
        }
        if (kainaNuoFilterDummy == ""){
            statement.setString(9, null);
            statement.setString(10, null);
        } else {
            statement.setString(9, kainaNuoFilterDummy);
            statement.setString(10, kainaNuoFilterDummy);
        }
        if (kainaIkiFilterDummy == ""){
            statement.setString(11, null);
            statement.setString(12, null);
        } else {
            statement.setString(11, kainaIkiFilterDummy);
            statement.setString(12, kainaIkiFilterDummy);
        }
        procesoriusID.clear();
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            procesoriusID.add(rs.getInt(1));
            TableParams tableParams = new TableParams(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6));
            ObservableList<TableParams> observableList = procesoriusTable.getItems();
            observableList.add(tableParams);
            procesoriusTable.setItems(observableList);
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
    @FXML
    void atnaujinti(ActionEvent event) throws SQLException {
        procesoriusTable.getItems().clear();
        refresh();
    }
    @FXML
    void prideti(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("procesoriusPrideti.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Prideti");
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.NONE);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Nepavyko atidaryti lango");
        }
    }
}
