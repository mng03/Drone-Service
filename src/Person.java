package src;

import java.time.LocalDate;
import java.util.TreeMap;

public class Person {
    protected String username;
    protected String fname;
    protected String lname;
    protected LocalDate bdate;
    protected String address;
    protected TreeMap<String, DeliveryService> workingFor;
    protected DeliveryService manages;
    public static TreeMap<String, Person> people = new TreeMap<String, Person>();

    public Person(String username, String fname, String lname, int year, int month, int date, String address) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.bdate = LocalDate.of(year, month, date);
        this.address = address;
        workingFor = new TreeMap<String, DeliveryService>();
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
        workingFor.put(service.getName(), service);
    }

    public void leave(DeliveryService service) throws Exception {
        if (manages != null) {
            throw new Exception("ERROR:employee_is_managing_a_service");
        }
        workingFor.remove(service.getName());
    }

    public void becomeManager(DeliveryService service) throws Exception {
        if (workingFor.size() > 1) {
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
        Pilot newPilot = new Pilot(this, license, experience);
        people.remove(this.username);
        people.put(newPilot.username, newPilot);
        return newPilot;
    }

    public String toString() {
        String workingAt = "";
        if (manages != null) {
            workingAt = "\nemployee is managing: " + manages.getName();
        } else if (workingFor.size() != 0) {
            workingAt += "\nemployee is working at: ";
            for (DeliveryService service : workingFor.values()) {
                workingAt += "\n&> " + service.getName();
            }
        }
        return "userID: " + username + ", name: " + fname + " " + lname + ", birth date: " + bdate
                + ", address: " + address + workingAt;
    }
    public static void makePerson(String init_username, String init_fname, String init_lname, Integer init_year, Integer init_month, Integer init_date, String init_address) throws Exception {
        //TODO: Do we need to check date values are valid?
        if(init_username.equals("")) {
            throw new Exception("ERROR:username_cannot_be_empty");
        } else if (people.containsKey(init_username)) {
            throw new Exception("ERROR:username_already_exists");
        } else {
            people.put(init_username, new Person(init_username, init_fname, init_lname, init_year, init_month, init_date, init_address));
        }
    }
    public static void personExists(String user_name) throws Exception {
        if (!people.containsKey(user_name)) {
            throw new Exception("ERROR:person_does_not_exist");
        } 
    }
}