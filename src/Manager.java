package src;

public class Manager implements PersonState {
    private DeliveryService managing;
    private Person person;

    public Manager(DeliveryService service, Person person) {
        managing = service;
        this.person = person;
    }

    @Override
    public void workFor(DeliveryService service) throws Exception {
        throw new Exception("ERROR:employee_is_managing_another_service");
    }

    @Override
    public void leave(DeliveryService service) throws Exception {
        throw new Exception("ERROR:employee_is_managing_a_service");
    }

    @Override
    public void becomeManager() throws Exception {
        throw new Exception("ERROR:this_person_is_already_managing_this_service");
    }

    @Override
    public void becomePilot(String license, int experience) throws Exception {
        throw new Exception("ERROR:employee_is_too_busy_managing");
    }

    //Assumes DeliveryService correctly dismisses current manager of service
    public void stopManaging() {
        person.setState(new Employee(managing, person));
    }

    @Override
    public void pilotDrone(Drone drone) throws Exception {
        throw new Exception("ERROR:employee_is_too_busy_managing");
    }
    @Override
    public String toString() {
        return "\nemployee is managing: " + managing.getName();
    }
}
