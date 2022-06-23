package src;

public class Pilot extends Person {
    private String license;
    private int experience;
    private Drone pilots;

    public Pilot(Person person, String license, int experience) {
        super(person);
        this.license = license;
        this.experience = experience;
        pilots = null;
    }

    public void pilotDrone(Drone drone) throws Exception {
        
    }

    public void stopPilotingDrone() {
        pilots.removePilot();
        pilots = null;
    }

    public String toString() {
        return super.toString() + "\nuser has a pilot's license (" + license + ") with " + experience + " successful flight(s)"
                + (pilots == null ? "" : "\nemployee is flying these drones: [ drone tags | " + pilots.getUniqueID() + " ]");
    }
}
