package com.example.pcbuilder2.fxControl;

import com.example.pcbuilder2.model.KompiuterisID;
import com.example.pcbuilder2.model.KompiuterisTable;
import com.example.pcbuilder2.utils.DbOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Kompiuteris implements Initializable {
    @FXML
    private TableView<KompiuterisTable> kompiuterisTable;
    @FXML
    private TableColumn<KompiuterisTable, String> procesoriusCol;
    @FXML
    private TableColumn<KompiuterisTable, String> ausintuvasCol;
    @FXML
    private TableColumn<KompiuterisTable, String> ploksteCol;
    @FXML
    private TableColumn<KompiuterisTable, String> ramCol;
    @FXML
    private TableColumn<KompiuterisTable, String> ventiliatoriusCol;
    @FXML
    private TableColumn<KompiuterisTable, String> diskasCol;
    @FXML
    private TableColumn<KompiuterisTable, String> gpuCol;
    @FXML
    private TableColumn<KompiuterisTable, String> psuCol;
    @FXML
    private TableColumn<KompiuterisTable, String> korpusasCol;

    private Connection connection;
    private PreparedStatement statement;
    private ArrayList<Integer> kompiuterioIDArray = new ArrayList<>();
    KompiuterisID data = KompiuterisID.getInstance();

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

    @FXML
    private ChoiceBox<String> procesorius;
    @FXML
    private ChoiceBox<String> ausintuvas;
    @FXML
    private ChoiceBox<String> plokste;
    @FXML
    private ChoiceBox<String> gpu;
    @FXML
    private ChoiceBox<String> korpusas;
    @FXML
    private ChoiceBox<String> klientas;
    @FXML
    private ChoiceBox<String> darbuotojas;
    @FXML
    private ChoiceBox<String> pristatyta;
    @FXML
    private ChoiceBox<String> sumoketa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        procesoriusCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("procesorius"));
        ausintuvasCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("ausintuvas"));
        ploksteCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("plokste"));
        ramCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("ram"));
        ventiliatoriusCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("ventiliatorius"));
        diskasCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("diskas"));
        gpuCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("gpu"));
        psuCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("psu"));
        korpusasCol.setCellValueFactory(new PropertyValueFactory<KompiuterisTable, String>("korpusas"));
        String[] procesoriai = {"Intel", "AMD"};
        procesorius.getItems().addAll(procesoriai);
        String[] ausintuvai = {"Oras", "Skystis"};
        ausintuvas.getItems().addAll(ausintuvai);
        String[] pagrindinesPlokstes = {"ATX", "Micro-ATX", "Mini-ITX"};
        plokste.getItems().addAll(pagrindinesPlokstes);
        String[] vaizdoPlokstes = {"2000", "4000", "1600", "16000"};
        gpu.getItems().addAll(vaizdoPlokstes);
        String[] korpusai = {"ATX", "Micro-ATX", "Mini-ITX"};
        korpusas.getItems().addAll(korpusai);
        String[] klientai = {"Žukauskas Mantas", "Aidas Kazlauskas", "Balciunaite Gabija", "Baranauskas Linas", "Butkus Jovydas", "Jankauskas Domas", "Navickaite Milda", "Paulauskaite Indre", "Petrauskas Arminas", "Stankevicius Eimantas", "Urbonas Mantvydas", "Vasiliauskas Laimonas"};
        klientas.getItems().addAll(klientai);
        String[] darbuotojai = {"Šimkus Pranas", "Žilinskas Alfonsas", "Ramanauskaite Asta", "Rimkute Ligita", "Savickas Kasparas"};
        darbuotojas.getItems().addAll(darbuotojai);
        String[] x = {"Taip", "Ne"};
        pristatyta.getItems().addAll(x);
        sumoketa.getItems().addAll(x);
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refresh() throws SQLException {
        kompiuterisTable.getItems().clear();
        kompiuterioIDArray.clear();
        int procesoriausID;
        String procesorius = null;
        int blokoID;
        String blokas = null;
        int korpusoID;
        String korpusas = null;
        int ausintuvoID;
        String ausintuvas = null;
        int plokstesID;
        String plokste = null;
        int kompiuterioID;
        String atmintis = null;
        int atmintiesKiekis;
        String ventiliatorius = null;
        int ventiliatoriausKiekis;
        String diskas = null;
        int diskoKiekis;
        String vaizdoPlokste = null;
        int vaizdoPlokstesKiekis;
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("SELECT * FROM Kompiuteris");
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            atmintiesKiekis=0;
            ventiliatoriausKiekis=0;
            diskoKiekis =0;
            vaizdoPlokstesKiekis=0;
            kompiuterioID = rs.getInt(1);
            kompiuterioIDArray.add(rs.getInt(1));
            statement = connection.prepareStatement("Select Procesorius.Gamintojas from Procesorius, Kompiuteris\n" +
                    "WHERE Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Procesoriaus_ID = Procesorius.Procesoriaus_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs1 = statement.executeQuery();
            while (rs1.next()){
                procesorius = rs1.getString(1);
            }
            statement = connection.prepareStatement("Select Maitinimo_blokas.Maitinimo_galia from Maitinimo_blokas, Kompiuteris\n" +
                    "WHERE Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Bloko_ID = Maitinimo_blokas.Bloko_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs2 = statement.executeQuery();
            while (rs2.next()){
                blokas = rs2.getString(1) + " W";
            }
            statement = connection.prepareStatement("Select Korpusas.Dydis FROM Korpusas, Kompiuteris\n" +
                    "WHERE Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Korpuso_ID = Korpusas.Korpuso_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs3 = statement.executeQuery();
            while (rs3.next()){
                korpusas = rs3.getString(1);
            }
            statement = connection.prepareStatement("Select Procesoriaus_ausintuvas.Ausinimo_budas FROM Procesoriaus_ausintuvas, Kompiuteris\n" +
                    "WHERE Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Ausintuvo_ID = Procesoriaus_ausintuvas.Ausintuvo_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs4 = statement.executeQuery();
            while (rs4.next()){
                ausintuvas = rs4.getString(1);
            }
            statement = connection.prepareStatement("Select Pagrindine_plokste.Dydis FROM Pagrindine_plokste, Kompiuteris\n" +
                    "WHERE Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Plokstes_ID = Pagrindine_plokste.Plokstes_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs5 = statement.executeQuery();
            while (rs5.next()){
                plokste = rs5.getString(1);
            }
            statement = connection.prepareStatement("SELECT Operatyvioji_atmintis.Talpa FROM Operatyvioji_atmintis, Kompiuteris, Sudarymas_3\n" +
                    "WHERE Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_3.Kompiuterio_ID\n" +
                    "AND Sudarymas_3.Atminties_ID = Operatyvioji_atmintis.Atminties_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs6 = statement.executeQuery();
            while (rs6.next()){
                atmintis = rs6.getString(1) + " GB";
                atmintiesKiekis++;
            }
            atmintis += " " + atmintiesKiekis + "x";
            statement = connection.prepareStatement("SELECT Ventiliatorius.Ventiliatoriaus_dydis FROM Ventiliatorius, Kompiuteris, Sudarymas_4\n" +
                    "WHERE Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_4.Kompiuterio_ID\n" +
                    "AND Sudarymas_4.Ventiliatoriaus_ID = Ventiliatorius.Ventiliatoriaus_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs7 = statement.executeQuery();
            while (rs7.next()){
                ventiliatorius = rs7.getString(1) + " mm";
                ventiliatoriausKiekis++;
            }
            ventiliatorius += " " + ventiliatoriausKiekis + "x";
            statement = connection.prepareStatement("SELECT Kietasis_diskas.Talpa FROM Kietasis_diskas, Kompiuteris, Sudarymas_5\n" +
                    "WHERE Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_5.Kompiuterio_ID\n" +
                    "AND Sudarymas_5.Disko_ID = Kietasis_diskas.Disko_ID\n");
            statement.setInt(1, kompiuterioID);
            ResultSet rs8 = statement.executeQuery();
            while (rs8.next()){
                diskas = rs8.getString(1) + " GB";
                diskoKiekis++;
            }
            diskas += " " + diskoKiekis + "x";
            statement = connection.prepareStatement("SELECT Vaizdo_plokste.Talpa FROM Vaizdo_plokste, Kompiuteris, Sudarymas_6\n" +
                    "WHERE Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_6.Kompiuterio_ID\n" +
                    "AND Sudarymas_6.Vaizdo_plokstes_ID = Vaizdo_plokste.Vaizdo_plokstes_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs9 = statement.executeQuery();
            while (rs9.next()){
                vaizdoPlokste = rs9.getString(1) + " MB";
                vaizdoPlokstesKiekis++;
            }
            vaizdoPlokste += " " + vaizdoPlokstesKiekis + "x";
            KompiuterisTable kompiuterisTable = new KompiuterisTable(procesorius, ausintuvas, plokste, atmintis, ventiliatorius, diskas, vaizdoPlokste, blokas, korpusas);
            ObservableList<KompiuterisTable> observableList = this.kompiuterisTable.getItems();
            observableList.add(kompiuterisTable);
            this.kompiuterisTable.setItems(observableList);
        }
        DbOperations.disconnectFromDb(connection, statement);
    }
    @FXML
    public void prideti(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("kompiuterisPrideti.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Prideti kompiuteri");
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.NONE);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Nepavyko atidaryti lango");
        }
    }
    @FXML
    void istrinti(ActionEvent event) throws SQLException {
        int uzsakymoID = 0;
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("select Kompiuteris.Uzsakymo_ID from Kompiuteris \n" +
                "where Kompiuteris.Kompiuterio_ID = ?");
        statement.setInt(1, kompiuterioIDArray.get(kompiuterisTable.getSelectionModel().getSelectedIndex()));
        ResultSet rs = statement.executeQuery();
        rs.next();
        uzsakymoID = rs.getInt(1);
        DbOperations.disconnectFromDb(connection, statement);
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("delete from Uzsakymas where Uzsakymas.Uzsakymo_ID = ?");
        statement.setInt(1, uzsakymoID);
        statement.executeUpdate();
        DbOperations.disconnectFromDb(connection, statement);
        kompiuterisTable.getItems().remove(kompiuterioIDArray.get(kompiuterisTable.getSelectionModel().getSelectedIndex()));
        refresh();
    }
    @FXML
    void redaguoti(ActionEvent event){
        data.setID(kompiuterioIDArray.get(kompiuterisTable.getSelectionModel().getSelectedIndex()));
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("kompiuterisRedaguoti.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Redaguoti kompiuterio duomenis");
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.NONE);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Nepavyko atidaryti lango");
        }
    }
    @FXML
    void atnaujinti(ActionEvent event) throws SQLException {
        refresh();
        procesorius.setValue(null);
        ausintuvas.setValue(null);
        plokste.setValue(null);
        gpu.setValue(null);
        korpusas.setValue(null);
        klientas.setValue(null);
        darbuotojas.setValue(null);
        pristatyta.setValue(null);
        sumoketa.setValue(null);
    }
    @FXML
    void filtruoti(ActionEvent event) throws SQLException {
        kompiuterisTable.getItems().clear();
        kompiuterioIDArray.clear();
        int kompiuterioID = 0;
        String dummyprocesorius = null;
        String dummyblokas = null;
        String dummykorpusas = null;
        String dummyausintuvas = null;
        String dummyplokste = null;
        String dummyatmintis = null;
        int atmintiesKiekis = 0;
        String dummyventiliatorius = null;
        int ventiliatoriausKiekis = 0;
        String dummydiskas = null;
        int diskoKiekis = 0;
        String dummyvaizdoPlokste = null;
        int vaizdoPlokstesKiekis = 0;
        connection = DbOperations.connectToDb();
        statement = connection.prepareStatement("select MAX(Kompiuteris.Kompiuterio_ID) AS Kompiuterio_ID, MAX(Procesorius.Gamintojas) AS Gamintojas, MAX(Procesoriaus_ausintuvas.Ausinimo_budas) AS Ausinimo_budas, MAX(Pagrindine_plokste.Dydis) AS Dydis, MAX(Operatyvioji_atmintis.Talpa) AS Talpa, MAX(Ventiliatorius.Ventiliatoriaus_dydis) AS Ventiliatoriaus_Dydis, MAX(Kietasis_diskas.Talpa) AS Talpa, MAX(Vaizdo_plokste.Talpa) AS Talpa, MAX(Maitinimo_blokas.Maitinimo_galia) AS Maitinimo_galia, MAX(Korpusas.Dydis) AS Dydis\n" +
                "from Kompiuteris, Procesorius, Procesoriaus_ausintuvas, Pagrindine_plokste, Operatyvioji_atmintis, Sudarymas_3, Ventiliatorius, Sudarymas_4, Kietasis_diskas, Sudarymas_5, Vaizdo_plokste, Sudarymas_6, Maitinimo_blokas, Korpusas, Uzsakymas, Darbuotojas, Klientas\n" +
                "Where Kompiuteris.Procesoriaus_ID = Procesorius.Procesoriaus_ID\n" +
                "AND Kompiuteris.Ausintuvo_ID = Procesoriaus_ausintuvas.Ausintuvo_ID\n" +
                "AND Kompiuteris.Plokstes_ID = Pagrindine_plokste.Plokstes_ID\n" +
                "AND Kompiuteris.Bloko_ID = Maitinimo_blokas.Bloko_ID\n" +
                "AND Kompiuteris.Korpuso_ID = Korpusas.Korpuso_ID\n" +
                "AND Kompiuteris.Kompiuterio_ID = Sudarymas_3.Kompiuterio_ID\n" +
                "AND Kompiuteris.Kompiuterio_ID = Sudarymas_4.Kompiuterio_ID\n" +
                "AND Kompiuteris.Kompiuterio_ID = Sudarymas_5.Kompiuterio_ID\n" +
                "AND Kompiuteris.Kompiuterio_ID = Sudarymas_6.Kompiuterio_ID\n" +
                "AND Sudarymas_3.Atminties_ID = Operatyvioji_atmintis.Atminties_ID\n" +
                "AND Sudarymas_4.Ventiliatoriaus_ID = Ventiliatorius.Ventiliatoriaus_ID\n" +
                "AND Sudarymas_5.Disko_ID = Kietasis_diskas.Disko_ID\n" +
                "AND Sudarymas_6.Vaizdo_plokstes_ID = Vaizdo_plokste.Vaizdo_plokstes_ID\n" +
                "AND (Vaizdo_plokste.Talpa = ? OR ? IS NULL)\n" +
                "AND (Pagrindine_plokste.Dydis = ? OR ? IS NULL)\n" +
                "AND (Procesorius.Gamintojas = ? OR ? IS NULL)\n" +
                "AND (Procesoriaus_ausintuvas.Ausinimo_budas = ? OR ? IS NULL)\n" +
                "AND (Korpusas.Dydis = ? OR ? IS NULL)\n" +
                "AND Kompiuteris.Uzsakymo_ID = Uzsakymas.Uzsakymo_ID\n" +
                "AND Uzsakymas.Darbuotojo_ID = Darbuotojas.Darbuotojo_ID\n" +
                "AND Uzsakymas.Kliento_ID = Klientas.Kliento_ID\n" +
                "AND (Klientas.Kliento_Pavarde_Vardas = ? OR ? IS NULL)\n" +
                "AND (Darbuotojas.Darbuotojo_Pavarde_Vardas = ? OR ? IS NULL)\n" +
                "AND (Uzsakymas.Pristatyta = ? OR ? IS NULL)\n" +
                "AND (Uzsakymas.Sumoketa = ? OR ? IS NULL)\n" +
                "GROUP BY Kompiuteris.Kompiuterio_ID");
        if (gpu.getSelectionModel().getSelectedItem() == null){
            statement.setString(1, null);
            statement.setString(2, null);
        } else {
            statement.setString(1, gpu.getSelectionModel().getSelectedItem());
            statement.setString(2, gpu.getSelectionModel().getSelectedItem());
        }
        if (plokste.getSelectionModel().getSelectedItem() == null){
            statement.setString(3, null);
            statement.setString(4, null);
        } else {
            statement.setString(3, plokste.getSelectionModel().getSelectedItem());
            statement.setString(4, plokste.getSelectionModel().getSelectedItem());
        }
        if (procesorius.getSelectionModel().getSelectedItem() == null){
            statement.setString(5, null);
            statement.setString(6, null);
        } else {
            statement.setString(5, procesorius.getSelectionModel().getSelectedItem());
            statement.setString(6, procesorius.getSelectionModel().getSelectedItem());
        }
        if (ausintuvas.getSelectionModel().getSelectedItem() == null){
            statement.setString(7, null);
            statement.setString(8, null);
        } else {
            statement.setString(7, ausintuvas.getSelectionModel().getSelectedItem());
            statement.setString(8, ausintuvas.getSelectionModel().getSelectedItem());
        }
        if (korpusas.getSelectionModel().getSelectedItem() == null){
            statement.setString(9, null);
            statement.setString(10, null);
        } else {
            statement.setString(9, " " + korpusas.getSelectionModel().getSelectedItem());
            statement.setString(10, " " + korpusas.getSelectionModel().getSelectedItem());
        }
        if (klientas.getSelectionModel().getSelectedItem() == null){
            statement.setString(11, null);
            statement.setString(12, null);
        } else {
            statement.setString(11, klientas.getSelectionModel().getSelectedItem());
            statement.setString(12, klientas.getSelectionModel().getSelectedItem());
        }
        if (darbuotojas.getSelectionModel().getSelectedItem() == null){
            statement.setString(13, null);
            statement.setString(14, null);
        } else {
            statement.setString(13, darbuotojas.getSelectionModel().getSelectedItem());
            statement.setString(14, darbuotojas.getSelectionModel().getSelectedItem());
        }
        if (pristatyta.getSelectionModel().getSelectedItem() == null){
            statement.setString(15, null);
            statement.setString(16, null);
        } else {
            if (pristatyta.getSelectionModel().getSelectedItem() == "Taip") {
                statement.setInt(15, 1);
                statement.setInt(16, 1);
            } else {
                statement.setInt(15, 0);
                statement.setInt(16, 0);
            }
        }
        if (sumoketa.getSelectionModel().getSelectedItem() == null){
            statement.setString(17, null);
            statement.setString(18, null);
        } else {
            if (sumoketa.getSelectionModel().getSelectedItem() == "Taip") {
                statement.setInt(17, 1);
                statement.setInt(18, 1);
            } else {
                statement.setInt(17, 0);
                statement.setInt(18, 0);
            }
        }
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            kompiuterioIDArray.add(rs.getInt(1));
            kompiuterioID = rs.getInt(1);
            dummyprocesorius = rs.getString(2);
            dummyausintuvas = rs.getString(3);
            dummyplokste = rs.getString(4);
            dummyatmintis = rs.getString(5) + " GB";
            dummyventiliatorius = rs.getString(6) + " mm";
            dummydiskas = rs.getString(7) + " GB";
            dummyvaizdoPlokste = rs.getString(8) + " MB";
            dummyblokas = rs.getString(9) + " W";
            dummykorpusas = rs.getString(10);
            statement = connection.prepareStatement("select Atminties_ID from Sudarymas_3, Kompiuteris \n" +
                    "where Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_3.Kompiuterio_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs1 = statement.executeQuery();
            atmintiesKiekis = 0;
            while (rs1.next()){
                atmintiesKiekis++;
            }
            statement = connection.prepareStatement("select Sudarymas_4.Ventiliatoriaus_ID from Sudarymas_4, Kompiuteris \n" +
                    "where Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_4.Kompiuterio_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs2 = statement.executeQuery();
            ventiliatoriausKiekis = 0;
            while (rs2.next()){
                ventiliatoriausKiekis++;
            }
            statement = connection.prepareStatement("select Sudarymas_5.Disko_ID from Sudarymas_5, Kompiuteris \n" +
                    "where Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_5.Kompiuterio_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs3 = statement.executeQuery();
            diskoKiekis = 0;
            while (rs3.next()){
                diskoKiekis++;
            }
            statement = connection.prepareStatement("select Sudarymas_6.Vaizdo_plokstes_ID from Sudarymas_6, Kompiuteris \n" +
                    "where Kompiuteris.Kompiuterio_ID = ?\n" +
                    "AND Kompiuteris.Kompiuterio_ID = Sudarymas_6.Kompiuterio_ID");
            statement.setInt(1, kompiuterioID);
            ResultSet rs4 = statement.executeQuery();
            vaizdoPlokstesKiekis=0;
            while (rs4.next()){
                vaizdoPlokstesKiekis++;
            }
            KompiuterisTable kompiuterisTable = new KompiuterisTable(dummyprocesorius, dummyausintuvas, dummyplokste, dummyatmintis + " " + atmintiesKiekis + "x", dummyventiliatorius + " " + ventiliatoriausKiekis + "x", dummydiskas + " " + diskoKiekis + "x", dummyvaizdoPlokste + " " + vaizdoPlokstesKiekis + "x", dummyblokas, dummykorpusas);
            ObservableList<KompiuterisTable> observableList = this.kompiuterisTable.getItems();
            observableList.add(kompiuterisTable);
            this.kompiuterisTable.setItems(observableList);
        }
    }
}
