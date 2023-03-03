package com.example.pcbuilder2.model;

public class KompiuterisTable {
    String procesorius;
    String ausintuvas;
    String plokste;
    String ram;
    String ventiliatorius;
    String diskas;
    String gpu;
    String psu;
    String korpusas;

    public KompiuterisTable(String procesorius, String ausintuvas, String plokste, String ram, String ventiliatorius, String diskas, String gpu, String psu, String korpusas) {
        this.procesorius = procesorius;
        this.ausintuvas = ausintuvas;
        this.plokste = plokste;
        this.ram = ram;
        this.ventiliatorius = ventiliatorius;
        this.diskas = diskas;
        this.gpu = gpu;
        this.psu = psu;
        this.korpusas = korpusas;
    }

    public String getProcesorius() {
        return procesorius;
    }

    public void setProcesorius(String procesorius) {
        this.procesorius = procesorius;
    }

    public String getAusintuvas() {
        return ausintuvas;
    }

    public void setAusintuvas(String ausintuvas) {
        this.ausintuvas = ausintuvas;
    }

    public String getPlokste() {
        return plokste;
    }

    public void setPlokste(String plokste) {
        this.plokste = plokste;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getVentiliatorius() {
        return ventiliatorius;
    }

    public void setVentiliatorius(String ventiliatorius) {
        this.ventiliatorius = ventiliatorius;
    }

    public String getDiskas() {
        return diskas;
    }

    public void setDiskas(String diskas) {
        this.diskas = diskas;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getPsu() {
        return psu;
    }

    public void setPsu(String psu) {
        this.psu = psu;
    }

    public String getKorpusas() {
        return korpusas;
    }

    public void setKorpusas(String korpusas) {
        this.korpusas = korpusas;
    }
}
