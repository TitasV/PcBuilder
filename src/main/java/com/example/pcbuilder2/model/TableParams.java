package com.example.pcbuilder2.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.TextAlignment;

public class TableParams {
    private String gamintojas;
    private String modelis;
    private String procesoriausJungtis;
    private int procesoriausDaznis;
    private double kaina;

    public TableParams(String gamintojas, String modelis, String procesoriausJungtis, int procesoriausDaznis, double kaina) {
        this.gamintojas = gamintojas;
        this.modelis = modelis;
        this.procesoriausJungtis = procesoriausJungtis;
        this.procesoriausDaznis = procesoriausDaznis;
        this.kaina = kaina;
    }

    public String getGamintojas() {
        return gamintojas;
    }

    public void setGamintojas(String gamintojas) {
        this.gamintojas = gamintojas;
    }

    public String getModelis() {
        return modelis;
    }

    public void setModelis(String modelis) {
        this.modelis = modelis;
    }

    public String getProcesoriausJungtis() {
        return procesoriausJungtis;
    }

    public void setProcesoriausJungtis(String procesoriausJungtis) {
        this.procesoriausJungtis = procesoriausJungtis;
    }

    public int getProcesoriausDaznis() {
        return procesoriausDaznis;
    }

    public void setProcesoriausDaznis(int procesoriausDaznis) {
        this.procesoriausDaznis = procesoriausDaznis;
    }

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }
}
