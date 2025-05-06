package de.home.vs.rest.model;

public class Artikel {
    private int artikelnummer;
    private String name;
    private String details;
    private double preis;
    private int lagerbestand;

    public Artikel(int artikelnummer, String name, String details, double preis, int lagerbestand) {
        this.artikelnummer = artikelnummer;
        this.name = name;
        this.details = details;
        this.preis = preis;
        this.lagerbestand = lagerbestand;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getLagerbestand() {
        return lagerbestand;
    }

    public void setLagerbestand(int lagerbestand) {
        this.lagerbestand = lagerbestand;
    }
}
