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
    private Location homeBase;

    private Map<String, Package> packages;
    private int fuel;
    private int sales;
    private int currCapacity;
    private Location currLocation;

    public Drone(int uniqueID, int capacity, int fuelMax, Location location) {
        this.uniqueID = uniqueID;
        this.capacity = capacity;
        this.fuelMax = fuelMax;
        this.homeBase = location;

        packages = new HashMap<String, Package>();
        fuel = fuelMax;
        sales = 0;
        currCapacity = capacity;
        currLocation = location;
        homeBase.addDrone();
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

    public Location getHomeBase() {
        return homeBase;
    }

    public Map<String, Package> getPackages() {
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
        fuel += petrol;
    }

    public void loadPackage(Package packageToAdd) {
        String barcode = packageToAdd.getIngredient().getBarcode();
        if (packages.containsKey(barcode)) {
            packages.get(barcode).addToPackage(packageToAdd.getQuantity());
        } else {
            packages.put(barcode, packageToAdd);
        }
        currCapacity -= packageToAdd.getQuantity();
    }

    public void fly(Location destination) {
        fuel -= currLocation.calcDistance(destination);
        currLocation.removeDrone();
        currLocation = destination;
        destination.addDrone();
    }

    public int getPackage(int barcode, int quantity) {
        return 0;
    }

    public String toString() {
        String text = "tag: " + uniqueID + ", capacity: " + capacity + ", remaining_cap: " + currCapacity +
        ", fuel: " + fuel + ", sales: $" + sales + ", location: " + currLocation.getName();
        for (Package pck : packages.values()) {
            text += "\n" + pck;
        }
        return text;
    }

}
