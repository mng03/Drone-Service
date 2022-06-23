package src;

public class Pilot extends Person {
    private String license;
    private int experience;
    private Drone pilotedDrone;

    public Pilot(Person person, String license, int experience) {
        super(person);
        this.license = license;
        this.experience = experience;
        pilotedDrone = null;
    }

    public void workFor(DeliveryService service) throws Exception {
        if (pilotedDrone != null) {
            throw new Exception("ERROR:employee_is_piloting_for_another_company");
        }
        super.workFor(service);
    }

    public void leave(DeliveryService service) throws Exception {
        if (pilotedDrone != null) {
            throw new Exception("ERROR:employee_is_piloting_a_drone");
        }
        super.leave(service);
    }

    public void becomeManager(DeliveryService service) throws Exception {
        if (pilotedDrone != null) {
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
        if (pilotedDrone != null) {
            stopPilotingDrone();
        }
        pilotedDrone = drone;
        pilotedDrone.setPilot(this);
    }

    public void stopPilotingDrone() {
        pilotedDrone = null;
    }

    public void addExperience() {
        experience++;
    }

    public String toString() {
        return super.toString() + "\nuser has a pilot's license (" + license + ") with " + experience + " successful flight(s)"
                + (pilotedDrone == null ? "" : "\nemployee is flying these drones: [ drone tags | " + pilotedDrone.getUniqueID() + " ]");
    }
}
