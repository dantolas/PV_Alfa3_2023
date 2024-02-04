package com.kuta.app;

import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.app.objectTemplates.Prescription;
import com.kuta.db.DataLayerAPI;
import com.kuta.ui.ConsoleUI;

/**
 * Bundles all the Delete of the CRUD together
 */
public class Delete {

    ConsoleUI ui;
    DataLayerAPI dataLayer;
    PrintList print;

    public Delete(ConsoleUI ui, DataLayerAPI dataLayer,PrintList print){
        this.ui = ui;
        this.dataLayer = dataLayer;
        this.print = print;
    }

    
    /**
     * Starts the process of deleting a prescription from db 
     */
    public void prescription(){
        List<Prescription> prescriptions = dataLayer.getPrescriptionDAO().getAll();
        if(prescriptions.size() < 1){
            ui.printSeparatorLine();
            ui.println("No prescriptions in database.");
            ui.printSeparatorLine();
            return;
        }
        while(true){
            print.prescriptions(prescriptions);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,prescriptions.size())-1;
            if(numberInput == prescriptions.size()) return;
            Prescription prescription = prescriptions.get(numberInput);
            ui.printSeparatorLine();
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getPrescriptionDAO().delete(prescription);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Prescription sucesfully deleted.||UUID:"+print.uuidToString(prescription.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't delete prescription.");
            ui.printSeparatorLine();
        }
    }

    public void medication(){
        List<Medication> meds= dataLayer.getMedicationDAO().getAll();
        if(meds.size() < 1){
            ui.printSeparatorLine();
            ui.println("No meds in database.");
            ui.printSeparatorLine();
            return;
        }
        while(true){
            print.meds(meds);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,meds.size())-1;
            if(numberInput == meds.size()) return;
            Medication med = meds.get(numberInput);
            ui.printSeparatorLine();
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getMedicationDAO().delete(med);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Medication "+med.getName()+" Type:"+med.getType()+" sucesfully deleted.||UUID:"+print.uuidToString(med.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't delete medication.");
            ui.printSeparatorLine();
        }
    }

    public void insuranceCompany(){
        List<InsuranceCompany> companies = dataLayer.getInsuranceDAO().getAll();
        if(companies.size() < 1){
            ui.printSeparatorLine();
            ui.println("No companies in database.");
            ui.printSeparatorLine();
            return;
        }
        while(true){
            print.insuranceCompanies(companies);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,companies.size())-1;
            if(numberInput == companies.size()) return;
            InsuranceCompany company = companies.get(numberInput);
            ui.printSeparatorLine();
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getInsuranceDAO().delete(company);
            if(success) {
                ui.printSeparatorLine();
                ui.println("InsuranceCompany "+company.getName()+" Shortcut:"+company.getShortcut()+" sucesfully deleted.||UUID:"+
                    print.uuidToString(company.getId()));
                ui.printSeparatorLine();
                return;
            }

                ui.printSeparatorLine();
            ui.println("Couldn't delete company.");
                ui.printSeparatorLine();
        }
    }

    public void patient(){
        List<Patient> patients = dataLayer.getPatientDAO().getAll();
        if(patients.size() < 1){
            ui.printSeparatorLine();
            ui.println("No patients in database.");
            ui.printSeparatorLine();
            return;
        }
        while(true){
            print.patients(patients);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,patients.size())-1;
            if(numberInput == patients.size()) return;
            Patient patient = patients.get(numberInput);
            ui.printSeparatorLine();
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getPatientDAO().delete(patient);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Patient "+patient.getFname()+" "+patient.getLname()+" sucesfully deleted.||UUID:"+print.uuidToString(patient.getId()));
                ui.printSeparatorLine();
                return;
            }

                ui.printSeparatorLine();
            ui.println("Couldn't delete patient.");
                ui.printSeparatorLine();
        }
        
    }
    public void doctor(){
        List<Doctor> doctors = dataLayer.getDoctorDAO().getAll();
        if(doctors.size() < 1){
            ui.printSeparatorLine();
            ui.println("No doctors in database.");
            ui.printSeparatorLine();
            return;
        }
        while(true){

            print.doctors(doctors);
            ui.println("\n|exit|");
            int numberInput = ui.readInputInt(1,doctors.size())-1;
            if(numberInput == doctors.size()) return;
            Doctor doctor = doctors.get(numberInput);
            ui.printSeparatorLine();
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getDoctorDAO().delete(doctor);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Doctor "+doctor.getFname()+" "+doctor.getLname()+" sucesfully deleted.||UUID:"+print.uuidToString(doctor.getId()));
                ui.printSeparatorLine();
                return;
            }
                ui.printSeparatorLine();
            ui.println("Couldn't delete doctor.");
                ui.printSeparatorLine();
        }

        
    }
}
