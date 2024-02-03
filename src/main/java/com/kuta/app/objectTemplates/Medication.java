package com.kuta.app.objectTemplates;

/**
 * Medication
 */
public class Medication {

    public static enum medType{
        pills,
        tablets,
        syrup,
        ointment,
        herbs
    }

    private byte[] id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private medType type;

    public Medication(){}


    public Medication(String name, String shortDescription, String longDescription, medType type) {
        this.name = name;
        if(shortDescription.length() > 500) shortDescription = shortDescription.substring(0,499);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.type = type;
    }
    public Medication(byte[] id, String name, String shortDescription, String longDescription, medType type) {
        this.id = id;
        this.name = name;
        if(shortDescription.length() > 500) shortDescription = shortDescription.substring(0,499);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.type = type;
    }
    public byte[] getId() {
        return id;
    }
    public void setId(byte[] id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        if(shortDescription.length() > 500) shortDescription = shortDescription.substring(0,499);
        this.shortDescription = shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    public medType getType() {
        return type;
    }
    public void setType(medType type) {
        this.type = type;
    };


}
