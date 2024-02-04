package com.kuta.app;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;

import com.kuta.db.DataLayerAPI;
import com.kuta.ui.ConsoleUI;
import com.kuta.util.IO;

/**
 * Report
 */
public class Report {

    private final String userDir = System.getProperty("user.dir");
    ConsoleUI ui;
    DataLayerAPI dataLayer;


    public Report(ConsoleUI ui, DataLayerAPI dataLayer) {
        this.ui = ui;
        this.dataLayer = dataLayer;
    }

    public void prescSumm() throws SQLException, FileAlreadyExistsException, SecurityException, IOException{
        String report = dataLayer.getPrescSummReport(); 
        String filepath = userDir+"/reports/Prescription_Summary_Report.md"; 
        if(!IO.isFile(filepath)){
            IO.createFile(filepath);
        }
        IO.overWriteFile(report,filepath);
        ui.printSeparatorLine();
        ui.println("Report generated in /reports");
        ui.printSeparatorLine();
    }

    public void insurancePatients() throws FileAlreadyExistsException, SecurityException, IOException, SQLException{

        String report = dataLayer.getInsurancePatientsReport(); 
        String filepath = userDir+"/reports/Insurance_Patients_Report.md"; 
        if(!IO.isFile(filepath)){
            IO.createFile(filepath);
        }
        IO.overWriteFile(report,filepath);
        ui.printSeparatorLine();
        ui.println("Report generated in /reports");
        ui.printSeparatorLine();
    }

    public void prescHandedOut() throws SQLException, FileAlreadyExistsException, SecurityException, IOException{

        String report = dataLayer.getPrescHandedOutReport(); 
        String filepath = userDir+"/reports/Prescriptions_Handed_Report.md"; 
        if(!IO.isFile(filepath)){
            IO.createFile(filepath);
        }
        IO.overWriteFile(report,filepath);
        ui.printSeparatorLine();
        ui.println("Report generated in /reports");
        ui.printSeparatorLine();
    }
}
