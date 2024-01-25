package com.kuta.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
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


    }
    
    public DB db;

    public static String readFileToString(String filepath) throws IOException{

        BufferedReader reader = Files.newBufferedReader(Paths.get(filepath));

        StringBuilder builder = new StringBuilder();

        String currLine = reader.readLine();
        while(currLine != null){

            builder.append(currLine);
            currLine = reader.readLine();
        }

        return builder.toString();

    }

    public Config(){
    }

    public static Config createFromFile(String filepath) throws IOException{

        String json = readFileToString(filepath);
        return GSON.fromJson(json,Config.class);

    }


    public DB getDb() {
        return db;
    }

}
