package com.kuta.app;

import java.io.InputStream;
import java.io.PrintStream;

import com.kuta.db.DataLayerAPI;
import com.kuta.ui.CommandHandler;
import com.kuta.ui.ConsoleUI;

/**
 * Serves as the bundler for the application tier of the app. 
 * Serves as the link between the data and inteface layers.
 * Provides functionality for data manipulation, data extraction,
 * report generation and more.
 *
 * Should be initialized with method chaining.
 */
public class ApplicationLayer {

    ConsoleUI ui;
    CommandHandler commandHandler;
    DataLayerAPI dataLayer;

    public Add add; 
    public Delete delete;
    public Update update;
    public PrintList print;
    public Read read;

    /**
     * This is the main application loop run on startup
     */
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

    /**
     * @param ui
     * @return
     */
    public ApplicationLayer setConsoleUI(ConsoleUI ui){
        this.ui = ui;
        return this;
    }
    /**
     * @param handler
     * @return
     */
    public ApplicationLayer setCommandHandler(CommandHandler handler){
        this.commandHandler = handler;
        return this;
    }
    /**
     * @param dataLayer
     * @return
     */
    public ApplicationLayer setDataLayer(DataLayerAPI dataLayer){
        this.dataLayer = dataLayer;
        return this;
    }
    

    /**
     * Creates a defaul ApplicationLayer with preset parameters.
     * @param output Client output stream, usually System.out
     * @param input Client input stream, usually System.in
     * @return Defaul ApplicationLayer
     */
    public final static ApplicationLayer DEFAULT_INIT(PrintStream output,InputStream input){
        ConsoleUI ui = new ConsoleUI("=",20,output,input);
        CommandHandler handler = new CommandHandler().setUi(ui).addDefaultCommands();
        DataLayerAPI dataLayer = DataLayerAPI.createDefault();
        ApplicationLayer app = new ApplicationLayer().setConsoleUI(ui).setCommandHandler(handler).setDataLayer(dataLayer);
        app.print = new PrintList(ui);
        app.delete = new Delete(ui,dataLayer,app.print);
        app.update = new Update(ui,dataLayer,app.print);
        app.add = new Add(ui,dataLayer,app.print);
        app.read = new Read(ui,dataLayer,app.print);
        handler.setAppLayer(app);
        return app;
    }
}
