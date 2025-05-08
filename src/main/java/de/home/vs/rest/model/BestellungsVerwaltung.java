package de.home.vs.rest.model;

import java.util.*;

public class BestellungsVerwaltung {

    private static BestellungsVerwaltung instance = null;
    private Map<Integer, Bestellung> bestellungen = new HashMap<>();
    private int id = 0;

    private BestellungsVerwaltung() {
        ArtikelVerwaltung av = ArtikelVerwaltung.getInstance();
        CustomerVerwaltung.getInstance().addCustomer("Rias","Gremory");
        CustomerVerwaltung.getInstance().addCustomer("Akeno","Himejima");
        Customer customer = CustomerVerwaltung.getInstance().getCustomerById(0);

        List<Bestellposten> bestellung1 = new ArrayList<>();
        Bestellposten a = new Bestellposten(av.getArtikelById(0),10);
        Bestellposten b = new Bestellposten(av.getArtikelById(1),2);
        bestellung1.add(a);
        bestellung1.add(b);
        addBestellung(bestellung1, customer);


    }

    public static BestellungsVerwaltung getInstance() {
        if (instance == null) {
            instance = new BestellungsVerwaltung();
        }
        return instance;
    }

    public Bestellung addBestellung(List<Bestellposten> posten, Customer customer) {
        Bestellung b = new Bestellung(id++, posten, customer);
        bestellungen.put(b.getBestellnummer(), b);
        return b;
    }

    public List<Bestellung> getAlleBestellungen() {
        return new ArrayList<>(bestellungen.values());
    }

    public Bestellung getBestellungById(int id) {
        return bestellungen.get(id);
    }

    public boolean deleteBestellung(int id) {
        return bestellungen.remove(id) != null;
    }
}
