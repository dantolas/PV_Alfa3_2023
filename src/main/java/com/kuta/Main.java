package com.kuta;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HexFormat;

import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.DAOs.PatientDAO;
import com.kuta.db.DAO.dbObjects.Patient;


public class Main{

    public static void main(String[] args) {

        Config config = null;

        try{
            config = Config.createFromFile("/home/charming/Projects/code/pv/java/alfa3/conf/config.json");
            System.out.println(config);
        }catch(Exception E){
            System.out.println("Config exception");
            
        }

        DatabaseConnector.init(config.db.host,config.db.name,config.db.username,config.db.password);
        System.out.println("Connected:"+DatabaseConnector.testConnection());


        Patient[] patients = {
            new Patient("Josef","Doubravsky","333432/2222",Date.valueOf("2002-11-11"),true),
            new Patient("Jan","Morava","311122/2222",Date.valueOf("2000-5-30"),true),
            new Patient("Pavel","Otava","355522/2222",Date.valueOf("1999-12-11"),true),
            new Patient("Konich","Ugy","0293840/2222",Date.valueOf("1977-1-3"),true),
            new Patient("Jana","Kotova","93029/2222",Date.valueOf("2004-11-11"),false),
            new Patient("Lada","Kovovova","94859/2222",Date.valueOf("1988-11-11"),false),
            new Patient("Jarmila","Stozarova","012122/2222",Date.valueOf("1966-11-11"),false),
        };

        PatientDAO pdao = new PatientDAO();

        ArrayList<Patient> getList = new ArrayList<>(pdao.getAll());

        Patient updatePatient = getList.get(0);
        updatePatient.setId(HexFormat.of().parseHex("0C7B054ABCF111EE92278C1759ECD7D1"));
        updatePatient.setBirthNumber("444555444/4445");
        System.out.println(pdao.getByUUID(updatePatient.getId()).getFname());
        

            
    }
    
}
