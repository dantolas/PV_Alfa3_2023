package com.kuta.ui.commands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;

import com.kuta.ui.CommandHandler;

/**
 * Report
 */
public class ReportCommand implements CCommand{



    public ReportCommand(CommandHandler handler) {
        this.handler = handler;
    }

    CommandHandler handler;

    SubCommand[] reportFunctions= 
        {
            ()->{try {
                prescriptionSummary();
            } catch (SQLException e) {
                handler.getAPI().errorHandler.handle(e);
            } catch (FileAlreadyExistsException e) {
                handler.getAPI().errorHandler.handle(e);
            } catch (SecurityException e) {
                handler.getAPI().errorHandler.handle(e);
            } catch (IOException e) {
                handler.getAPI().errorHandler.handle(e);
            }},
            ()->{try {
                prescriptionsHandedOut();
            } catch (SecurityException | IOException | SQLException e) {
                handler.getAPI().errorHandler.handle(e);
            }},
            ()->{try {
                insurancePatients();
            } catch (SecurityException | SQLException | IOException e) {
                handler.getAPI().errorHandler.handle(e);
            }},
        };
    @Override
    public String getName() {
        return "report";
    }

    @Override
    public String getHelp() {
        return 
        """
        Generate a data report in markdown(.md) format.
        \ndo: report
        """;
    }

    @Override
    public void execute(String... args) {
        String list = 
        """
        |Choose the report|
        1) Prescription Summary
        ----------------
        2) Prescriptions handed out 
        ----------------
        3) Insurance patients 
        ----------------
        """;
        while(true){
            handler.getCui().println(list);

            int numberInput = handler.getCui().readInputInt(1,3)-1;
            if(numberInput == 3) return;
            reportFunctions[numberInput].work();
        }
    }

    public void prescriptionSummary() throws SQLException, FileAlreadyExistsException, SecurityException, IOException{
        handler.getAPI().report.prescSumm();
    }

    public void insurancePatients() throws FileAlreadyExistsException, SecurityException, SQLException, IOException{
        handler.getAPI().report.prescHandedOut();
    }

    public void prescriptionsHandedOut() throws FileAlreadyExistsException, SecurityException, IOException, SQLException{
        handler.getAPI().report.insurancePatients();
    }

    
}
