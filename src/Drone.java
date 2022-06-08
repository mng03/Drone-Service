package src;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines the behavior of Drones.
 *
 * @author Group 9
 * @version 1.0
 */
public class Drone {
    private int uniqueID;
    private int fuelMax;
    private int capacity;
    private DeliveryService service;

    private Map<Integer, Package> packages;
    private int fuel;
    private double sales;
    private int currCapacity;
    private Location currLocation;

    public Drone(int uniqueID, int capacity, int fuelMax, DeliveryService service) {
        this.uniqueID = uniqueID;
        this.capacity = capacity;
        this.fuelMax = fuelMax;
        this.service = service;

        packages = new HashMap<Integer, Package>();
        fuel = 0;
        sales = 0;
        currCapacity = 0;
        currLocation = service.getLocation();
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public int getFuelMax() {
        return fuelMax;
    }

    public int getCapacity() {
        return capacity;
    }

    public DeliveryService getService() {
        return service;
    }

    public Map<Integer, Package> getPackages() {
        return packages;
    }

    public int getFuel() {
        return fuel;
    }

    public double getSales() {
        return sales;
    }

    public int getCurrCapacity() {
        return currCapacity;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public void setFuel(int newFuel) {
        fuel = newFuel;
    }

    public void addFuel(int petrol) {
        setFuel(petrol + fuel);
    }

    /**
     * Calculates the total distance the drone would have to travel to reach a
     * destination from its current location and also fly back to its home base
     * location.
     * @param destination The location the drone might fly to
     * @return the total distance it would have to travel
     */
    public int calcTotalDistance(Location destination) {
        return currLocation.calcDistance(destination) + destination.calcDistance(service.getLocation());
    }

    public void loadPackage(Package packageToAdd) {

    }

    public void fly(Location destination) {

    }

    public double getPackage(int barcode, int quantity) {
        return 0;
    }

    public String toString() {
        String packagesString = "[";
        for (int i = 0; i < currCapacity; i++) {
            packagesString += packages.get(0);
            if (i < currCapacity - 1) {
                packagesString += ", ";
            }
        }
        packagesString += "]";
        return "Drone: " + uniqueID + " in Delivery Service: " + service
                + " is currently carrying: " + packagesString + " and has made $"
                + sales + " in sales.";
    }

}
