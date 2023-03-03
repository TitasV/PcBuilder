package com.example.pcbuilder2.fxControl;

import com.example.pcbuilder2.model.KompiuterisID;
import com.example.pcbuilder2.utils.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class KompiuterisRedaguoti implements Initializable {
    KompiuterisID data = KompiuterisID.getInstance();
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
    private ChoiceBox<String> sumoketa;
    @FXML
    private ChoiceBox<String> pristatyta;

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
    private int pasirinktasProcesorius;
    private int pasirinktasAusintuvas;
    private int pasirinktasPlokste;
    private int pasirinktasAtmintis;
    private int pasirinktasVentiliatorius;
    private int pasirinktasDiskas;
    private int pasirinktasVaizdoPlokste;
    private int pasirinktasAtmintiesKiekis = 0;
    private int pasirinktasVentiliatoriausKiekis = 0;
    private int pasirinktasDiskoKiekis = 0;
    private int pasirinktasVaizdoPlokstesKiekis = 0;
    private int pasirinktasBLokas;
    private int pasirinktasKorpusas;
    private int pasirinktasUzsakymas;
    private int pasirinktasDarbuotojas;
    private int pasirinktasKlientas;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pasirinkimai();
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
                    if (ausintuvoID.get(ausintuvoID.size()-1) == pasirinktasAusintuvas){
                        ausintuvas.setValue(ausintuvasChoice);
                    }
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
                    if (plokstesID.get(plokstesID.size()-1) == pasirinktasPlokste){
                        plokste.setValue(ploksteChoice);
                    }
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
                    if (korpusoID.get(korpusoID.size()-1) == pasirinktasKorpusas){
                        korpusas.setValue(korpusasChoice);
                    }
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
                if (procesoriausID.get(procesoriausID.size()-1) == pasirinktasProcesorius){
                    procesorius.setValue(procesoriusChoice);
                }
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
                if (atmintiesID.get(atmintiesID.size()-1) == pasirinktasAtmintis){
                    atmintis.setValue(atmintisChoice);
                }
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
                if (ventiliatoriausID.get(ventiliatoriausID.size()-1) == pasirinktasVentiliatorius){
                    ventiliatorius.setValue(ventiliatoriusChoice);
                }
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
                if (diskoID.get(diskoID.size()-1) == pasirinktasDiskas){
                    diskas.setValue(diskasChoice);
                }
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
                if (vaizdoPlokstesID.get(vaizdoPlokstesID.size()-1) == pasirinktasVaizdoPlokste){
                    vaizdoPlokste.setValue(vaizdoPloksteChoice);
                }
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
                if (blokoID.get(blokoID.size()-1) == pasirinktasBLokas){
                    blokas.setValue(blokasChoice);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        Integer[] pasirinkimas = {1, 2, 3, 4};
        atmintiesKiekis.getItems().addAll(pasirinkimas);
        atmintiesKiekis.setValue(pasirinktasAtmintiesKiekis);
        ventiliatoriausKiekis.getItems().addAll(pasirinkimas);
        ventiliatoriausKiekis.setValue(pasirinktasVentiliatoriausKiekis);
        pasirinkimas = new Integer[]{1, 2};
        diskoKiekis.getItems().addAll(pasirinkimas);
        diskoKiekis.setValue(pasirinktasDiskoKiekis);
        vaizdoPlokstesKiekis.getItems().addAll(pasirinkimas);
        vaizdoPlokstesKiekis.setValue(pasirinktasVaizdoPlokstesKiekis);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT * FROM Klientas");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                klientoID.add(rs.getInt(1));
                klientasChoice = rs.getString(4);
                klientas.getItems().add(klientasChoice);
                if (klientoID.get(klientoID.size()-1) == pasirinktasKlientas){
                    klientas.setValue(klientasChoice);
                }
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
                if (darbuotojoID.get(darbuotojoID.size()-1) == pasirinktasDarbuotojas){
                    darbuotojas.setValue(darbuotojasChoice);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        String[] apmokejimas = {"Kortele", "Grynais"};
        this.apmokejimas.getItems().addAll(apmokejimas);
        String[] sumoketa = {"Taip", "Ne"};
        this.sumoketa.getItems().addAll(sumoketa);
        String[] pristatyta = {"Taip", "Ne"};
        this.pristatyta.getItems().addAll(pristatyta);
        try {
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("select Uzsakymas.Prisatymo_adresas, Uzsakymas.Apmokejimo_budas, Uzsakymas.Sumoketa, Uzsakymas.Pristatyta\n" +
                    "from Uzsakymas\n" +
                    "where Uzsakymas.Uzsakymo_ID = ?");
            statement.setInt(1, pasirinktasUzsakymas);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                adresas.setText(rs.getString(1));
                this.apmokejimas.setValue(rs.getString(2));
                if (rs.getInt(3) == 1){
                    this.sumoketa.setValue("Taip");
                } else this.sumoketa.setValue("Ne");
                if (rs.getInt(4) == 1){
                    this.pristatyta.setValue("Taip");
                } else this.pristatyta.setValue("Ne");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
    @FXML
    public void atnaujinti(ActionEvent event) throws SQLException {
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Uzsakymas SET Kliento_ID = ?, Darbuotojo_ID = ?, Kli_Kliento_ID = ?, Prisatymo_adresas = ?, Apmokejimo_budas = ?, Sumoketa = ?, Pristatyta = ?\n" +
                    "WHERE Uzsakymo_ID = ?");
            statement.setInt(1, klientoID.get(klientas.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, darbuotojoID.get(darbuotojas.getSelectionModel().getSelectedIndex()));
            statement.setInt(3, klientoID.get(klientas.getSelectionModel().getSelectedIndex()));
            statement.setString(4, adresas.getText());
            if (apmokejimas.getSelectionModel().getSelectedItem() == "Grynais"){
                statement.setString(5, "Grynais");
            } else {
                statement.setString(5, "Kortele");
            }
            if (sumoketa.getSelectionModel().getSelectedItem() == "Taip"){
                statement.setInt(6, 1);
            } else statement.setInt(6, 0);
            if (pristatyta.getSelectionModel().getSelectedItem() == "Taip"){
                statement.setInt(7, 1);
            } else statement.setInt(7,0);
            statement.setInt(8, pasirinktasUzsakymas);
            statement.executeUpdate();
        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus 1");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Kompiuteris SET Procesoriaus_ID = ?, Bloko_ID = ?, Darbuotojo_ID = ?, Korpuso_ID = ?, Ausintuvo_ID = ?, Plokstes_ID = ?\n" +
                    "WHERE Kompiuterio_ID = ?");
            statement.setInt(1, procesoriausID.get(procesorius.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, blokoID.get(blokas.getSelectionModel().getSelectedIndex()));
            statement.setInt(3, darbuotojoID.get(darbuotojas.getSelectionModel().getSelectedIndex()));
            statement.setInt(4, korpusoID.get(korpusas.getSelectionModel().getSelectedIndex()));
            statement.setInt(5, ausintuvoID.get(ausintuvas.getSelectionModel().getSelectedIndex()));
            statement.setInt(6, plokstesID.get(plokste.getSelectionModel().getSelectedIndex()));
            statement.setInt(7, pasirinktasUzsakymas);
            statement.executeUpdate();
        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Sudarymas_3 SET Atminties_ID = ?\n" +
                    "WHERE Kompiuterio_ID = ?");
            statement.setInt(1, atmintiesID.get(atmintis.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, data.getID());
            statement.executeUpdate();

        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Sudarymas_4 SET Ventiliatoriaus_ID = ?\n" +
                    "WHERE Kompiuterio_ID = ?");
            statement.setInt(1, ventiliatoriausID.get(ventiliatorius.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, data.getID());
            statement.executeUpdate();

        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Sudarymas_5 SET Disko_ID = ?\n" +
                    "WHERE Kompiuterio_ID = ?");
            statement.setInt(1, diskoID.get(diskas.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, data.getID());
            statement.executeUpdate();

        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        try {
            statement = connection.prepareStatement("UPDATE Sudarymas_6 SET Vaizdo_plokstes_ID = ?\n" +
                    "WHERE Kompiuterio_ID = ?");
            statement.setInt(1, vaizdoPlokstesID.get(vaizdoPlokste.getSelectionModel().getSelectedIndex()));
            statement.setInt(2, data.getID());
            statement.executeUpdate();

        } catch (Exception e){
            alertMessage("Patikrinkite įvestus laukus");
        }
        DbOperations.disconnectFromDb(connection, statement);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void pasirinkimai(){
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("SELECT Procesorius.Procesoriaus_ID, Procesoriaus_ausintuvas.Ausintuvo_ID, Pagrindine_plokste.Plokstes_ID, Maitinimo_blokas.Bloko_ID, Korpusas.Korpuso_ID, Uzsakymas.Uzsakymo_ID, Darbuotojas.Darbuotojo_ID, Klientas.Kliento_ID\n" +
                    "FROM Procesorius, Procesoriaus_ausintuvas, Pagrindine_plokste, Maitinimo_blokas, Korpusas, Uzsakymas, Darbuotojas, Klientas, Kompiuteris\n" +
                    "WHERE Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Uzsakymo_ID = Uzsakymas.Uzsakymo_ID\n" +
                    "AND Uzsakymas.Kliento_ID = Klientas.Kliento_ID\n" +
                    "AND Kompiuteris.Darbuotojo_ID = Darbuotojas.Darbuotojo_ID\n" +
                    "AND Kompiuteris.Procesoriaus_ID = Procesorius.Procesoriaus_ID\n" +
                    "AND Kompiuteris.Ausintuvo_ID = Procesoriaus_ausintuvas.Ausintuvo_ID\n" +
                    "AND Kompiuteris.Plokstes_ID = Pagrindine_plokste.Plokstes_ID\n" +
                    "AND Kompiuteris.Bloko_ID = Maitinimo_blokas.Bloko_ID\n" +
                    "AND Kompiuteris.Korpuso_ID = Korpusas.Korpuso_ID");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            rs.next();
            pasirinktasProcesorius = rs.getInt(1);
            pasirinktasAusintuvas = rs.getInt(2);
            pasirinktasPlokste = rs.getInt(3);
            pasirinktasBLokas = rs.getInt(4);
            pasirinktasKorpusas = rs.getInt(5);
            pasirinktasUzsakymas = rs.getInt(6);
            pasirinktasDarbuotojas = rs.getInt(7);
            pasirinktasKlientas = rs.getInt(8);

        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("select Sudarymas_3.Atminties_ID from Sudarymas_3\n" +
                    "where Sudarymas_3.Kompiuterio_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pasirinktasAtmintis = rs.getInt(1);
                pasirinktasAtmintiesKiekis++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("select Sudarymas_4.Ventiliatoriaus_ID from Sudarymas_4\n" +
                    "where Sudarymas_4.Kompiuterio_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pasirinktasVentiliatorius = rs.getInt(1);
                pasirinktasVentiliatoriausKiekis++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("select Sudarymas_5.Disko_ID from Sudarymas_5\n" +
                    "where Sudarymas_5.Kompiuterio_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pasirinktasDiskas = rs.getInt(1);
                pasirinktasDiskoKiekis++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
        try{
            connection = DbOperations.connectToDb();
            statement = connection.prepareStatement("select Sudarymas_6.Vaizdo_plokstes_ID from Sudarymas_6\n" +
                    "where Sudarymas_6.Kompiuterio_ID = ?");
            statement.setInt(1, data.getID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pasirinktasVaizdoPlokste = rs.getInt(1);
                pasirinktasVaizdoPlokstesKiekis++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
    private void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
