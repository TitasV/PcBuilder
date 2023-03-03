package com.example.pcbuilder2.model;

public class KompiuterisID {
    private static final KompiuterisID instance = new KompiuterisID();
    private int kompiuterioID;
    private KompiuterisID(){}
    public static KompiuterisID getInstance(){
        return instance;
    }
    public int getID(){
        return kompiuterioID;
    }
    public void setID(int id){
        this.kompiuterioID = id;
    }
}
