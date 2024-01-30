package com.kuta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HexFormat;

import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.DAOs.DoctorDAO;
import com.kuta.db.DAO.DAOs.InsuranceDAO;
import com.kuta.db.DAO.DAOs.MedicationDAO;
import com.kuta.db.DAO.DAOs.PatientDAO;
import com.kuta.db.DAO.DAOs.PrescriptionDAO;
import com.kuta.db.DAO.dbObjects.InsuranceCompany;
import com.kuta.db.DAO.dbObjects.Patient;
import com.kuta.db.DAO.dbObjects.Prescription;


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
        try {

            for (Connection connection : connections) {
                System.out.println("Connection open:"+connection.isValid(10));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected:"+DatabaseConnector.testConnection());

        DoctorDAO ddao = new DoctorDAO();
        InsuranceDAO idao = new InsuranceDAO();
        PatientDAO pdao = new PatientDAO(idao);
        MedicationDAO mdao = new MedicationDAO();
        PrescriptionDAO prdao = new PrescriptionDAO(ddao,pdao,mdao);
        byte[] id = HexFormat.of().parseHex("68610423BF5C11EE96EE8C1759ECD7D1");

        Prescription p = prdao.getByUUID(id);

        System.out.println(p.getDiagnosis());
        System.out.println(p.getMeds().size());
        System.out.println(p.getDoctor().getFname());
        System.out.println(p.getPatient().getLname());
        System.out.println(p.getMeds().get(0).getDescription());
        System.out.println(p.getMeds().get(0).getMed().getName());

        System.out.println(prdao.getAll().get(0).getPatient().getFname());
    }

}
