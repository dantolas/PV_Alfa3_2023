package com.kuta.db.DAO.dbObjects;

import java.util.List;
/**
 * Prescription
 */
public class Prescription {

    public static class Item {
        public Medication med;
        public int amount;
        public boolean insuranceCovered;
        public String description;

        public Item(){}

        public Medication getMed() {
            return med;
        }

        public void setMed(Medication med) {
            this.med = med;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isInsuranceCovered() {
            return insuranceCovered;
        }

        public void setInsuranceCovered(boolean insuranceCovered) {
            this.insuranceCovered = insuranceCovered;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


    }

    byte[] id;
    Patient patient;
    Doctor doctor;
    String diagnosis;
    List<Item> items;
    
    public Prescription(){}


    
    public byte[] getId() {
        return id;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public List<Item> getMeds() {
        return items;
    }
    



    public void setId(byte[] id) {
        this.id = id;
    }



    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }



    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }



    public void setMeds(List<Item> meds) {
        this.items = meds;
    }


    
}
