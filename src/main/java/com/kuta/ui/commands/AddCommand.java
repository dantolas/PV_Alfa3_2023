package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * AddCommand
 */
public class AddCommand implements CCommand{

    ConsoleUI ui;
    CommandHandler handler;

    SubCommand[] addFunctions = 
        {
            ()->{addPrescription();},
            ()->{addPatient();},
            ()->{addInsuranceCompany();},
            ()->{addMedication();},
            ()->{addDoctor();}
        };

    public AddCommand(CommandHandler handler){
        this.handler = handler;
        this.ui = handler.getCui();
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getHelp() {
        return 
        """
        You can write and add records to database, like new prescriptions, doctors etc.
        \ndo: add
        """;
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
            ui.println("@tasks/add");
            int numberInput = ui.readInputInt(1,5)-1;
            if(numberInput >= 5) return;


            addFunctions[numberInput].work();
        }
    }

    public void addPrescription(){
        handler.getAPI().add.prescription();
    }

    public void addPatient(){
        handler.getAPI().add.patient();
    }

    public void addInsuranceCompany(){
        handler.getAPI().add.insuranceCompany();
    }

    public void addMedication(){
        handler.getAPI().add.medication();

    }

    public void addDoctor(){
        handler.getAPI().add.doctor();

    }

    
}
