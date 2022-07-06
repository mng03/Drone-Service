package src;
public interface PersonState {
    //Methods assume DeliveryService already assessed that person is in or not service

    void workFor(DeliveryService service) throws Exception;
    
    void leave(DeliveryService service, Person person) throws Exception;

    void becomeManager(Person person) throws Exception;

    void becomePilot(String license, int experience, Person person) throws Exception;

    void pilotDrone(Drone drone, Person person) throws Exception;

    String toString(Person person);
}
