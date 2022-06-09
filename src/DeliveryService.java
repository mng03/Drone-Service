package src;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines the functionality and behavior of the Delivery Services.
 *
 * @author Group 9
 * @version 1.0
 */
public class DeliveryService {
    private String name;
    private int revenue;
    private Location location;
    private Map<Integer, Drone> drones;

    public DeliveryService(String name, int revenue, Location location) {
        this.name = name;
        this.location = location;
        this.revenue = revenue;
        drones = new HashMap<Integer, Drone>();
    }

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public Location getLocation() {
        return location;
    }

    public Map<Integer, Drone> getDrones() {
        return drones;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void addDrone(Drone drone) {
        drones.put(drone.getUniqueID(), drone);
    }

    public void removeDrone(Drone drone) {
        drones.remove(drone.getUniqueID());
    }

    public void purchaseDrone(int droneID, int capacity, int fuelMax) throws Exception {
            if (location.getCurrSpots() == 0) {
                throw new Exception("ERROR:not_enough_space_to_create_new_drone");
            } else if (drones.containsKey(droneID)) {
                throw new Exception("ERROR:drone_with_tag_already_exists_in_service");
            } else {
                drones.put(droneID, new Drone(droneID, capacity, fuelMax, location));
            }
    }

    public void loadFuel(int droneID, int petrol) {

    }

    public void loadPackage(int droneID, IngredientInfo ingredient, int quantity, int unitPrice) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else {
            Drone drone = drones.get(droneID);
            if (drone.getCurrCapacity() - quantity < 0) {
                throw new Exception("ERROR:drone_does_not_have_enough_space_for_ingredient");
            } else if (drone.getCurrLocation().equals(location)) {
                throw new Exception("ERROR:drone_not_located_at_home_base");
            }
            Package packageToAdd = new Package(ingredient, unitPrice, quantity);
            drone.loadPackage(packageToAdd);
        }

    }

    public void flyDrone(int droneID, Location destination) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else {
            Drone drone = drones.get(droneID);
            int toDestinationDistance = destination.calcDistance(drone.getCurrLocation());
            if (toDestinationDistance > drone.getFuel()) {
                throw new Exception("ERROR:not_enough_fuel_to_reach_the_destination");
            } else if (toDestinationDistance + destination.calcDistance(location) > drone.getFuel()) {
                throw new Exception("ERROR:not_enough_fuel_to_reach_home_base_from_the_destination");
            } else if (destination.getCurrSpots() == 0) {
                throw new Exception("ERROR:not_enough_space_for_drone_at_destination");
            } else {
                drone.fly(destination);
            }
        } 
    }

    public double requestPackage(int droneID, int barcode, int quantity) {
        return 0;
    }

    public String toString() {
        return "name: " + name + ", revenue: $" + revenue + ", location: " + location.getName();
    }
}
