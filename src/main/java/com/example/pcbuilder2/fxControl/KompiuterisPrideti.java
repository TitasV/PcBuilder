package com.example.pcbuilder2.fxControl;

import com.example.pcbuilder2.utils.DbOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class KompiuterisPrideti implements Initializable {
    @FXML
    private ChoiceBox<String> procesorius;
    @FXML
    private ChoiceBox<String> ausintuvas;
    @FXML
    private ChoiceBox<String> plokste;
    @FXML
    private ChoiceBox<String> atmintis;
    @FXML
    private ChoiceBox<String> ventiliatorius;
    @FXML
    private ChoiceBox<String> diskas;
    @FXML
    private ChoiceBox<String> vaizdoPlokste;
    @FXML
    private ChoiceBox<String> blokas;
    @FXML
    private ChoiceBox<String> korpusas;
    @FXML
    private ChoiceBox<Integer> atmintiesKiekis;
    @FXML
    private ChoiceBox<Integer> ventiliatoriausKiekis;
    @FXML
    private ChoiceBox<Integer> diskoKiekis;
    @FXML
    private ChoiceBox<Integer> vaizdoPlokstesKiekis;

    @FXML
    private ChoiceBox<String> klientas;
    @FXML
    private ChoiceBox<String> darbuotojas;
    @FXML
    private TextField adresas;
    @FXML
    private ChoiceBox<String> apmokejimas;
    @FXML
    private Label ausintuvoPranesimas;
    @FXML
    private Label plokstesPranesimas;
    private Connection connection;
    private PreparedStatement statement;
    @FXML private javafx.scene.control.Button closeButton;

    String procesoriusChoice = "";
    ArrayList<Integer> procesoriausID = new ArrayList<>();
    String ausintuvasChoice = "";
    ArrayList<Integer> ausintuvoID = new ArrayList<>();
    String ploksteChoice = "";
    ArrayList<Integer> plokstesID = new ArrayList<>();
    String atmintisChoice = "";
    ArrayList<Integer> atmintiesID = new ArrayList<>();
    String ventiliatoriusChoice = "";
    ArrayList<Integer> ventiliatoriausID = new ArrayList<>();
    String diskasChoice = "";
    ArrayList<Integer> diskoID = new ArrayList<>();
    String vaizdoPloksteChoice = "";
    ArrayList<Integer> vaizdoPlokstesID = new ArrayList<>();
    String blokasChoice = "";
    ArrayList<Integer> blokoID = new ArrayList<>();
    String korpusasChoice = "";
    ArrayList<Integer> korpusoID = new ArrayList<>();
    String klientasChoice = "";
    ArrayList<Integer> klientoID = new ArrayList<>();
    String darbuotojasChoice = "";
    ArrayList<Integer> darbuotojoID = new ArrayList<>();
    ArrayList<String> procesoriausJungtis = new ArrayList<>();
    ArrayList<String> ausintuvoJungtis = new ArrayList<>();
    ArrayList<String> plokstesJungtis = new ArrayList<>();
    ArrayList<String> plokstesDydis = new ArrayList<>();
    public int kompiuterioID;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
        ausintuvas.setOnMouseEntered(event -> {
            ausintuvas.getItems().clear();
            ausintuvoID.clear();
            ausintuvoJungtis.clear();
            try {
                connection = DbOperations.connectToDb();
                statement = connection.prepareStatement("SELECT Procesoriaus_ausintuvas.Ausintuvo_ID, Procesoriaus_ausintuvas.Gamintojas, Procesoriaus_ausintuvas.Modelis, Procesoriaus_ausintuvas.Ausinimo_budas, Procesoriaus_ausintuvas.Procesoriaus_lizdo_tipas, Procesoriaus_ausintuvas.Kaina \n" +
                        "FROM Procesoriaus_ausintuvas, Procesorius \n" +
                        "WHERE Procesoriaus_ausintuvas.Procesoriaus_lizdo_tipas = ?\n" +
                        "AND Procesorius.Procesoriaus_ID = ?");
                statement.setString(1, procesoriausJungtis.get(procesorius.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, procesoriausID.get(procesorius.getSelectionModel().getSelectedIndex()));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    ausintuvoID.add(rs.getInt(1));
                    ausintuvasChoice = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDouble(6) + " Eur";
                    ausintuvas.getItems().add(ausintuvasChoice);
                    ausintuvoJungtis.add(rs.getString(5));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            DbOperations.disconnectFromDb(connection, statement);
        });
        plokste.setOnMouseEntered(event -> {
            plokste.getItems().clear();
            plokstesID.clear();
            plokstesDydis.clear();
            try {
                connection = DbOperations.connectToDb();
                statement = connection.prepareStatement("select Pagrindine_plokste.Plokstes_ID, Pagrindine_plokste.Gamintojas, Pagrindine_plokste.Procesoriaus_lizdo_tipas, Pagrindine_plokste.Lustu_rinkinys, Pagrindine_plokste.Dydis, Pagrindine_plokste.Kaina, Pagrindine_plokste.Modelis\n" +
                        "from Pagrindine_plokste, Procesorius\n" +
                        "where Pagrindine_plokste.Procesoriaus_lizdo_tipas = ?\n" +
                        "and Procesorius.Procesoriaus_ID = ?");
                statement.setString(1, procesoriausJungtis.get(procesorius.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, procesoriausID.get(procesorius.getSelectionModel().getSelectedIndex()));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    plokstesID.add(rs.getInt(1));
                    ploksteChoice = rs.getString(2) + " " + rs.getString(7) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDouble(6) + " Eur";
                    plokste.getItems().add(ploksteChoice);
                    plokstesJungtis.add(rs.getString(3));
                    plokstesDydis.add(rs.getString(5));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            DbOperations.disconnectFromDb(connection, statement);
        });
        korpusas.setOnMouseEntered(event -> {
            korpusas.getItems().clear();
            korpusoID.clear();
            try {
                connection = DbOperations.connectToDb();
                if (plokstesDydis.get(plokste.getSelectionModel().getSelectedIndex()).compareTo("ATX") == 0){
                    statement = connection.prepareStatement("select * from Korpusas where Korpusas.Dydis = ' ATX'");
                } else {
                    statement = connection.prepareStatement("SELECT * FROM Korpusas");
                }
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    korpusoID.add(rs.getInt(1));
                    korpusasChoice = rs.getString(2) + " " + rs.getString(5) + " " + rs.getString(3) + " " + rs.getDouble(4) + " Eur";
                    korpusas.getItems().add(korpusasChoice);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            DbOperations.disconnectFromDb(connection, statement);
        });
    }
    public void refresh(){
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Procesorius");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                procesoriausID.add(rs.getInt(1));
                procesoriusChoice = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " MHz " + rs.getDouble(6) + " Eur";
                procesorius.getItems().add(procesoriusChoice);
                procesoriausJungtis.add(rs.getString(4));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Operatyvioji_atmintis");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                atmintiesID.add(rs.getInt(1));
                atmintisChoice = rs.getString(2) + " " + rs.getString(7) + " " + rs.getString(3) + " GB " + rs.getString(4) + " " + rs.getString(5) + " MHz " + rs.getDouble(6) + " Eur";
                atmintis.getItems().add(atmintisChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Ventiliatorius");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ventiliatoriausID.add(rs.getInt(1));
                ventiliatoriusChoice = rs.getString(2) + " " + rs.getString(5) + " " + rs.getString(4) + " mm " + rs.getDouble(3) + " Eur";
                ventiliatorius.getItems().add(ventiliatoriusChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Kietasis_diskas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                diskoID.add(rs.getInt(1));
                diskasChoice = rs.getString(2) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(3) + " " + rs.getString(4) + " MB/s " + rs.getString(5) + " GB " + rs.getDouble(6) + " Eur";
                diskas.getItems().add(diskasChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Vaizdo_plokste");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                vaizdoPlokstesID.add(rs.getInt(1));
                vaizdoPloksteChoice = rs.getString(2) + " " + rs.getString(7) + " " + rs.getString(3) + " MB " + rs.getString(4) + " MHz " + rs.getString(5) + " MHz " + rs.getDouble(6) + " Eur";
                vaizdoPlokste.getItems().add(vaizdoPloksteChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Maitinimo_blokas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                blokoID.add(rs.getInt(1));
                blokasChoice = rs.getString(2) + " " + rs.getString(5) + " " + rs.getString(3) + " W " + rs.getDouble(4) + " Eur";
                blokas.getItems().add(blokasChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        Integer[] pasirinkimas = {1, 2, 3, 4};
        atmintiesKiekis.getItems().addAll(pasirinkimas);
        ventiliatoriausKiekis.getItems().addAll(pasirinkimas);
        pasirinkimas = new Integer[]{1, 2};
        diskoKiekis.getItems().addAll(pasirinkimas);
        vaizdoPlokstesKiekis.getItems().addAll(pasirinkimas);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Klientas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                klientoID.add(rs.getInt(1));
                klientasChoice = rs.getString(4);
                klientas.getItems().add(klientasChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Darbuotojas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                darbuotojoID.add(rs.getInt(1));
                darbuotojasChoice = rs.getString(2);
                darbuotojas.getItems().add(darbuotojasChoice);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        String[] apmokejimas = {"Kortele", "Grynais"};
        this.apmokejimas.getItems().addAll(apmokejimas);
    }
    @FXML
    public void prideti(ActionEvent event) throws SQLException {
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("insert into Uzsakymas (Kliento_ID, Darbuotojo_ID, Kli_Kliento_ID, Prisatymo_adresas, Apmokejimo_budas, Sumoketa, Pristatyta) \n" +
                    "values (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, klientoID.get(klientas.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, darbuotojoID.get(darbuotojas.getSelectionModel().getSelectedIndex()));
            statement.setInt(3, klientoID.get(klientas.getSelectionModel().getSelectedIndex()));
            statement.setString(4, adresas.getText());
            statement.setString(5, apmokejimas.getSelectionModel().getSelectedItem());
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.executeUpdate();
        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        int uzsakymoID = 1;
        connection = DbOperations.connectToDb();
        try{
            statement = connection.prepareStatement("SELECT * FROM Uzsakymas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                uzsakymoID = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("insert into Kompiuteris (Uzsakymo_ID, Procesoriaus_ID, Bloko_ID, Darbuotojo_ID, Korpuso_ID, Ausintuvo_ID, Plokstes_ID, Procesoriaus_Serijinis_Nr, Procesoriaus_ausintuvo_Serijinis_Nr, Plokstes_Serijinis_Nr, Korpuso_Serijinis_Nr, Bloko_Serijinis_Nr) \n" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, uzsakymoID);
            statement.setInt(2, procesoriausID.get(procesorius.getSelectionModel().getSelectedIndex()));
            statement.setInt(3, blokoID.get(blokas.getSelectionModel().getSelectedIndex()));
            statement.setInt(4, darbuotojoID.get(darbuotojas.getSelectionModel().getSelectedIndex()));
            statement.setInt(5, korpusoID.get(korpusas.getSelectionModel().getSelectedIndex()));
            statement.setInt(6, ausintuvoID.get(ausintuvas.getSelectionModel().getSelectedIndex()));
            statement.setInt(7, plokstesID.get(plokste.getSelectionModel().getSelectedIndex()));
            statement.setString(8, randomUUID(8, 9, ' '));
            statement.setString(9, randomUUID(8, 9, ' '));
            statement.setString(10, randomUUID(8, 9, ' '));
            statement.setString(11, randomUUID(8, 9, ' '));
            statement.setString(12, randomUUID(8, 9, ' '));
            statement.executeUpdate();
        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try{
            statement = connection.prepareStatement("SELECT * FROM Kompiuteris");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kompiuterioID = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        for (int i=1; i<=atmintiesKiekis.getSelectionModel().getSelectedItem(); i++) {
            connection = DbOperations.connectToDb();
            try {
                statement = connection.prepareStatement("insert into Sudarymas_3 (Atminties_ID, Kompiuterio_ID, Atminties_Serijinis_Nr) \n" +
                        "values (?, ?, ?)");
                statement.setInt(1, atmintiesID.get(atmintis.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, kompiuterioID);
                statement.setString(3, randomUUID(8, 9, ' '));
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Klaida su Sudarymas_3");
            }
            DbOperations.disconnectFromDb(connection, statement);
        }
        for (int i=1; i<=ventiliatoriausKiekis.getSelectionModel().getSelectedItem(); i++) {
            connection = DbOperations.connectToDb();
            try {
                statement = connection.prepareStatement("insert into Sudarymas_4 (Ventiliatoriaus_ID, Kompiuterio_ID, Ventiliatoriaus_Serijinis_Nr) \n" +
                        "values (?, ?, ?)");
                statement.setInt(1, ventiliatoriausID.get(ventiliatorius.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, kompiuterioID);
                statement.setString(3, randomUUID(8, 9, ' '));
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Klaida su Sudarymas_4");
            }
            DbOperations.disconnectFromDb(connection, statement);
        }
        for (int i=1; i<=diskoKiekis.getSelectionModel().getSelectedItem(); i++) {
            connection = DbOperations.connectToDb();
            try {
                statement = connection.prepareStatement("insert into Sudarymas_5 (Disko_ID, Kompiuterio_ID, Disko_Serijinis_Nr) \n" +
                        "values (?, ?, ?)");
                statement.setInt(1, diskoID.get(diskas.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, kompiuterioID);
                statement.setString(3, randomUUID(8, 9, ' '));
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Klaida su Sudarymas_5");
            }
            DbOperations.disconnectFromDb(connection, statement);
        }
        for (int i=1; i<=vaizdoPlokstesKiekis.getSelectionModel().getSelectedItem(); i++) {
            connection = DbOperations.connectToDb();
            try {
                statement = connection.prepareStatement("insert into Sudarymas_6 (Vaizdo_plokstes_ID, Kompiuterio_ID, Vaizdo_plokstes_Serijinis_Nr) \n" +
                        "values (?, ?, ?)");
                statement.setInt(1, vaizdoPlokstesID.get(vaizdoPlokste.getSelectionModel().getSelectedIndex()));
                statement.setInt(2, kompiuterioID);
                statement.setString(3, randomUUID(8, 9, ' '));
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Klaida su Sudarymas_6");
            }
            DbOperations.disconnectFromDb(connection, statement);
        }
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    private void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
    static final private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    final private Random rng = new SecureRandom();

    char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    String randomUUID(int length, int spacing, char spacerChar){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(length > 0){
            if(spacer == spacing){
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
