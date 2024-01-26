package com.kuta.db.DAO.dbObjects;

import java.sql.Date;

/**
 * Doctor
 */
public class Doctor {

    public Doctor(String id, String fname, String lname, Date startedPractice) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.startedPractice = startedPractice;
    }
    private String id;
    private String fname;
    private String lname;
    private Date startedPractice;

    public String getId() {
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
    
}
