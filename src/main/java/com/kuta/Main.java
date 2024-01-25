package com.kuta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Main{

    public static void main(String[] args) {
        System.out.println("Iam running");
        String sql= "select * from InsuranceCompany;";
        String connectionUrl = "jdbc:mysql://localhost:3306/alfa3";

        try (Connection conn = (Connection) DriverManager.getConnection(connectionUrl, "charming", "kuta"); 
        PreparedStatement ps =  (PreparedStatement) conn.prepareStatement(sql); 
        ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                System.out.println("Executed query");
                System.out.println(rs.getString("name"));

            }

        } catch (SQLException e) {
            System.out.println("Exception encountered");
            e.printStackTrace();
        }
    }
}
