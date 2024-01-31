package com.kuta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HexFormat;

import com.kuta.app.ApplicationLayer;
import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.DAOs.DoctorDAO;
import com.kuta.db.DAO.DAOs.InsuranceDAO;
import com.kuta.db.DAO.DAOs.MedicationDAO;
import com.kuta.db.DAO.DAOs.PatientDAO;
import com.kuta.db.DAO.DAOs.PrescriptionDAO;
import com.kuta.db.DAO.dbObjects.Doctor;
import com.kuta.db.DAO.dbObjects.InsuranceCompany;
import com.kuta.db.DAO.dbObjects.Medication;
import com.kuta.db.DAO.dbObjects.Patient;
import com.kuta.db.DAO.dbObjects.Prescription;
import com.kuta.db.DAO.dbObjects.Prescription.Item;


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

        ApplicationLayer.DEFAULT_INIT().run();
        
    }

}
