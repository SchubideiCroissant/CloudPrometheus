package de.home.vs.rest.model;

public class Bestellposten {
    private Artikel artikel;
    private int menge;

    // Konstruktor
    public Bestellposten(Artikel artikel, int menge) {
        this.artikel = artikel;
        this.menge = menge;
    }

    // Getter
    public Artikel getArtikel() {
        return artikel;
    }

    public int getMenge() {
        return menge;
    }

    // Setter
    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}


