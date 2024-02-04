package com.kuta.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.app.objectTemplates.Prescription;
import com.kuta.db.DAO.DAOs.DAO;
import com.kuta.db.DAO.DAOs.DoctorDAO;
import com.kuta.db.DAO.DAOs.InsuranceDAO;
import com.kuta.db.DAO.DAOs.MedicationDAO;
import com.kuta.db.DAO.DAOs.PatientDAO;
import com.kuta.db.DAO.DAOs.PrescriptionDAO;

/**
 * Serves as a bundler for the entire data layer of the application
 * Provides functionality for making database calls and retreiving data
 * from database.
 *
 * Uses DAO design pattern for database tables.
 */
public class DataLayerAPI {

    public String getPrescSummReport() throws SQLException{
        try(Connection c = DatabaseConnector.getConnection()){
            String sql = 
            """
            select * from Presc_Summ;
            """;
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                StringBuilder report = new StringBuilder("# Prescriptions summary data report\n");
                report.append("| Prescribed | Doctor | Patient | Diagnosis | Medicine |\n");
                report.append("| ---------- | ------ | ------- | --------- | -------- |\n");
                try(ResultSet rs = ps.executeQuery()){

                    while(rs.next()){
                        report.append("| "+rs.getString(1));
                        report.append("| "+rs.getString(2));
                        report.append("| "+rs.getString(3));
                        report.append("| "+rs.getString(4));
                        report.append("| "+rs.getString(5) + " |");
                        report.append("\n");
                    }
                    return report.toString();

                }

            }
        }
    }

    public String getInsurancePatientsReport() throws SQLException{
        try(Connection c = DatabaseConnector.getConnection()){
            String sql = 
            """
            select * from Insurance_Patient_Count;
            """;
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                StringBuilder report = new StringBuilder("# Patiens registered with different insurance companies data report\n");
                report.append("| Registered patients | Insurance |\n");
                report.append("| ------------------- | --------- |\n");
                try(ResultSet rs = ps.executeQuery()){

                    while(rs.next()){
                        report.append("| "+rs.getInt(1));
                        report.append("| "+rs.getString(2)+" |");
                        report.append("\n");
                    }
                    return report.toString();

                }

            }
        }
    }

    public String getPrescHandedOutReport() throws SQLException{
        try(Connection c = DatabaseConnector.getConnection()){
            String sql = 
            """
            select * from Prescriptions_Handed_Out;
            """;
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                StringBuilder report = new StringBuilder("# Prescriptions handed out data report\n");
                report.append("| Prescriptions handed out | Doctor |\n");
                report.append("| ------------------------ | ------ |\n");
                try(ResultSet rs = ps.executeQuery()){

                    while(rs.next()){
                        report.append("| "+rs.getInt(1));
                        report.append("| "+rs.getString(2)+" |");
                        report.append("\n");
                    }
                    return report.toString();

                }

            }
        }
    }

    public DAO<Doctor> getDoctorDAO() {
        return doctorDAO;
    }

    public DAO<InsuranceCompany> getInsuranceDAO() {
        return insuranceDAO;
    }

    public DAO<Patient> getPatientDAO() {
        return patientDAO;
    }

    public DAO<Medication> getMedicationDAO() {
        return medicationDAO;
    }

    public DAO<Prescription> getPrescriptionDAO() {
        return prescriptionDAO;
    }


    DAO<Doctor> doctorDAO;
    DAO<InsuranceCompany> insuranceDAO;
    DAO<Patient> patientDAO;
    DAO<Medication> medicationDAO;
    DAO<Prescription> prescriptionDAO;

    public DataLayerAPI setDoctorDAO(DAO<Doctor> ddao){
        this.doctorDAO = ddao;
        return this;
    };

    public DataLayerAPI setInsuranceDAO(DAO<InsuranceCompany> idao){
        this.insuranceDAO = idao;
        return this;

    };
    public DataLayerAPI setPatientDAO(DAO<Patient> pdao){
        this.patientDAO = pdao;
        return this;

    };
    public DataLayerAPI setMedicationDAO(DAO<Medication> mdao){
        this.medicationDAO = mdao;
        return this;
    };
    public DataLayerAPI setPrescriptionDAO(DAO<Prescription> prdao){
        this.prescriptionDAO = prdao;
        return this;
    };


    /**
     * Create a DataLayerAPI with default attributes
     * @return
     */
    public static DataLayerAPI createDefault(){
        DAO<InsuranceCompany> idao = new InsuranceDAO();
        DAO<Patient> pdao = new PatientDAO(idao);
        DAO<Medication> mdao = new MedicationDAO();
        DAO<Doctor> ddao = new DoctorDAO();
        DAO<Prescription> prdao = new PrescriptionDAO(ddao,pdao,mdao);
        return new DataLayerAPI()
        .setDoctorDAO(ddao)
        .setPatientDAO(pdao)
        .setInsuranceDAO(idao)
        .setMedicationDAO(mdao)
        .setPrescriptionDAO(prdao);
    }


    
}
