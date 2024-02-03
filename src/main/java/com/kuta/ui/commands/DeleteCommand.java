package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * DeleteCommand
 */
public class DeleteCommand implements CCommand{


    ConsoleUI ui;
    CommandHandler handler;
    SubCommand[] deleteFunctions = 
        {
            ()->{deletePrescription();},
            ()->{deletePatient();},
            ()->{deleteInsuranceCompany();},
            ()->{deleteMedication();},
            ()->{deleteDoctor();}
        };

    public DeleteCommand(CommandHandler handler){
        this.handler = handler;
        this.ui = handler.getCui();
    }
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getHelp() {
        return 
        """
        Delete a record from database.
        do: delete
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
            ui.println("@tasks/delete");
            int numberInput = ui.readInputInt(1,5)-1;
            if(numberInput >= 5) return;


            deleteFunctions[numberInput].work();
        }
    }

    public void deletePrescription(){
        handler.getAPI().delete.prescription();
    }

    public void deleteDoctor(){
        handler.getAPI().delete.doctor();
    }

    public void deleteInsuranceCompany(){
        handler.getAPI().delete.insuranceCompany();
    }

    public void deleteMedication(){
        handler.getAPI().delete.medication();
    }

    public void deletePatient(){
        handler.getAPI().delete.patient();
    }

}
