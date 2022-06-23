package src;

import java.util.Date;
import java.util.TreeSet;

public class Person {
    private String username;
    private String fname;
    private String lname;
    private Date bdate;
    private String address;
    private TreeSet<DeliveryService> workingFor;
    private DeliveryService manages;

    public Person(String username, String fname, String lname, int year, int month, int date, String address) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.bdate = new Date(year, month, date);
        this.address = address;
        workingFor = new TreeSet<DeliveryService>();
        manages = null;
    }

    public void workFor(DeliveryService service) throws Exception {
        if (manages != null) {
            throw new Exception("ERROR:employee_is_managing_another_service");
        }
        workingFor.add(service);
    }

    public void leave(DeliveryService service) throws Exception {
        if (manages != null) {
            throw new Exception("ERROR:employee_is_managing_a_service");
        }
        workingFor.remove(service);
    }

    public void becomeManager(DeliveryService service) throws Exception {

    }

    public Pilot becomePilot(String license, int experience) throws Exception {

    }

    public String toString() {

    }
}