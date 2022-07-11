package src;
public interface PersonState {
    //Methods assume DeliveryService already assessed that person is in or not service

    void workFor(DeliveryService service) throws Exception;
    
    void leave(DeliveryService service) throws Exception;

    void becomeManager() throws Exception;

    void becomePilot(String license, int experience) throws Exception;

    void pilotDrone(Drone drone) throws Exception;

}
