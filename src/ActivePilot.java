package src;

import java.util.TreeMap;

public class ActivePilot implements PersonState {
    private TreeMap<Integer, Drone> pilotedDrones;
    private DeliveryService workingFor;

    public ActivePilot(Drone drone, DeliveryService service, Person person) throws Exception {
        pilotedDrones = new TreeMap<Integer, Drone>();
        pilotDrone(drone, person);
        workingFor = service;
    }
    @Override
    public void workFor(DeliveryService service) throws Exception {
        throw new Exception("ERROR:employee_is_piloting_for_another_company");
    }

    @Override
    public void leave(DeliveryService service, Person person) throws Exception {
        throw new Exception("ERROR:employee_is_piloting_a_drone");
    }

    @Override
    public void becomeManager(Person person) throws Exception {
        throw new Exception("ERROR:employee_is_busy_piloting_a_drone");
    }

    @Override
    public void becomePilot(String license, int experience, Person person) throws Exception {
        throw new Exception("ERROR:employee_is_already_trained");
    }
    @Override
    public void pilotDrone(Drone drone, Person person) throws Exception {
        if (pilotedDrones.containsKey(drone.getUniqueID())) {
            throw new Exception("ERROR:pilot_already_flying_drone");
        }
        pilotedDrones.put(drone.getUniqueID(), drone);
        if (drone.getPilot() != null) {
            drone.getPilot().stopPilotingDrone(drone);
        }
        drone.setPilot(person);
    }
    public void stopPilotingDrone(Drone drone, Person person) {
        pilotedDrones.remove(drone.getUniqueID());
        if (pilotedDrones.size() == 0) {
            person.setState(new Employee(workingFor));
        }
    }
    @Override
    public String toString(Person person) {
        String text = "\nemployee is working at:\n&> " + workingFor.getName();
        text += "\nuser has a pilot's license (" + person.getLicense() + ") with " + person.getExperience() + " successful flight(s)";
        text += "\nemployee is flying these drones: [ drone tags ";
        for (Drone drone : pilotedDrones.values()) {
            text += "| " + drone.getUniqueID() + " ";
        }
        text += "]";
        return text;
    }
}
