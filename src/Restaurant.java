package src;

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
        moneySpent += service.requestPackage(this, droneID, barcode, quantity);
    }

    public String toString() {
        return "name: " + name + ", money_spent: $" + moneySpent +", location: " + location.getName();
    }
}
