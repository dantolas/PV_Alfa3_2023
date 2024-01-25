package com.kuta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DatabaseConnector
 */
public class DatabaseConnector {

    private static String username;
    private static String password;
    private static String connectionUrl;

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionUrl, username,password);

    };

    public static PreparedStatement prepSql(String sql) throws SQLException{

        return getConnection().prepareStatement(sql);
    }

    public static boolean testConnection(){
        String testSql = "SELECT version()";

        try (
        PreparedStatement ps =  (PreparedStatement) getConnection().prepareStatement(testSql); 
        
        ResultSet rs = ps.executeQuery()
        ){
            return true;

        }catch(SQLException e){

            return false;
        }
    }

    public static void init(String dbHost,String dbName, String username, String password){
        DatabaseConnector.connectionUrl = dbHost + "/" + dbName;
        DatabaseConnector.password = password;
        DatabaseConnector.username = username;

    }

    
}
