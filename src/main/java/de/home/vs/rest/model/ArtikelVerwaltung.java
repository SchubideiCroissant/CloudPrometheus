package de.home.vs.rest.model;

import de.home.vs.rest.model.Artikel;
import java.util.*;

public class ArtikelVerwaltung {

    private static ArtikelVerwaltung instance = null;
    private Map<Integer, Artikel> artikelMap = new HashMap<>();
    private int id = 0;

    private ArtikelVerwaltung() {
        // Initialisierung
        addArtikel("Kissen", "Beschreibung", 9.99, 100);
        addArtikel("Blume", "Rose", 3.50, 10);
    }

    public static ArtikelVerwaltung getInstance() {
        if (instance == null) {
            instance = new ArtikelVerwaltung();
        }
        return instance;
    }

    public Artikel addArtikel(String name, String details, double preis, int lagerbestand) {
        Artikel artikel = new Artikel(id, name, details, preis, lagerbestand);
        artikelMap.put(id, artikel);
        id++;
        return artikel;

    }

    public List<Artikel> getAlleArtikel() {
        return new ArrayList<>(artikelMap.values());
    }

    public Artikel getArtikelById(int id) {
        return artikelMap.get(id);
    }

    // Optional: Ändern oder Löschen von Artikeln
    public boolean deleteArtikel(int id) {
        return artikelMap.remove(id) != null;
    }
    public boolean aktualisiereArtikelPreis(int artikelnummer, double neuerPreis) {
        Artikel artikel = artikelMap.get(artikelnummer);
        if (artikel != null) {
            artikel.setPreis(neuerPreis);
            return true;
        }
        return false; // Artikel nicht gefunden
    }
}
