package com.kuta.app;

import java.sql.Date;
import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.db.DataLayerAPI;
import com.kuta.ui.ConsoleUI;

/**
 * Bundles all the Update of the CRUD 
 */
public class Update {


    ConsoleUI ui;
    DataLayerAPI dataLayer;
    PrintList print;

    public Update(ConsoleUI ui, DataLayerAPI dataLayer,PrintList print){
        this.ui = ui;
        this.dataLayer = dataLayer;
        this.print = print;
    }

    public void medication(){
        List<Medication> meds= dataLayer.getMedicationDAO().getAll();
        if(meds.size() < 1){
            ui.println("No meds in database.");
            return;
        }
        while(true){
            print.meds(meds);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,meds.size())-1;
            if(numberInput == meds.size()) return;
            Medication med = meds.get(numberInput);
            while(true){
                ui.println("Enter medication name (Enter to skip)");
                String name = ui.readInputString();
                if(name.equals("exit")) return;
                if(!name.equals("")) med.setName(name);

                ui.println("Enter medication type");
                Medication.medType[] types = Medication.medType.values();
                for(int i = 0; i < types.length; i++){
                    ui.println(i+1+") "+types[i]);
                }
                numberInput = ui.readInputInt(1,types.length)-1;
                if(numberInput == types.length) continue;
                Medication.medType type = types[numberInput];
                med.setType(type);

                ui.println("Enter short description (Enter to skip)");
                String shortDesc= ui.readInputString();
                if(shortDesc.equals("exit")) return;
                if(!shortDesc.equals("")) med.setShortDescription(shortDesc);

                ui.println("Enter detailed description (Enter to skip)");
                String longDesc= ui.readInputString();
                if(longDesc.equals("exit")) return;
                if(!longDesc.equals("")) med.setLongDescription(longDesc);

                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("|Name|:"+med.getName());
                ui.println("|Shortcut|:"+med.getType());
                ui.println("|Description|:"+med.getShortDescription());
                ui.printSeparatorLine();
                ui.println("|Long Description|:"+med.getLongDescription());
                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("Save changes?");
                if(ui.readInputBoolean()) break;

            }
            boolean success = dataLayer.getMedicationDAO().update(med);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Medication sucesfully updated.||UUID:"+print.uuidToString(med.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't update medication.");
            ui.printSeparatorLine();
        }
    }

    public void insuranceCompany(){
        List<InsuranceCompany> companies = dataLayer.getInsuranceDAO().getAll();
        if(companies.size() < 1){
            ui.println("No companies in database.");
            return;
        }
        while(true){
            print.insuranceCompanies(companies);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,companies.size())-1;
            if(numberInput == companies.size()) return;
            InsuranceCompany company = companies.get(numberInput);
            while(true){
                ui.println("Enter Company name (Enter to skip)");
                String name = ui.readInputString();
                if(name.equals("exit")) return;
                if(!name.equals("")) company.setName(name);

                ui.println("Enter company shortcut");
                String shortcut = ui.readInputString();
                if(shortcut.equals("exit")) return;
                if(!shortcut.equals("")) company.setShortcut(name);

                ui.println("Enter country of origin (Enter to skip)");
                String cor= ui.readInputString();
                if(cor.equals("exit")) return;
                if(!cor.equals("")) company.setCountryOfOrigin(cor);

                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("|Name|:"+company.getName());
                ui.println("|Shortcut|:"+company.getShortcut());
                ui.println("|Country of origin|:"+company.getCountryOfOrigin());
                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("Save changes?");
                if(ui.readInputBoolean()) break;

            }
            boolean success = dataLayer.getInsuranceDAO().update(company);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Insurance company sucesfully updated.||UUID:"+print.uuidToString(company.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't update company.");
            ui.printSeparatorLine();
        }
    }

    public void patient(){
        List<Patient> patients = dataLayer.getPatientDAO().getAll();
        if(patients.size() < 1){
            ui.println("No patients in database.");
            return;
        }
        while(true){
            print.patients(patients);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,patients.size())-1;
            if(numberInput == patients.size()) return;
            Patient patient = patients.get(numberInput);
            while(true){
                ui.println("Enter first name (Enter to skip)");
                String fname = ui.readInputString();
                if(fname.equals("exit")) return;
                if(!fname.equals("")) patient.setFname(fname);

                ui.println("Enter last name (Enter to skip)");
                String lname = ui.readInputString();
                if(lname.equals("exit")) return;
                if(!lname.equals("")) patient.setLname(lname);

                ui.println("Enter gender - yes = male, no = female(Enter to skip)");
                boolean gender = ui.readInputBoolean();
                patient.setGender(gender);

                ui.println("Enter insurance number (Enter to skip)");
                String insuranceNumber = ui.readInputString();
                if(insuranceNumber.equals("exit")) return;
                if(!insuranceNumber.equals("")) patient.setInsuranceNumber(insuranceNumber);

                ui.println("Select insurance company");
                List<InsuranceCompany> companies = dataLayer.getInsuranceDAO().getAll();
                print.insuranceCompanies(companies);
                numberInput = ui.readInputInt(1,companies.size())-1;
                if(numberInput == companies.size()) return;
                patient.setInsuranceCompany(companies.get(numberInput));



                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("|First Name|:"+patient.getFname());
                ui.println("|Last Name|:"+patient.getLname());
                String genderString = "female";
                if(patient.isGender()) genderString = "male";
                ui.println("|Gender|:"+genderString);
                ui.println("|Insurance number|:"+patient.getInsuranceNumber());
                ui.println("|Birth number|:"+patient.getBirthNumber());
                ui.println("|Insurance Company|:"+patient.getInsuranceShortcut());
                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("Save changes?");
                if(ui.readInputBoolean()) break;

            }
            boolean success = dataLayer.getPatientDAO().update(patient);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Insurance patient sucesfully updated.||UUID:"+print.uuidToString(patient.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't update patient.");
            ui.printSeparatorLine();
        }
        
    }
    public void doctor(){
        List<Doctor> doctors = dataLayer.getDoctorDAO().getAll();
        if(doctors.size() < 1){
            ui.println("No doctors in database.");
            return;
        }
        while(true){
            print.doctors(doctors);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,doctors.size())-1;
            if(numberInput == doctors.size()) return;
            Doctor doctor = doctors.get(numberInput);
            while(true){
                ui.println("Enter first name (Enter to skip)");
                String fname = ui.readInputString();
                if(fname.equals("exit")) return;
                if(!fname.equals("")) doctor.setFname(fname);

                ui.println("Enter last name (Enter to skip)");
                String lname = ui.readInputString();
                if(lname.equals("exit")) return;
                if(!lname.equals("")) doctor.setFname(lname);

                while(true){
                    ui.println("Enter date of first practice format:yyyy-[m]m-[d]d (Enter to skip)");
                    String date = ui.readInputString();
                    if(date.equals("exit"))return;
                    if(date.equals("")) break;
                    try {
                        doctor.setStartedPractice(Date.valueOf(date));
                    } catch (Exception e) {
                        ui.println("Not a valid date");
                    }

                }

                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("|First name|:"+doctor.getFname());
                ui.println("|Last name|:"+doctor.getLname());
                ui.println("|Practicing since|:"+doctor.getStartedPractice());
                ui.printSeparatorLine();
                ui.printSeparatorLine();
                ui.println("Save changes?");
                if(ui.readInputBoolean()) break;

            }
            boolean success = dataLayer.getDoctorDAO().update(doctor);
            if(success) {
                ui.printSeparatorLine();
                ui.println("Doctor sucesfully updated.||UUID:"+print.uuidToString(doctor.getId()));
                ui.printSeparatorLine();
                return;
            }

            ui.printSeparatorLine();
            ui.println("Couldn't update doctor.");
            ui.printSeparatorLine();
        }
        
    }
    
}
