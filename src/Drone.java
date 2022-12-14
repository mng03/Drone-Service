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
    private Person pilot;
    private Drone leader;
    private TreeMap<Integer, Drone> followers;

    public Drone(int uniqueID, int capacity, int fuel, Location location) {
        this.uniqueID = uniqueID;
        this.capacity = capacity;
        this.homeBase = location;

        packages = new TreeMap<String, Package>();
        this.fuel = fuel;
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

    public Person getPilot() { return pilot; }

    public void setFuel(int newFuel) {
        fuel = newFuel;
    }

    public void addFuel(int petrol) throws Exception {
        if (!currLocation.equals(homeBase)) {
            throw new Exception("ERROR:drone_not_located_at_home_base");
        } else if (petrol < 0) {
            throw new Exception("ERROR:fuel_cannot_be_negative");
        }
        fuel += petrol;
    }

    public void loadPackage(Package packageToAdd) throws Exception {
        if (currCapacity - packageToAdd.getQuantity() < 0) {
            throw new Exception("ERROR:drone_does_not_have_enough_space_for_ingredient");
        } else if (!currLocation.equals(homeBase)) {
            throw new Exception("ERROR:drone_not_located_at_home_base");
        }
        String barcode = packageToAdd.getIngredient().getBarcode();
        if (packages.containsKey(barcode)) {
            packages.get(barcode).addToPackage(packageToAdd.getQuantity());
        } else {
            packages.put(barcode, packageToAdd);
        }
        currCapacity -= packageToAdd.getQuantity();
    }

    public void fly(Location destination) throws Exception {
        if (leader != null) {
            throw new Exception("ERROR:cannot_control_swarm_drone");
        } else if (pilot == null) {
            throw new Exception("ERROR:drone_does_not_have_pilot");
        } else if (destination.calcDistance(currLocation) > fuel) {
            throw new Exception("ERROR:not_enough_fuel_to_reach_the_destination");
        } else if (destination.calcDistance(currLocation) + destination.calcDistance(homeBase) > fuel) {
            throw new Exception("ERROR:not_enough_fuel_to_reach_home_base_from_the_destination");
        } else if (destination.equals(currLocation)) {
            throw new Exception("ERROR:drone_already_at_location");
        } else if (destination.getCurrSpots() < followers.size() + 1) {
            throw new Exception("ERROR:not_enough_space_to_maneuver_the_swarm_to_that_location");
        } else {
            for (Drone d : followers.values()) {
                if (destination.calcDistance(currLocation) + destination.calcDistance(homeBase) > d.getFuel()) {
                    throw new Exception("ERROR:one_or_more_swarm_drones_do_not_have_enough_fuel");
                }
            }
        }
        move(destination);
        for (Drone d : followers.values()) {
            d.move(destination);
        }
        pilot.addExperience();
    }

    public void move(Location destination) {
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

    public void setPilot(Person pilot) {
        if (leader != null) {
            leader.removeFromSwarm(this);
            leader = null;
        }
        this.pilot = pilot;
    }

    public void swarm(Drone leadDrone) throws Exception {
        if (leader != null && leadDrone.getUniqueID() == leader.getUniqueID()) {
            throw new Exception("ERROR:swarm_drone_already_following_same_lead_drone");
        }
        if (followers.size() > 0) {
            throw new Exception("ERROR:this_drone_is_leading_a_swarm_already");
        }
        if (leadDrone.leader != null) {
            throw new Exception("ERROR:lead_drone_in_a_swarm");
        }
        if (leadDrone.pilot == null) {
            throw new Exception("ERROR:lead_drone_must_have_a_pilot");
        }
        if (this.pilot != null) {
            this.pilot.stopPilotingDrone(this);
            pilot = null;
        }
        if (this.leader != null) {
            this.leader.removeFromSwarm(this);
        }
        this.leader = leadDrone;
        leadDrone.addToSwarm(this);
    }

    public void leaveSwarm() throws Exception {
        if (leader == null) {
            throw new Exception("ERROR:drone_is_not_following_another_drone");
        }
        leader.removeFromSwarm(this);
        setPilot(leader.pilot);
        leader = null;
    }

    public void addToSwarm(Drone follower) {
        followers.put(follower.getUniqueID(), follower);
    }

    public void removeFromSwarm(Drone follower) {
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
                    text += "| " + d.getUniqueID() + " ";
                }
                text += "]";
            }
        }
        for (Package pck : packages.values()) {
            text += "\n" + pck;
        }
        return text;
    }

}
