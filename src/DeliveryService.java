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
    public static TreeMap<String, DeliveryService> deliveryServices = new TreeMap<String, DeliveryService>();

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

    public void purchaseDrone(int droneID, int capacity, int fuel) throws Exception {
        if (drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_with_tag_already_exists_in_service");
        } else if (capacity < 0) {
            throw new Exception("ERROR:drone_capacity_cannot_be_negative");
        } else if (fuel < 0) {
            throw new Exception("ERROR:drone_fuel_cannot_be_negative");
        } else if (location.getCurrSpots() == 0) {
            throw new Exception("ERROR:not_enough_space_to_create_new_drone");
        } else {
            drones.put(droneID, new Drone(droneID, capacity, fuel, location));
        }
    }

    public void loadFuel(int droneID, int petrol) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else if (workerCount < 1) {
            throw new Exception("ERROR:not_enough_workers_to_complete_task");
        } else {
            drones.get(droneID).addFuel(petrol);
        }
    }

    public void loadPackage(int droneID, IngredientInfo ingredient, int quantity, int unitPrice) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        } else if (workerCount < 1) {
            throw new Exception("ERROR:not_enough_workers_to_complete_task");
        } else if (quantity <= 0) {
            throw new Exception("ERROR:ingredient_must_be_have_a_positive_quantity");
        } else if (unitPrice < 0) {
            throw new Exception("ERROR:unit_price_cannot_be_negative");
        } else {
            drones.get(droneID).loadPackage(new Package(ingredient, unitPrice, quantity));
        }
    }

    public void flyDrone(int droneID, Location destination) throws Exception {
        if (!drones.containsKey(droneID)) {
            throw new Exception("ERROR:drone_does_not_exist");
        }
        drones.get(droneID).fly(destination);
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
        } else if (person.equals(manager)) {
            throw new Exception("ERROR:this_person_is_already_managing_this_service");
        }
        person.becomeManager(this);
        if (manager != null) {
            manager.stopManaging();
            workerCount++;
        }
        manager = person;
        workerCount--;
    }

    public void train(Person person, String license, int experience) throws Exception {
        if (!employees.containsKey(person.getUsername())) {
            throw new Exception("ERROR:employee_does_not_work_for_this_service");
        } else if (manager == null) {
            throw new Exception("ERROR:delivery_service_does_not_have_valid_manager");
        } else if (experience < 0) {
            throw new Exception("ERROR:experience_cannot_be_negative");
        }
        Pilot newPilot = person.becomePilot(license, experience);
        employees.remove(person.getUsername());
        employees.put(newPilot.getUsername(), newPilot);
        Person.people.put(newPilot.getUsername(), newPilot);
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
    public static void makeService(String init_name, Integer init_revenue, String located_at) throws Exception {
        if(init_name.equals("")) {
            throw new Exception("ERROR:delivery_service_name_cannot_be_empty");
        } else if (deliveryServices.containsKey(init_name)) {
            throw new Exception("ERROR:delivery_service_name_already_exists");
        } else if (init_revenue < 0) {
            throw new Exception("ERROR:revenue_cannot_be_negative");
        } 
        Location.locationExists(located_at);
        deliveryServices.put(init_name, new DeliveryService(init_name, init_revenue, Location.locations.get(located_at)));
    }
    public static void serviceExists(String service_name) throws Exception {
        if (!deliveryServices.containsKey(service_name)) {
            throw new Exception("ERROR:service_does_not_exist");
        }
    }
}
