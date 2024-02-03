package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * ReadCommand
 */
public class ReadCommand implements CCommand{

    
    ConsoleUI ui;
    CommandHandler handler;

    SubCommand[] readFunctions = 
        {
            ()->{readPrescription();},
            ()->{readPatient();},
            ()->{readInsuranceCompany();},
            ()->{readMedication();},
            ()->{readDoctor();}
        };

    public ReadCommand(CommandHandler handler){
        this.handler = handler;
        this.ui = handler.getCui();
    }

    public void readPrescription(){
        handler.getAPI().read.prescription();
    }

    public void readPatient(){
        handler.getAPI().read.patient();
    }

    public void readInsuranceCompany(){
        handler.getAPI().read.insuranceCompany();
    }

    public void readMedication(){
        handler.getAPI().read.medication();
    }

    public void readDoctor(){
        handler.getAPI().read.doctor();
    }

    @Override
    public String getName() {
        return "read";
    }

    @Override
    public String getHelp() {
        return "Prints out all rows in database.\ndo: read";
    }

    @Override
    public void execute(String... args) {
        String list = 
        """
        |Choose the table|
        1) Prescription
        ----------------
        2) Patient
        ----------------
        3) Insurance Company
        ----------------
        4) Medication
        ----------------
        5) Doctor
        ----------------
        """;
        while(true){

            ui.print(list);
            ui.println(" ");
            ui.println("|exit|");
            ui.printSeparatorLine();
            ui.println("@tasks/read");
            int numberInput = ui.readInputInt(1,5)-1;
            if(numberInput >= 5) return;


            readFunctions[numberInput].work();
        }
    }
}
