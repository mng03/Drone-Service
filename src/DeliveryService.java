package src;

import java.util.TreeMap;
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
    private TreeMap<String, Person> employees;
    private Person manager;
    private int workerCount;

    public DeliveryService(String name, int revenue, Location location) {
        this.name = name;
        this.location = location;
        this.revenue = revenue;
        drones = new TreeMap<Integer, Drone>();
        employees = new TreeMap<String, Person>();
        manager = null;
        workerCount = 0;
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

    public void loadFuel(int droneID, int petrol) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else if (workerCount < 1) {
            throw new Exception("ERROR:not_enough_workers_to_complete_task");
        } else {
            Drone drone = drones.get(droneID);
            if (!drone.getCurrLocation().equals(location)) {
                throw new Exception("ERROR:drone_not_located_at_home_base");
            } else if (petrol < 0) {
                throw new Exception("ERROR:fuel_cannot_be_negative");
            }
            drone.addFuel(petrol);
        }
    }

    public void loadPackage(int droneID, IngredientInfo ingredient, int quantity, int unitPrice) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else if (workerCount < 1) {
            throw new Exception("ERROR:not_enough_workers_to_complete_task");
        } else {
            Drone drone = drones.get(droneID);
            if (drone.getCurrCapacity() - quantity < 0) {
                throw new Exception("ERROR:drone_does_not_have_enough_space_for_ingredient");
            } else if (!drone.getCurrLocation().equals(location)) {
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
            } else if (toDestinationDistance + destination.calcDistance(location) > drone.getFuel() && !destination.equals(location)) {
                throw new Exception("ERROR:not_enough_fuel_to_reach_home_base_from_the_destination");
            } else if (destination.getCurrSpots() == 0) {
                throw new Exception("ERROR:not_enough_space_for_drone_at_destination");
            } else {
                drone.fly(destination);
            }
        } 
    }

    public int requestPackage(Restaurant restaurant, int droneID, String barcode, int quantity) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else {
            Drone drone = drones.get(droneID);
            if (!restaurant.getLocation().equals(drone.getCurrLocation())) {
                throw new Exception("ERROR:drone_not_located_at_restaurant");
            }
            return drone.getPackage(barcode, quantity);
        }
    }

    public void hire(Person person) throws Exception {
        if (employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:person_already_works_for_service");
        }
        person.workFor(this);
        employees.put(person.getUsername(), person);
        workerCount++;
    }

    public void fire(Person person) throws Exception {
        if (!employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:employee_does_not_work_for_this_service");
        }
        person.leave(this);
        employees.remove(person.getUsername());
        workerCount--;
    }

    public void makeManager(Person person) throws Exception {
        if (!employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:employee_does_not_work_for_this_service");
        }
        person.becomeManager(this);
        if (manager != null) {
            manager.stopManaging();
            workerCount++;
        }
        manager = person;
        workerCount--;
    }

    public Pilot train(Person person, String license, int experience) throws Exception {
        if (!employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:employee_does_not_work_for_this_service");
        }
        if (manager == null) {
            throw new Exception("ERROR:delivery_service_does_not_have_valid_manager");
        }
        Pilot newPilot = person.becomePilot(license, experience);
        employees.remove(person.getUsername());
        employees.put(newPilot.getUsername(), newPilot);
        return newPilot;
    }

    public void assignDronePilot(Person person, int droneTag) throws Exception {
        if (!employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:employee_does_not_work_for_this_service");
        }
        if (!(person instanceof Pilot)) {
            throw new Exception("ERROR:employee_does_not_have_a_valid_pilot's_license");
        }
        if (!drones.containsKey(droneTag)) {
            throw new Exception("ERROR:drone_does_not_exist");
        }
        if(drones.get(droneTag).getPilot() != null) {
            ((Pilot) person).pilotDrone(drones.get(droneTag));
            workerCount++;
        } else {
            ((Pilot) person).pilotDrone(drones.get(droneTag));
        }
        workerCount--;
    }

    public void addSwarmDrone(int leadDroneTag, int swarmDroneTag) throws Exception {
        if (!drones.containsKey(leadDroneTag) || !drones.containsKey(swarmDroneTag)) {
            throw new Exception("ERROR:drone_does_not_exist");
        }
        if (leadDroneTag == swarmDroneTag) {
            throw new Exception("ERROR:a_drone_cannot_follow_itself");
        }
        drones.get(swarmDroneTag).swarm(drones.get(leadDroneTag));
    }

    public void removeSwarmDrone(int droneTag) throws Exception {
        if (!drones.containsKey(droneTag)) {
            throw new Exception("ERROR:drone_does_not_exist");
        }
        drones.get(droneTag).leaveSwarm();
    }

    public void getMoney() throws Exception {
        if (manager == null) {
            throw new Exception("ERROR:delivery_service_does_not_have_valid_manager");
        }
        for (Drone d : drones.values()) {
            revenue += d.getSales();
            d.resetSales();
        }
    }

    public String toString() {
        return "name: " + name + ", revenue: $" + revenue + ", location: " + location.getName();
    }
}
