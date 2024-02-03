package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * UpdateCommand
 */
public class UpdateCommand implements CCommand{


    ConsoleUI ui;
    CommandHandler handler;

    SubCommand[] updateFunctions= 
        {
            ()->{updatePatient();},
            ()->{updateInsuranceCompany();},
            ()->{updateMedication();},
            ()->{updateDoctor();}
        };

    public UpdateCommand(CommandHandler handler){
        this.handler = handler;
        this.ui = handler.getCui();
    }
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getHelp() {
        return 
        """
        Update a record in database.
        do: update
        """;
    }

    @Override
    public void execute(String... args) {
        String list = 
        """
        |Choose the table|
        1) Patient
        ----------------
        2) Insurance Company
        ----------------
        3) Medication
        ----------------
        4) Doctor
        ----------------
        """;
        while(true){

            ui.print(list);
            ui.println(" ");
            ui.println("|exit|");
            ui.printSeparatorLine();
            ui.println("@tasks/update");
            int numberInput = ui.readInputInt(1,4)-1;
            if(numberInput >= 4) return;


            updateFunctions[numberInput].work();
        }
    }

    public void updateDoctor(){
        handler.getAPI().update.doctor();
    }

    public void updateInsuranceCompany(){
        handler.getAPI().update.insuranceCompany();
    }

    public void updateMedication(){
        handler.getAPI().update.medication();
    }

    public void updatePatient(){
        handler.getAPI().update.patient();
    }

    
}
