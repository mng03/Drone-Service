package src;

import java.time.LocalDate;
import java.util.Date;
import java.util.TreeSet;

public class Person {
    private String username;
    private String fname;
    private String lname;
    private LocalDate bdate;
    private String address;
    private TreeSet<DeliveryService> workingFor;
    private DeliveryService manages;

    public Person(String username, String fname, String lname, int year, int month, int date, String address) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.bdate = LocalDate.of(year, month, date);
        this.address = address;
        workingFor = new TreeSet<DeliveryService>();
        manages = null;
    }

    public Person(Person person) {
        this(person.username, person.fname, person.lname, person.bdate.getYear(), person.bdate.getMonthValue(), person.bdate.getDayOfMonth(), person.address);
    }

    public String getUsername() {
        return username;
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
        if (workingFor.size() != 1) {
            throw new Exception("ERROR:employee_is_working_at_other_companies");
        }
        manages = service;
    }

    public void stopManaging() {
        manages = null;
    }

    public Pilot becomePilot(String license, int experience) throws Exception {
        if (manages != null) {
            throw new Exception("ERROR:employee_is_too_busy_managing");
        }
        return new Pilot(this, license, experience);
    }

    public String toString() {
        String workingAt = "";
        if (manages != null) {
            workingAt = "\nemployee is managing: " + manages;
        } else {
            workingAt += "\nemployee is working at: ";
            for (DeliveryService service : workingFor) {
                workingAt += "\n&> " + service.getName();
            }
        }
        return "userID: " + username + ", name: " + fname + " " + lname + ", birth date: " + bdate
                + ", address: " + address + workingAt;
    }
}