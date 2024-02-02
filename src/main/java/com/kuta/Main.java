package com.kuta;

import com.kuta.app.ApplicationLayer;
import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;


public class Main{

    public static void main(String[] args) {

        System.out.println(Integer.MIN_VALUE);
        Config config = null;

        try{
            config = Config.createFromFile("/home/charming/Projects/code/pv/java/alfa3/conf/config.json");
            System.out.println(config);
            DatabaseConnector.init(config.db.host,config.db.name,config.db.username,config.db.password);
            System.out.println("Connected:"+DatabaseConnector.testConnection());
            
            ApplicationLayer.DEFAULT_INIT(System.out,System.in).run();
        }catch(Exception e){
            e.printStackTrace();
        }


        
    }

}
