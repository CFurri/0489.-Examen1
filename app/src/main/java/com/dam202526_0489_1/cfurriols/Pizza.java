package com.dam202526_0489_1.cfurriols;

import java.io.Serializable;

public class Pizza implements Serializable {
    private String nomRecepta;
    private double preu;
    private int unitats;
    private String mida; // "Petita", "Mitjana", "Familiar"
    private String tipusMassa; // "Fina", "Clàssica", "Gruixuda"
    private boolean teTomaquet;
    private boolean teMozzarella;
    private boolean tePernil;
    private boolean teXampinyons;
    private boolean tePinya;
    private String picant; // "No", "Sí"
    private String codiDescompte;
    private int puntuacioClient;
    private String notesComanda;
    private String dataComanda;
    private boolean perEmportar;
    private String nivellPicant;

    //GETTERS I SETTERS


    public String getNomRecepta() {
        return nomRecepta;
    }

    public void setNomRecepta(String nomRecepta) {
        this.nomRecepta = nomRecepta;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public int getUnitats() {
        return unitats;
    }

    public void setUnitats(int unitats) {
        this.unitats = unitats;
    }

    public String getMida() {
        return mida;
    }

    public void setMida(String mida) {
        this.mida = mida;
    }

    public String getTipusMassa() {
        return tipusMassa;
    }

    public void setTipusMassa(String tipusMassa) {
        this.tipusMassa = tipusMassa;
    }

    public boolean isTeTomaquet() {
        return teTomaquet;
    }

    public void setTeTomaquet(boolean teTomaquet) {
        this.teTomaquet = teTomaquet;
    }

    public boolean isTeMozzarella() {
        return teMozzarella;
    }

    public void setTeMozzarella(boolean teMozzarella) {
        this.teMozzarella = teMozzarella;
    }

    public boolean isTePernil() {
        return tePernil;
    }

    public void setTePernil(boolean tePernil) {
        this.tePernil = tePernil;
    }

    public boolean isTeXampinyons() {
        return teXampinyons;
    }

    public void setTeXampinyons(boolean teXampinyons) {
        this.teXampinyons = teXampinyons;
    }

    public boolean isTePinya() {
        return tePinya;
    }

    public void setTePinya(boolean tePinya) {
        this.tePinya = tePinya;
    }

    public String getPicant() {
        return picant;
    }

    public void setPicant(String picant) {
        this.picant = picant;
    }

    public String getCodiDescompte() {
        return codiDescompte;
    }

    public void setCodiDescompte(String codiDescompte) {
        this.codiDescompte = codiDescompte;
    }

    public int getPuntuacioClient() {
        return puntuacioClient;
    }

    public void setPuntuacioClient(int puntuacioClient) {
        this.puntuacioClient = puntuacioClient;
    }

    public String getNotesComanda() {
        return notesComanda;
    }

    public void setNotesComanda(String notesComanda) {
        this.notesComanda = notesComanda;
    }

    public String getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(String dataComanda) {
        this.dataComanda = dataComanda;
    }

    public boolean isPerEmportar() {
        return perEmportar;
    }

    public void setPerEmportar(boolean perEmportar) {
        this.perEmportar = perEmportar;
    }

    public String getNivellPicant() {
        return nivellPicant;
    }

    public void setNivellPicant(String nivellPicant) {
        this.nivellPicant = nivellPicant;
    }

    //toString()


    @Override
    public String toString() {
        return "Pizza{" +
                "nomRecepta='" + nomRecepta + '\'' +
                ", preu=" + preu +
                ", unitats=" + unitats +
                ", mida='" + mida + '\'' +
                ", tipusMassa='" + tipusMassa + '\'' +
                ", teTomaquet=" + teTomaquet +
                ", teMozzarella=" + teMozzarella +
                ", tePernil=" + tePernil +
                ", teXampinyons=" + teXampinyons +
                ", tePinya=" + tePinya +
                ", picant='" + picant + '\'' +
                ", codiDescompte='" + codiDescompte + '\'' +
                ", puntuacioClient=" + puntuacioClient +
                ", notesComanda='" + notesComanda + '\'' +
                ", dataComanda='" + dataComanda + '\'' +
                ", perEmportar=" + perEmportar +
                ", nivellPicant='" + nivellPicant + '\'' +
                '}';
    }
}
