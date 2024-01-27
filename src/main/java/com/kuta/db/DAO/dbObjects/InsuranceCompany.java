package com.kuta.db.DAO.dbObjects;

/**
 * InsuranceCompany
 */
public class InsuranceCompany {

    
    private byte[] id;
    private String name;
    private int uniqueNumber;
    private String shortcut;
    public byte[] getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getUniqueNumber() {
        return uniqueNumber;
    }
    public String getShortcut() {
        return shortcut;
    }

}
