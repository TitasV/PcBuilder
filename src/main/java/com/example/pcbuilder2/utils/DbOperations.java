package com.example.pcbuilder2.utils;

import javafx.scene.control.Alert;

import java.sql.*;

public class DbOperations {
    private static Connection connection;
    private static PreparedStatement statement;

    public static Connection connectToDb() throws SQLException {
        //jdbc:jtds:sqlserver://DESKTOP-1MQQNI1:1433/master;instance=SQLEXPRESS
        String DB_URL = "jdbc:sqlserver://DESKTOP-1MQQNI1\\SQLEXPRESS:1433;DatabaseName=master;encrypt = true;trustServerCertificate=true;IntegratedSecurity=true";
        String USER = "root";
        String PASS = "";
        connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
        //java code convention
    }
    public static void disconnectFromDb(Connection connection, Statement statement){
        try{
            if (connection != null && statement != null){
                connection.close();
                statement.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void alertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
