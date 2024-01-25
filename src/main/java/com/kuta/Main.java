package com.kuta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;


public class Main{

    public static void main(String[] args) {

        Config config = null;

        try{
            config = Config.createFromFile("/home/charming/Projects/code/pv/java/alfa3/conf/config.json");
            System.out.println(config);
        }catch(Exception E){
            System.out.println("Config exception");
            
        }

        DatabaseConnector.init(config.db.host,config.db.name,config.db.username,config.db.password);
        System.out.println("Connected:"+DatabaseConnector.testConnection());
    }
}
