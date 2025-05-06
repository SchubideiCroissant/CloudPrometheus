package de.home.vs.rest.model;
import java.time.LocalDateTime;

import java.util.List;

public class Bestellung {
    private int bestellnummer;
    private LocalDateTime zeit = LocalDateTime.now();
    private List<Bestellposten> artikelMitMenge;

    // Konstruktor
    public Bestellung(int bestellnummer, List<Bestellposten> artikelMitMenge) {
        this.bestellnummer = bestellnummer;
        this.zeit = LocalDateTime.now();
        this.artikelMitMenge = artikelMitMenge;
    }

    // Getter & Setter
    public int getBestellnummer() {
        return bestellnummer;
    }


    public LocalDateTime getZeit() {
        return zeit;
    }

    public void setZeit(LocalDateTime zeit) {
        this.zeit = zeit;
    }

    public List<Bestellposten> getPosten() {
        return artikelMitMenge;
    }

    public void setArtikelMitMenge(List<Bestellposten> artikelMitMenge) {
        this.artikelMitMenge = artikelMitMenge;
    }
}
