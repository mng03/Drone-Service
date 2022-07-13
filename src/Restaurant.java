package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.TreeMap;

/**
 * Class that defines the specific functionality of restaurants.
 *
 * @author Group 9
 * @version 1.0
 */
public class Restaurant {
    private String name;
    private int moneySpent;
    private Location location;

    public static TreeMap<String, Restaurant> restaurants = new TreeMap<String, Restaurant>();
    public static ObservableList<String> restaurantsGUI = FXCollections.observableArrayList();

    public Restaurant(String name, Location location) {
        this.name = name;
        this.location = location;
        moneySpent = 0;
    }

    public String getName() {
        return name;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String n) {
        name = n;
    }

    public void setMoneySpent(int ms) {
        moneySpent = ms;
    }

    public void setLocation(Location loc) {
        location = loc;
    }

    public void incrementMoneySpent(int moreMoney) {
        setMoneySpent(moreMoney + moneySpent);
    }

    public void purchasePackage(DeliveryService service, int droneID, String barcode, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("ERROR:must_purchase_positive_quantity");
        } 
        moneySpent += service.requestPackage(this, droneID, barcode, quantity);
    }

    public String toString() {
        return "name: " + name + ", money_spent: $" + moneySpent +", location: " + location.getName();
    }
    public static void makeRestaurant(String init_name, String located_at) throws Exception {
        if(init_name.equals("")) {
            throw new Exception("ERROR:restaurant_name_cannot_be_empty");
        } else if (restaurants.containsKey(init_name)) {
            throw new Exception("ERROR:restaurant_name_already_exists");
        }
        Location.locationExists(located_at);
        restaurants.put(init_name, new Restaurant(init_name, Location.locations.get(located_at)));
        restaurantsGUI.add(init_name);
        FXCollections.sort(restaurantsGUI);
    }
    public static void restaurantExists(String restaurant_name) throws Exception {
        if (!restaurants.containsKey(restaurant_name)) {
            throw new Exception("ERROR:restaurant_does_not_exist");
        }
    }
}
