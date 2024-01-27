package com.kuta.db.DAO.dbObjects;

/**
 * Medication
 */
public class Medication {

    public Medication(String name, String shortDescription,String longDescription, String type) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.type = type;
    }
    public Medication(byte[] id, String name, String shortDescription,String longDescription, String type) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.type = type;
    }
    private byte[] id;
    private String name;
    private String shortDescription;
    private String longDescription;
    public void setId(byte[] id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    private String type;
    public byte[] getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public String getType() {
        return type;
    }

    
}
