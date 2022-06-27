package src;

import java.util.TreeMap;

public class Pilot extends Person {
    private String license;
    private int experience;
    private TreeMap<Integer, Drone> pilotedDrones;

    public Pilot(Person person, String license, int experience) {
        super(person);
        this.license = license;
        this.experience = experience;
        pilotedDrones = new TreeMap<Integer, Drone>();
    }
    @Override
    public void workFor(DeliveryService service) throws Exception {
        if (pilotedDrones.size() != 0) {
            throw new Exception("ERROR:employee_is_piloting_for_another_company");
        }
        super.workFor(service);
    }
    @Override
    public void leave(DeliveryService service) throws Exception {
        if (pilotedDrones.size() != 0) {
            throw new Exception("ERROR:employee_is_piloting_a_drone");
        }
        super.leave(service);
    }

    public void becomeManager(DeliveryService service) throws Exception {
        if (pilotedDrones.size() != 0) {
            throw new Exception("ERROR:employee_is_busy_piloting_a_drone");
        }
        super.becomeManager(service);
    }

    public void pilotDrone(Drone drone) throws Exception {
        if (manages != null) {
            throw new Exception("ERROR:employee_is_too_busy_managing");
        }
        if (workingFor.size() != 1) {
            throw new Exception("ERROR:employee_is_working_at_other_companies");
        }
        pilotedDrones.put(drone.getUniqueID(), drone);
        drone.setPilot(this);
    }

    public void stopPilotingDrone(Drone drone) {
        pilotedDrones.remove(drone.getUniqueID());
    }

    public void addExperience() {
        experience++;
    }
    @Override
    public String toString() {
        String text = super.toString() + "\nuser has a pilot's license (" + license + ") with " + experience + " successful flight(s)";
        if (pilotedDrones.size() > 0) {
            text += "\nemployee is flying these drones: [ drone tags ";
            for (Drone drone : pilotedDrones.values()) {
                text += "| " + drone.getUniqueID() + " ";
            }
            text += "]";
        }
        return text;
    }
}
