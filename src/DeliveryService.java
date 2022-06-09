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
    //TODO: Is id a string or an int?
    private int id;
    private String name;
    private double revenue;
    private Location location;
    private Map<Integer, Drone> drones;

    public DeliveryService(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        revenue = 0;
        drones = new HashMap<Integer, Drone>();
    }

    public int getID() {
        return id;
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

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void addDrone(Drone drone) {
        drones.put(drone.getUniqueID(), drone);
    }

    public void removeDrone(Drone drone) {
        drones.remove(drone.getUniqueID());
    }

    public void purchaseDrone(int droneID, int capacity, int fuelMax) {
        drones.put(droneID, new Drone(droneID, capacity, fuelMax, this));
    }

    public void loadFuel(int droneID, int petrol) {

    }

    public void loadPackage(int droneID, IngredientInfo ingredient, int quantity, double unitPrice) {

    }

    public void flyDrone(int droneID, Location destination) {

    }

    public double requestPackage(int droneID, int barcode, int quantity) {
        return 0;
    }

    public String toString() {
        return "name: " + name + ", revenue: $" + revenue + ", location: " + location.getName();
    }
}
