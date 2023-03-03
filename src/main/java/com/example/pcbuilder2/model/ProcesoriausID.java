package com.example.pcbuilder2.model;

public class ProcesoriausID {
    private static final ProcesoriausID instance = new ProcesoriausID();
    private int procesoriausID;
    private ProcesoriausID(){}
    public static ProcesoriausID getInstance(){
        return instance;
    }
    public int getID(){
        return procesoriausID;
    }
    public void setID(int id){
        this.procesoriausID = id;
    }
}
