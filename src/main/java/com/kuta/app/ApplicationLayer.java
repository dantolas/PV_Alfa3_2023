package com.kuta.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HexFormat;
import java.util.List;

import com.kuta.db.DataLayerAPI;
import com.kuta.db.DAO.dbObjects.Doctor;
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
        ui.println(ui.WELCOME_MESSAGE);
        ui.printSeparatorLine();
        while(true){
            try {
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


    public void printDoctorsList(){
        StringBuilder listString = new StringBuilder();
        List<Doctor> doctors = dataLayer.getDoctorDAO().getAll();
        for(int i = 0; i < doctors.size(); i++){
            listString.append(i+") ");
            Doctor d = doctors.get(i);
            listString.append(d.getFname()+" ");
            listString.append(d.getLname()+" ");
            listString.append("practicing since:"+d.getStartedPractice());
            listString.append(" || UUID:"+uuidToString(d.getId()));
            listString.append("\n");
        }
        ui.println(listString.toString());
    }

    

    public void printPatientsList(){

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
        ConsoleUI ui = new ConsoleUI("=",10,output,input);
        CommandHandler handler = new CommandHandler().setUi(ui).addDefaultCommands();
        DataLayerAPI dataLayer = new DataLayerAPI();
        ApplicationLayer app = new ApplicationLayer().setConsoleUI(ui).setCommandHandler(handler).setDataLayer(dataLayer);
        return app;

    }

    private String uuidToString(byte[] uuid){
        return HexFormat.of().formatHex(uuid);
    }

    
}
