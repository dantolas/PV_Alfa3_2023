package com.kuta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Serves as wrapper for mysql jdbc connector. 
 * Provides functionality to connect to database, and execute queries.
 */
public class DatabaseConnector {

    private static String username;
    private static String password;
    private static String connectionUrl;

    /**
     * Get an open connection to db.
     * @return java.sql.Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionUrl, username,password);
    };

    /**
     * @param c
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement prepSql(Connection c,String sql) throws SQLException{

            return c.prepareStatement(sql);
    }

    /**
     * Can be used to test connection to db.
     * @return true if connection established
     */
    public static boolean testConnection(){
        String testSql = "SELECT version()";

        try(Connection c = getConnection()){
            try (
            PreparedStatement ps = prepSql(c,testSql); 

            ResultSet rs = ps.executeQuery()
        ){
                return true;

            }

        }catch(SQLException e){

            return false;
        }
    }



    /**
     * Initialize the connector
     * @param dbHost Hostname of db server
     * @param dbName Name of database on server
     * @param username Connection username
     * @param password Connection password
     */
    public static void init(String dbHost,String dbName, String username, String password){
        DatabaseConnector.connectionUrl = dbHost + "/" + dbName;
        DatabaseConnector.password = password;
        DatabaseConnector.username = username;

    }

    public static String settingsToString(){
        return connectionUrl + " -u " + username + "-p " + password;
    }
    
}
