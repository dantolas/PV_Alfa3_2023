package com.kuta.db;

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
 *DataLayerAPI 
 */
public class DataLayerAPI {

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
