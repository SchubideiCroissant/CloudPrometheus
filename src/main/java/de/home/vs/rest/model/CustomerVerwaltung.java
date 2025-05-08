package de.home.vs.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerVerwaltung {
    private static CustomerVerwaltung instance = null;
    private Map<Integer, Customer> customerMap = new HashMap<>();
    private int id = 0;

    private CustomerVerwaltung(){

    }
    public static CustomerVerwaltung getInstance(){
        if(instance == null){
            instance = new CustomerVerwaltung();
        }
        return instance;
    }
    public void addCustomer(String firstName, String familyName){
        Customer c = new Customer(firstName, familyName, id);
        customerMap.put(id, c);
        id++;
    }
    public List<Customer> getAllCustomers(){return new ArrayList<>(customerMap.values());}

    public Customer getCustomerById(int id) {
        return customerMap.get(id);
    }
    public boolean deleteCustomer(int id){return customerMap.remove(id) != null;}


}
