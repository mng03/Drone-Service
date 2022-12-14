package src;

import java.util.TreeMap;

public class Employee implements PersonState {
    private TreeMap<String, DeliveryService> workingFor;
    private Person person;

    public Employee(DeliveryService service, Person person) {
        workingFor = new TreeMap<String, DeliveryService>();
        workingFor.put(service.getName(), service);
        this.person = person;
    }

    @Override
    public void workFor(DeliveryService service) {
        workingFor.put(service.getName(), service);
    }

    @Override
    public void leave(DeliveryService service) {
        workingFor.remove(service.getName());
        if (workingFor.size() == 0) {
            person.setState(null);
        }
    }

    @Override
    public void becomeManager() throws Exception {
        if (workingFor.size() > 1) {
            throw new Exception("ERROR:employee_is_working_at_other_companies");
        }
        person.setState(new Manager(workingFor.firstEntry().getValue(), person));
    }

    @Override
    public void becomePilot(String license, int experience) {
        person.setExperience(experience);
        person.setLicense(license);
    }

    @Override
    public void pilotDrone(Drone drone) throws Exception {
        if (workingFor.size() != 1) {
            throw new Exception("ERROR:employee_is_working_at_other_companies");
        }
        person.setState(new ActivePilot(drone, workingFor.firstEntry().getValue(), person));
    }
    
    @Override
    public String toString() {
        String text = "\nemployee is working at: ";
        for (DeliveryService service : workingFor.values()) {
            text += "\n&> " + service.getName();
        }
        return text;
    }
}
