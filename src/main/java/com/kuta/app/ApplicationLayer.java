package com.kuta.app;

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
        ui.println(ui.WELCOME_MESSAGE);
        ui.printSeparatorLine();
        while(true){
            try {
                String input = ui.readInputString();
                commandHandler.handle(input);
                
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

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

    public final static ApplicationLayer DEFAULT_INIT(){
        ConsoleUI ui = new ConsoleUI("=",10,System.out,System.in);
        CommandHandler handler = new CommandHandler().setUi(ui);
        DataLayerAPI dataLayer = new DataLayerAPI();
        ApplicationLayer app = new ApplicationLayer().setConsoleUI(ui).setCommandHandler(handler).setDataLayer(dataLayer);
        return app;

    }
    
}
