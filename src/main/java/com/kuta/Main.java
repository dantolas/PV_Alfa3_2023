package com.kuta;

import java.util.ArrayList;
import java.util.HexFormat;

import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.DAOs.InsuranceDAO;
import com.kuta.db.DAO.DAOs.PatientDAO;
import com.kuta.db.DAO.dbObjects.InsuranceCompany;
import com.kuta.db.DAO.dbObjects.Patient;


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


        InsuranceDAO idao = new InsuranceDAO();
        PatientDAO pdao = new PatientDAO(idao);
        byte[] id = HexFormat.of().parseHex("0C84DCFCBCF111EE92278C1759ECD7D1");
        Patient p = pdao.getByUUID(id);
        System.out.println(p.getFname()+" "+p.getInsuranceName());
        

    }

}
