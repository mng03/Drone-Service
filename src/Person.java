package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.TreeMap;

public class Person {
    private String username;
    private String fname;
    private String lname;
    private LocalDate bdate;
    private String address;

    private PersonState state;

    private String license;
    private int experience;

    public static TreeMap<String, Person> people = new TreeMap<String, Person>();
    public static ObservableList<String> peopleGUI = FXCollections.observableArrayList();

    public Person(String username, String fname, String lname, int year, int month, int date, String address) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.bdate = LocalDate.of(year, month, date);
        this.address = address;
        state = null;
        license = null;
        experience = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setState(PersonState state) {
        this.state = state;
    }

    public void setLicense(String lic) {
        license = lic;
    }

    public void setExperience(int exp) {
        experience = exp;
    }

    public String getLicense() {
        return license;
    }

    public int getExperience() {
        return experience;
    }

    public void workFor(DeliveryService service) throws Exception {
        if (state == null) {
            state = new Employee(service, this);
        } else {
            state.workFor(service);
        }
    }

    public void leave(DeliveryService service) throws Exception {
        state.leave(service);
    }

    public void becomeManager(DeliveryService service) throws Exception {
        state.becomeManager();
    }

    public void stopManaging() {
        ((Manager) state).stopManaging();
    }

    public void becomePilot(String license, int experience) throws Exception {
        if (this.license != null) {
            throw new Exception("ERROR:employee_is_already_trained");
        }
        state.becomePilot(license, experience);
    }

    public void pilotDrone(Drone drone) throws Exception {
        if (license == null) {
            throw new Exception("ERROR:employee_does_not_have_a_valid_pilot's_license");
        }
        state.pilotDrone(drone);
    }

    public void addExperience() {
        experience++;
    }

    public void stopPilotingDrone(Drone drone) {
        ((ActivePilot) state).stopPilotingDrone(drone, this);
    }

    public String toString() {
        String text = "userID: " + username + ", name: " + fname + " " + lname + ", birth date: " + bdate
                + ", address: " + address;
        if (state != null) {
            text += state.toString();
        }
        if(!(state instanceof ActivePilot) && license != null) {
            text += "\nuser has a pilot's license (" + license + ") with " + experience + " successful flight(s)";
        }
        return text;
    }


    
    public static void makePerson(String init_username, String init_fname, String init_lname, Integer init_year, Integer init_month, Integer init_date, String init_address) throws Exception {
        if(init_username.equals("")) {
            throw new Exception("ERROR:username_cannot_be_empty");
        } else if (people.containsKey(init_username)) {
            throw new Exception("ERROR:username_already_exists");
        } else {
            people.put(init_username, new Person(init_username, init_fname, init_lname, init_year, init_month, init_date, init_address));
            peopleGUI.add(init_username);
            FXCollections.sort(peopleGUI);
        }
    }
    public static void personExists(String user_name) throws Exception {
        if (!people.containsKey(user_name)) {
            throw new Exception("ERROR:person_does_not_exist");
        } 
    }
}