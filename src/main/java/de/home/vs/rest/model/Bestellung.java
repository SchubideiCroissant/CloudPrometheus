package de.home.vs.rest.model;
import java.time.LocalDateTime;

import java.util.List;

public class Bestellung {
    private int bestellnummer;
    private LocalDateTime zeit = LocalDateTime.now();
    private Customer customer ;

    private List<Bestellposten> artikelMitMenge;

    // Konstruktor
    public Bestellung(int bestellnummer, List<Bestellposten> artikelMitMenge, Customer customer) {
        this.bestellnummer = bestellnummer;
        this.customer = customer;
        this.artikelMitMenge = artikelMitMenge;
        this.zeit = LocalDateTime.now();
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
    public Customer getCustomer() {
        return customer;
    }

    public List<Bestellposten> getPosten() {
        return artikelMitMenge;
    }

    public void setArtikelMitMenge(List<Bestellposten> artikelMitMenge) {
        this.artikelMitMenge = artikelMitMenge;
    }
}
