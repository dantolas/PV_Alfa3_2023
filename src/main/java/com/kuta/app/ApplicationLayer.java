package com.kuta.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HexFormat;
import java.util.List;

import com.kuta.app.dbObjects.Doctor;
import com.kuta.app.dbObjects.Patient;
import com.kuta.db.DataLayerAPI;
import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * ApplicationLayer
 */
public class ApplicationLayer {

    ConsoleUI ui;
    CommandHandler commandHandler;
    DataLayerAPI dataLayer;

    public void run(){
        ui.printSeparatorLine();
        ui.printSeparatorLine();
        ui.println(ui.WELCOME_MESSAGE);
        ui.printSeparatorLine();
        ui.printSeparatorLine();
        ui.println("|AVAILABLE TASKS|");
        commandHandler.command("help").execute();
        while(true){
            try {
                ui.print("@welcome/");
                String input = ui.readInputString();
                if(input.toLowerCase().equals("exit")) break;
                if(input.toLowerCase().equals("")) continue;
                commandHandler.handle(input);
                
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        ui.println(ui.GOODBYE_MESSAGE);

    }

    public void deletePatient(){
        List<Patient> patients = dataLayer.getPatientDAO().getAll();
        if(patients.size() < 1){
            ui.println("No patients in database.");
            return;
        }
        while(true){
            printPatientsList(patients);
            ui.println("\n|exit|");

            int numberInput = ui.readInputInt(1,patients.size())-1;
            System.out.println("Input number = "+numberInput+" equals MIN_VALUE:"+(numberInput==Math.abs(Integer.MIN_VALUE)));
            if(numberInput == Integer.MIN_VALUE) return;
            Patient patient = patients.get(numberInput);
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getPatientDAO().delete(patient);
            if(success) {
                ui.println("Patient "+patient.getFname()+" "+patient.getLname()+" sucesfully deleted.");
                return;
            }

            ui.println("Couldn't delete patient.");
        }
        
    }
    public void deleteDoctor(){
        List<Doctor> doctors = dataLayer.getDoctorDAO().getAll();
        if(doctors.size() < 1){
            ui.println("No doctors in database.");
            return;
        }
        while(true){

            printDoctorsList(doctors);
            ui.println("\n|exit|");
            int numberInput = ui.readInputInt(1,doctors.size())-1;
            if(numberInput == Integer.MIN_VALUE) return;
            Doctor doctor = doctors.get(numberInput);
            if(!ui.readInputBoolean()) continue;
            boolean success = dataLayer.getDoctorDAO().delete(doctor);
            if(success) {
                ui.println("Doctor "+doctor.getFname()+" "+doctor.getLname()+" sucesfully deleted.");
                return;
            }
            ui.println("Couldn't delete doctor.");
        }

        
    }

    public void printDoctorsList(List<Doctor> doctors){
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < doctors.size(); i++){
            listString.append(i+1+") ");
            Doctor d = doctors.get(i);
            listString.append(d.getFname()+" ");
            listString.append(d.getLname()+" ");
            listString.append("practicing since:"+d.getStartedPractice());
            listString.append(" || UUID:"+uuidToString(d.getId()));

            listString.append("\n");
        }
        ui.println(listString.toString());
    }

    

    public void printPatientsList(List<Patient> patients){
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < patients.size(); i++){
            listString.append(i+1+") ");
            Patient p = patients.get(i);
            listString.append(p.getFname()+" ");
            listString.append(p.getLname()+" ");
            listString.append("birth number:"+p.getBirthNumber());
            if(p.isGender()) listString.append(" male ");
            else listString.append(" female ");
            listString.append("dof:"+p.getDof().toString());
            listString.append(" insurance:"+p.getInsuranceShortcut());
            listString.append(" || UUID:"+uuidToString(p.getId()));
            
            listString.append("\n");
        }
        ui.println(listString.toString());
    }

    public void printInsuranceCompaniesList(){

    }

    public void printMedicationsList(){

    }

    public void printPrescriptionList(){

    }

    public ApplicationLayer setConsoleUI(ConsoleUI ui){
        this.ui = ui;
        return this;
    }
    public ApplicationLayer setCommandHandler(CommandHandler handler){
        this.commandHandler = handler;
        return this;
    }
    public ApplicationLayer setDataLayer(DataLayerAPI dataLayer){
        this.dataLayer = dataLayer;
        return this;
    }

    public final static ApplicationLayer DEFAULT_INIT(PrintStream output,InputStream input){
        ConsoleUI ui = new ConsoleUI("=",20,output,input);
        CommandHandler handler = new CommandHandler().setUi(ui).addDefaultCommands();
        DataLayerAPI dataLayer = DataLayerAPI.createDefault();
        ApplicationLayer app = new ApplicationLayer().setConsoleUI(ui).setCommandHandler(handler).setDataLayer(dataLayer);
        handler.setAppLayer(app);
        return app;

    }

    private String uuidToString(byte[] uuid){
        return HexFormat.of().formatHex(uuid);
    }

    
}
