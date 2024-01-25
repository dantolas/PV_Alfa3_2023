package com.kuta.db.DAO.dbObjects;

import java.time.LocalDate;

public class Patient {
    private String id;
    private String fname;
    private String lname;
    private String birthNumber;
    private LocalDate dof;
    private boolean gender;
    public String getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getBirthNumber() {
        return birthNumber;
    }
    public LocalDate getDof() {
        return dof;
    }
    public boolean isGender() {
        return gender;
    }



    
}
