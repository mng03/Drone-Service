package src;

public class Manager implements PersonState {
    private DeliveryService managing;

    public Manager(DeliveryService service) {
        managing = service;
    }

    @Override
    public void workFor(DeliveryService service) throws Exception {
        throw new Exception("ERROR:employee_is_managing_another_service");
    }

    @Override
    public void leave(DeliveryService service, Person person) throws Exception {
        throw new Exception("ERROR:employee_is_managing_a_service");
    }

    @Override
    public void becomeManager(Person person) throws Exception {
        throw new Exception("ERROR:this_person_is_already_managing_this_service");
    }

    @Override
    public void becomePilot(String license, int experience, Person person) throws Exception {
        throw new Exception("ERROR:employee_is_too_busy_managing");
    }

    //Assumes DeliveryService correctly dismisses current manager of service
    public void stopManaging(Person person) {
        person.setState(new Employee(managing));
    }

    @Override
    public void pilotDrone(Drone drone, Person person) throws Exception {
        throw new Exception("ERROR:employee_is_too_busy_managing");
    }
    @Override
    public String toString(Person person) {
        return "\nemployee is managing: " + managing.getName();
    }
}
