package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * DeleteCommand
 */
public class DeleteCommand implements CCommand{

    ConsoleUI ui;
    CommandHandler handler;

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
        ui.print(list);
        ui.printSeparatorLine();

        while(true){
            int numberInput = ui.readInputInt();
            if(numberInput > 0) break;
        }

        SubCommand[] subs = {
        };
    }

    public void deletePrescription(){

    }

    public void deleteDoctor(){
        handler.getAPI().printDoctorsList();


    }

    public void deleteInsuranceCompany(){

    }

    public void deleteMedication(){

    }

    public void deletePatient(){

    }

}
