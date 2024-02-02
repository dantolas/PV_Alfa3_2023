package com.kuta.app.dbObjects;

/**
 * InsuranceCompany
 */
public class InsuranceCompany {

    
    private byte[] id;
    private String name;
    private String countryOfOrigin;
    private String shortcut;




    public InsuranceCompany(String name, String countryOfOrigin, String shortcut) {
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.shortcut = shortcut;
    }
    public InsuranceCompany(byte[] id, String name, String countryOfOrigin, String shortcut) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.shortcut = shortcut;
    }
    public byte[] getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    public String getShortcut() {
        return shortcut;
    }
    public void setId(byte[] id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

}