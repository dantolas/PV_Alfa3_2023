package com.kuta.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HexFormat;
import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.app.objectTemplates.InsuranceCompany;
import com.kuta.app.objectTemplates.Medication;
import com.kuta.app.objectTemplates.Patient;
import com.kuta.app.objectTemplates.Prescription;
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

    public Add add; 
    public Delete delete;
    public Update update;
    public PrintList print;

    public void run(){
        ui.printSeparatorLine();
        ui.printSeparatorLine();
        ui.println(ui.WELCOME_MESSAGE);
        ui.printSeparatorLine();
        ui.printSeparatorLine();
        ui.println("|AVAILABLE TASKS|");
        while(true){
            commandHandler.command("help").execute();
            try {
                ui.print("@tasks/");
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
        app.print = new PrintList(ui);
        app.delete = new Delete(ui,dataLayer,app.print);
        handler.setAppLayer(app);
        return app;

    }

}
