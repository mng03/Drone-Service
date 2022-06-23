package src;

import java.util.TreeMap;
import java.util.Map;

/**
 * Class that defines the behavior of Drones.
 *
 * @author Group 9
 * @version 1.0
 */
public class Drone {
    private int uniqueID;
    private int capacity;
    private Location homeBase;

    private Map<String, Package> packages;
    private int fuel;
    private int sales;
    private int currCapacity;
    private Location currLocation;
    private Pilot pilot;
    private Drone leader;
    private TreeMap<Integer, Drone> followers;

    public Drone(int uniqueID, int capacity, int fuelMax, Location location) {
        this.uniqueID = uniqueID;
        this.capacity = capacity;
        this.homeBase = location;

        packages = new TreeMap<String, Package>();
        fuel = fuelMax;
        sales = 0;
        currCapacity = capacity;
        currLocation = location;
        homeBase.addDrone();
        pilot = null;
        leader = null;
        followers = new TreeMap<Integer, Drone>();
    }

    public int getUniqueID() {
        return uniqueID;
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

    public void resetSales() { sales = 0; }

    public int getCurrCapacity() {
        return currCapacity;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public Pilot getPilot() { return pilot; }

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

    public int getPackage(String barcode, int quantity) throws Exception {
        if (!packages.containsKey(barcode)) {
            throw new Exception("ERROR:drone_does_not_have_ingredient");
        } else {
            Package pkg = packages.get(barcode);
            if (pkg.getQuantity() - quantity < 0) {
                throw new Exception("ERROR:drone_does_not_have_enough_ingredient");
            }
            int saleAmount = pkg.unloadPackage(quantity);
            sales += saleAmount;
            if(pkg.getQuantity() == 0) {
                packages.remove(barcode);
            }
            currCapacity += quantity;
            return saleAmount;
        }
    }

    public void setPilot(Pilot pilot) {
        if (this.pilot != null) {
            this.pilot.stopPilotingDrone();
        }
        this.pilot = pilot;
    }

    public void swarm(Drone leader) throws Exception {
        if (followers.size() > 0) {
            throw new Exception("ERROR:this_drone_is_leading_a_swarm_already");
        }
        if (leader.pilot == null) {
            throw new Exception("ERROR:lead_drone_must_have_a_pilot");
        }
        if (this.pilot != null) {
            this.pilot.stopPilotingDrone();
        }
        if (this.leader != null) {
            this.leader.removeSwarmDrone(this);
        }
        this.leader = leader;
        leader.addSwarmDrone(this);
    }

    public void leaveSwarm() {
        leader.removeSwarmDrone(this);
        setPilot(leader.pilot);
    }

    public void addSwarmDrone(Drone follower) {
        followers.put(follower.getUniqueID(), follower);
    }

    public void removeSwarmDrone(Drone follower) {
        followers.remove(follower.getUniqueID());
    }

    public String toString() {
        String text = "tag: " + uniqueID + ", capacity: " + capacity + ", remaining_cap: " + currCapacity +
        ", fuel: " + fuel + ", sales: $" + sales + ", location: " + currLocation.getName();
        if (pilot != null) {
            text += "\n&> pilot: " + pilot.getUsername();
            if (followers.size() > 0) {
                text += "\ndrone is directing this swarm: [ drone tags ";
                for (Drone d : followers.values()) {
                    text += "| " + d.getUniqueID();
                }
                text += " ]";
            }
        }
        for (Package pck : packages.values()) {
            text += "\n" + pck;
        }
        return text;
    }

}
