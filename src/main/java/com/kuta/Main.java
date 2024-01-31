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
        DoctorDAO ddao = new DoctorDAO();
        InsuranceDAO idao = new InsuranceDAO();
        PatientDAO pdao = new PatientDAO(idao);
        MedicationDAO mdao = new MedicationDAO();

        String diagnosis = "Atherosclerosis of other type of bypass graft(s) of the extremities with intermittent claudication, left leg";
        Doctor doctor = ddao.getAll().get(0);
        Patient patient = pdao.getAll().get(0);

        Medication med = mdao.getAll().get(0);
        
        Item item = new Item();
        item.setAmount(2);
        item.setDescription("Totally new experimental medication, good luck");
        item.setMed(med);

        Prescription p = new Prescription();
        p.setPatient(patient);
        p.setDoctor(doctor);
        p.setDiagnosis(diagnosis);
        p.setMeds(new ArrayList<Item>(){{add(item);}});

        PrescriptionDAO prdao = new PrescriptionDAO(ddao, pdao, mdao);
        System.out.println("Inserting prescription:"+prdao.insert(p));

        
    }

}
