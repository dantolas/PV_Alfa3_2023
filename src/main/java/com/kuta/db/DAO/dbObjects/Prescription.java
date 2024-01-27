package com.kuta.db.DAO.dbObjects;

import java.util.List;
/**
 * Prescription
 */
public class Prescription {


    byte[] id;
    Patient patient;
    Doctor doctor;
    InsuranceCompany insuranceCompany;
    String diagnosis;
    List<Medication> meds;

    public byte[] getId() {
        return id;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public List<Medication> getMeds() {
        return meds;
    }


    
}
