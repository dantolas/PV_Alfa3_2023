package com.kuta.db.DAO.dbObjects;

import java.sql.Date;

public class Patient {

    private byte[] id;
    private String fname;
    private String lname;
    private InsuranceCompany insuranceCompany;
    private String insuranceNumber;
    private String birthNumber;
    private Date dof;
    private boolean gender;


    public Patient(){}

    public Patient(String fname, String lname, InsuranceCompany insuranceCompany, String insuranceNumber,
            String birthNumber, Date dof, boolean gender) {
        this.fname = fname;
        this.lname = lname;
        this.insuranceCompany = insuranceCompany;
        this.insuranceNumber = insuranceNumber;
        this.birthNumber = birthNumber;
        this.dof = dof;
        this.gender = gender;
    }
    public Patient(byte[] id, String fname, String lname, InsuranceCompany insuranceCompany, String insuranceNumber,
            String birthNumber, Date dof, boolean gender) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.insuranceCompany = insuranceCompany;
        this.insuranceNumber = insuranceNumber;
        this.birthNumber = birthNumber;
        this.dof = dof;
        this.gender = gender;
    }
    public byte[] getId() {
        return id;
    }
    public void setId(byte[] id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getInsuranceName() {
        return insuranceCompany.getName();
    }
    public String getInsuranceShortcut(){
        return insuranceCompany.getShortcut();
    }
    public String getInsuranceOrigin(){
        return insuranceCompany.getCountryOfOrigin();
    }
    public byte[] getInsuranceId(){
        return insuranceCompany.getId();
    }
    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
    public String getInsuranceNumber() {
        return insuranceNumber;
    }
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
    public String getBirthNumber() {
        return birthNumber;
    }
    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }
    public Date getDof() {
        return dof;
    }
    public void setDof(Date dof) {
        this.dof = dof;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }


}
