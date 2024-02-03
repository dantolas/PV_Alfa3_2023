package com.kuta.app;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.app.objectTemplates.Prescription;
import com.kuta.app.objectTemplates.Prescription.Item;
import com.kuta.db.DataLayerAPI;
import com.kuta.ui.ConsoleUI;

/**
 * Bundles all Create of the CRUD operations together
 */
public class Add {

    ConsoleUI ui;
    DataLayerAPI dataLayer;
    PrintList print;
    public Add(ConsoleUI ui, DataLayerAPI dataLayer,PrintList print){
        this.ui = ui;
        this.dataLayer = dataLayer;
        this.print = print;
    }

    /**
     * Start the process of creating and adding a new Prescription to db
     */
    public void prescription(){
        Prescription p = new Prescription();
        List<Patient> patients = dataLayer.getPatientDAO().getAll();
        List<Doctor> doctors = dataLayer.getDoctorDAO().getAll();

        while(true){

            print.patients(patients);
            ui.println("Select a patient.");
            int numberInput = ui.readInputInt(1,patients.size())-1;
            if(numberInput == patients.size()) return;
            p.setPatient(patients.get(numberInput));

            ui.printSeparatorLine();
            print.doctors(doctors);
            ui.println("Which doctor prescribed it?.");
            numberInput = ui.readInputInt(1,doctors.size())-1;
            if(numberInput == doctors.size()) return;
            p.setDoctor(doctors.get(numberInput));

            ui.printSeparatorLine();
            ui.println("Write the diagnosis");
            String input = ui.readInputString();
            if(input.equals("exit")) return;
            p.setDiagnosis(input);

            List<Item> items = new ArrayList<>();
            List<Medication> meds = dataLayer.getMedicationDAO().getAll();
            while(true){
                Item item = new Item();
                print.meds(meds);
                ui.println("What medication should be prescribed?");
                numberInput = ui.readInputInt(1,meds.size())-1;
                if(numberInput == meds.size()) break;
                item.setMed(meds.get(numberInput));

                ui.printSeparatorLine();
                ui.println("Type medication description (Enter to skip)");
                input = ui.readInputString();
                item.setDescription(input);

                ui.printSeparatorLine();
                ui.println("Amount:");
                numberInput = ui.readInputInt(1,1_000);
                item.setAmount(numberInput);

                ui.printSeparatorLine();
                ui.println("Is the medication covered by insurance?");
                item.setInsuranceCovered(ui.readInputBoolean());
                
                items.add(item);

                ui.println("Prescribe more medication?");
                if(!ui.readInputBoolean()) break;
            }
            p.setMeds(items);

            ui.println("|Patient:|"+p.getPatient().getLname()+ " " + p.getPatient().getFname());
            ui.printSeparatorLine();
            ui.println("|Doctor:|"+p.getDoctor().getLname()+" "+p.getDoctor().getFname());
            ui.printSeparatorLine();
            ui.println("|Diagnosis:| "+p.getDiagnosis());
            ui.printSeparatorLine();
            for(Item item : p.getMeds()){
                ui.println("|Medication:|"+item.getMed().getName());
                ui.println("|Amount:|"+item.getAmount());
                ui.println("|Description:|"+item.getDescription());
                ui.println("--------------");
            }
            ui.printSeparatorLine();
            ui.printSeparatorLine();
            ui.println("Prescribe?");
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getPrescriptionDAO().insert(p);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Prescription added to db");
                ui.printSeparatorLine();
                return;
            }
            ui.printSeparatorLine();
            ui.println("Couldn't add prescription to db");
            ui.printSeparatorLine();

        }

    }

    public void doctor(){
        Doctor doctor = new Doctor();
        while (true) {
            
            ui.println("Please enter first name");
            String input = ui.readInputString();
            if(input.equals("exit")) break;
            doctor.setFname(input);

            ui.printSeparatorLine();
            ui.println("Please enter last name");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            doctor.setLname(input);

            while(true){
                ui.printSeparatorLine();
                ui.println("Please date of first practice in format: yyyy-[m]m-[d]d");
                input = ui.readInputString();
                if(input.equals("exit")) break;
                try {
                    doctor.setStartedPractice(Date.valueOf(input));
                    break;
                } catch (Exception e) {
                    ui.println("Not a valid date input");
                }

                doctor.setLname(input);
            }

            ui.printSeparatorLine();
            ui.println("|Name:| "+doctor.getFname() + " " + doctor.getLname());
            ui.println("|Practicing since:| "+doctor.getStartedPractice());
            ui.println("Add doctor?");
            if(!ui.readInputBoolean()) continue;

            if(dataLayer.getDoctorDAO().insert(doctor)) {
                ui.printSeparatorLine();
                ui.println("Doctor succesfully added to db");
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldnt add doctor to db");
            ui.printSeparatorLine();
            

        }

    }

    public void patient(){
        Patient patient = new Patient();
        while (true) {
            
            ui.println("Please enter first name");
            String input = ui.readInputString();
            if(input.equals("exit")) break;
            patient.setFname(input);

            ui.printSeparatorLine();
            ui.println("Please enter last name");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            patient.setLname(input);

            ui.printSeparatorLine();
            ui.println("Please enter birth number");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            patient.setBirthNumber(input);

            ui.printSeparatorLine();
            ui.println("Male?");
            patient.setGender(ui.readInputBoolean());
            

            while(true){
                ui.printSeparatorLine();
                ui.println("Please enter date of birth in format: yyyy-[m]m-[d]d");
                input = ui.readInputString();
                if(input.equals("exit")) break;
                try {
                    patient.setDof(Date.valueOf(input));
                    break;
                } catch (Exception e) {
                    ui.println("Not a valid date input");
                }

                patient.setLname(input);
            }

            ui.printSeparatorLine();
            ui.println("Please enter insurance number");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            patient.setInsuranceNumber(input);

            List<InsuranceCompany> companies = dataLayer.getInsuranceDAO().getAll();
            print.insuranceCompanies(companies);
            ui.println("Please select insurance company");
            int numberInput = ui.readInputInt(1,companies.size())-1;
            if(numberInput == companies.size()) return;
            patient.setInsuranceCompany(companies.get(numberInput));


            ui.printSeparatorLine();
            ui.println("|Name:| "+patient.getFname() + " " + patient.getLname());
            ui.println("|Born:| "+patient.getDof());
            ui.println("|BirthNumber:| "+patient.getBirthNumber());
            ui.println("|InsuranceNumber:| "+patient.getInsuranceNumber());
            String gender = patient.isGender() ? "male" : "female";
            ui.println("|Gender:| "+gender);
            ui.println("|Insurance:| "+patient.getInsuranceShortcut());
            ui.println("Add patient?");
            if(!ui.readInputBoolean()) continue;

            if(dataLayer.getPatientDAO().insert(patient)) {
                ui.printSeparatorLine();
                ui.println("Patient succesfully added to db");
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldnt add patient to db");
            ui.printSeparatorLine();
            
        }
    }

    public void insuranceCompany(){
        InsuranceCompany insurance = new InsuranceCompany();
        while (true) {
            
            ui.println("Please company name");
            String input = ui.readInputString();
            if(input.equals("exit")) break;
            insurance.setName(input);

            ui.printSeparatorLine();
            ui.println("Please enter company shortcut");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            insurance.setShortcut(input);

            ui.printSeparatorLine();
            ui.println("Please enter country of origin");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            insurance.setCountryOfOrigin(input);

            ui.printSeparatorLine();
            ui.println("|Name:| "+insurance.getName());
            ui.println("|Shortcut:| "+insurance.getShortcut());
            ui.println("|Country of origin:| "+insurance.getCountryOfOrigin());
            ui.println("Add insurance?");

            if(!ui.readInputBoolean()) continue;

            if(dataLayer.getInsuranceDAO().insert(insurance)) {
                ui.printSeparatorLine();
                ui.println("Insurance Company succesfully added to db");
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldnt add insurance to db");
            ui.printSeparatorLine();
        }
    }

    public void medication(){
        Medication medication = new Medication();
        while (true) {
            
            ui.println("Enter medication name");
            String input = ui.readInputString();
            if(input.equals("exit")) break;
            medication.setName(input);

            ui.printSeparatorLine();
            ui.println("Enter short description");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            medication.setShortDescription(input);

            ui.printSeparatorLine();
            ui.println("Enter long description");
            input = ui.readInputString();
            if(input.equals("exit")) break;
            medication.setLongDescription(input);

            ui.printSeparatorLine();
            Medication.medType[] types = Medication.medType.values();
            for(int i = 0; i < types.length; i++){
                ui.println(i+1+") "+types[i]);
            }
            ui.println("Enter medication type");
            int numberInput = ui.readInputInt(1,types.length)-1;
            if(numberInput == types.length) continue;
            Medication.medType type = types[numberInput];
            medication.setType(type);

            ui.printSeparatorLine();
            ui.println("|Name:| "+medication.getName());
            ui.println("|Type:| "+medication.getType());
            ui.println("|Short description:| "+medication.getShortDescription());
            ui.println("Add medication?");

            if(!ui.readInputBoolean()) continue;

            if(dataLayer.getMedicationDAO().insert(medication)) {
                ui.printSeparatorLine();
                ui.println("Medication succesfully added to db");
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldnt add medication to db");
            ui.printSeparatorLine();
        }
    }
    
}
