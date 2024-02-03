package com.kuta.app;

import java.util.HexFormat;
import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.app.objectTemplates.Prescription;
import com.kuta.ui.ConsoleUI;

/**
 * PrintList
 */
public class PrintList {

    ConsoleUI ui;
    
    public PrintList(ConsoleUI ui) {
        this.ui = ui;
    }



    public void doctors(List<Doctor> doctors){
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < doctors.size(); i++){
            listString.append(i+1+") ");
            Doctor d = doctors.get(i);
            listString.append(d.getFname()+" ");
            listString.append(d.getLname()+" ");
            listString.append(" | Practiced since:"+d.getStartedPractice());
            listString.append(" || UUID:"+uuidToString(d.getId()));

            listString.append("\n");
        }
        ui.println(listString.toString());
    }

    

    public void patients(List<Patient> patients){
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < patients.size(); i++){
            listString.append(i+1+") ");
            Patient p = patients.get(i);
            listString.append(p.getFname()+" ");
            listString.append(p.getLname()+" ");
            listString.append(" | Birth number:"+p.getBirthNumber() + "| ");
            if(p.isGender()) listString.append(" male ");
            else listString.append(" female ");
            listString.append(" | dof:"+p.getDof().toString());
            listString.append("| insurance:"+p.getInsuranceShortcut());
            listString.append(" || UUID:"+uuidToString(p.getId()));
            
            listString.append("\n");
        }
        ui.println(listString.toString());
    }

    public void insuranceCompanies(List<InsuranceCompany> companies){
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < companies.size(); i++){
            listString.append(i+1+") ");
            InsuranceCompany c = companies.get(i);
            listString.append("|Name|:"+c.getName());
            listString.append("\n|Shortcut|:"+c.getShortcut());
            listString.append("\n|Origin country|:"+c.getCountryOfOrigin());
            listString.append("\n|| UUID:"+uuidToString(c.getId()));
            
            listString.append("\n");
            ui.printSeparatorLine();
        }
        ui.println(listString.toString());
    }

    public void meds(List<Medication> meds){

        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < meds.size(); i++){
            listString.append(i+1+") ");
            Medication m = meds.get(i);
            listString.append(m.getName()+" ");
            listString.append("Type:"+m.getType());
            listString.append("|| UUID:"+uuidToString(m.getId()));
            
            listString.append("\n");
            ui.printSeparatorLine();
        }
        ui.println(listString.toString());
    }

    public  void prescriptions(List<Prescription> prescriptions){

        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < prescriptions.size(); i++){
            listString.append(i+1+") ");
            Prescription p = prescriptions.get(i);
            listString.append("|| UUID:"+uuidToString(p.getId()));
            listString.append("\n|Date|:"+p.getDatePrescribed());
            listString.append("\n|Prescriped by|:"+p.getDoctor().getLname()+" "+p.getDoctor().getFname());
            listString.append("\n|Patient|:"+p.getPatient().getFname()+" "+p.getPatient().getLname()+" "+p.getPatient().getBirthNumber());
            listString.append("\n|Insurance|:"+p.getPatient().getInsuranceNumber()+" "+p.getPatient().getInsuranceShortcut());
            listString.append("\n|Items|");
            for (Prescription.Item item: p.getMeds()) {
                listString.append("\n-----------------------");
                listString.append("\n"+item.getMed().getName());
                listString.append(" | Amount: "+item.getAmount());
                listString.append("\n=> Insurance covered:");
                if(item.isInsuranceCovered()){
                    listString.append("Yes");
                }else{
                    listString.append("No");
                }
                listString.append("\n");
            }
            listString.append("\n");
        ui.printSeparatorLine();
        }
        ui.println(listString.toString());
        ui.printSeparatorLine();
    }
    public String uuidToString(byte[] uuid){
        return HexFormat.of().formatHex(uuid);
    }
}
