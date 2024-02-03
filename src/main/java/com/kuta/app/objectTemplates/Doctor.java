package com.kuta.app.objectTemplates;

import java.sql.Date;

/**
 * Doctor
 * Object model representing the db table
 */
public class Doctor {

    public Doctor(byte[] id, String fname, String lname, Date startedPractice) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.startedPractice = startedPractice;
    }
    public Doctor(){}

    private byte[] id;
    private String fname;
    private String lname;
    private Date startedPractice;

    public byte[] getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public Date getStartedPractice() {
        return startedPractice;
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
    public void setStartedPractice(Date startedPractice) {
        this.startedPractice = startedPractice;
    }
    
}
