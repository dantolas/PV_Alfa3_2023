package com.kuta.conf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.kuta.util.IO;
import com.kuta.vendor.JsonGson;

public class Config {


    private static Gson GSON = JsonGson.gson;

    @Override
    public String toString() {
        //return "{\"db\":" + db + "}";
        return Config.GSON.toJson(this);
    }

    public class DB {

        @SerializedName("database_host")
        public String host;
        @SerializedName("database_name")
        public String name;
        @SerializedName("username")
        public String username;
        @SerializedName("password")
        public String password;
        @Override
        public String toString() {
            return "{\"database_host\":" + host + ", \"database_name\":" + name 
            + ", \"username\":" + username + ", \"password\":" + password + "}";
        }

        public String getHostname(){
            return "jdbc:mysql://"+host;
        }


    }
    
    public DB db;


    public Config(){
    }

    public static Config createFromFile(String filepath) throws FileNotFoundException, IOException{
        String json = IO.readFileIntoString(filepath);
        Config config = fromJson(json); 
        config.checkDefaults();
        return config;

    }

    public static Config fromJson(String json){

        return GSON.fromJson(json,Config.class);
    }

    public void checkDefaults(){
        if(this.db.host.toLowerCase().equals("default")) db.host = "localhost:3306";
        if(this.db.name.toLowerCase().equals("default")) db.name = "alfa3";
        if(this.db.username.toLowerCase().equals("default")) db.username = "su";
        if(this.db.password.toLowerCase().equals("default")) db.password = "student";
    }


    public DB getDb() {
        return db;
    }

}
