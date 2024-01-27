package com.kuta.db.DAO.dbObjects;

import java.sql.Date;

public class Patient {

    private byte[] id;
    private String fname;
    private String lname;
    private String birthNumber;
    private Date dof;
    private boolean gender;



    public Patient(byte[] id, String fname, String lname, String birthNumber, Date dof, boolean gender) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.birthNumber = birthNumber;
        this.dof = dof;
        this.gender = gender;
    }

    public Patient(String fname, String lname, String birthNumber, Date dof, boolean gender) {
        this.fname = fname;
        this.lname = lname;
        this.birthNumber = birthNumber;
        this.dof = dof;
        this.gender = gender;
    }

    public Patient(){

    }


    public byte[] getId() {
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
    public Date getDof() {
        return dof;
    }
    public boolean isGender() {
        return gender;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public void setDof(Date dof) {
        this.dof = dof;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }



    
}
