package com.kuta.app;

import com.kuta.db.DataLayerAPI;
import com.kuta.ui.ConsoleUI;

/**
 * Read
 */
public class Read {

    ConsoleUI ui;
    DataLayerAPI dataLayer;
    PrintList print;

    public Read(ConsoleUI ui, DataLayerAPI dataLayer,PrintList print){
        this.ui = ui;
        this.dataLayer = dataLayer;
        this.print = print;
    }

    public void patient(){
        print.patients(dataLayer.getPatientDAO().getAll());
    }

    public void doctor(){
        print.doctors(dataLayer.getDoctorDAO().getAll());
    }
    public void medication(){
        print.meds(dataLayer.getMedicationDAO().getAll());
    }
    public void insuranceCompany(){
        print.insuranceCompanies(dataLayer.getInsuranceDAO().getAll());
    }
    public void prescription(){
        print.prescriptions(dataLayer.getPrescriptionDAO().getAll());
    }
    
}
