package com.orion.model;

/**
 * Created by Vova on 18.08.2017.
 */
public enum ApplicantStatus {

    NEW("NEW"), DEPARTED("DEPARTED"), NOTDEPARTED("NOTDEPARTED");

    private String name;

    ApplicantStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicantStatus valueOf(int id){
        return ApplicantStatus.values()[id];
    }
    /*public static ApplicantStatus getEnum(String value) {
        for(ApplicantStatus v : values())
            if(v.getName().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }*/
}
