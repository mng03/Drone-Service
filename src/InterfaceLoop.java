package src;

import java.util.Scanner;
import java.util.HashMap;

public class InterfaceLoop {
    private HashMap<String, Location> locations;
    private HashMap<String, DeliveryService> deliveryServices;
    private HashMap<String, Restaurant> restaurants;

    InterfaceLoop() { 
        locations = new HashMap<String, Location>();
        deliveryServices = new HashMap<String, DeliveryService>();
        restaurants = new HashMap<String, Restaurant>();
    }

    void makeIngredient(String init_barcode, String init_name, Integer init_weight) { }

    void displayIngredients() { }

    void makeLocation(String init_name, Integer init_x_coord, Integer init_y_coord, Integer init_space_limit) {
        if(init_name.equals("")) {
            System.out.println("ERROR:location_name_cannot_be_empty");
        } else if (locations.containsKey(init_name)) {
            System.out.println("ERROR:location_name_already_exists");
        } else if (init_space_limit < 0) {
            System.out.println("ERROR:location_cannot_have_a_negative_space_limit");
        } else {
            locations.put(init_name, new Location(init_name, init_x_coord, init_y_coord, init_space_limit));
            System.out.println("OK:change_completed");
        }
    }

    void displayLocations() {
        for (Location location : locations.values()) {
            System.out.println(location);
        }
        System.out.println("OK:display_completed");
    }

    void checkDistance(String departure_point, String arrival_point) { }

    void makeDeliveryService(String init_name, Integer init_revenue, String located_at) {
        //TODO: Is name unique or the ID?
        if(init_name.equals("")) {
            System.out.println("ERROR:delivery_service_name_cannot_be_empty");
        } else if (deliveryServices.containsKey(init_name)) {
            System.out.println("ERROR:delivery_service_name_already_exists");
        } else if (!locations.containsKey(located_at)) {
            System.out.println("ERROR:location_does_not_exist");
            //TODO: Does init_revenue need to be >= 0?
        } else {
            deliveryServices.put(init_name, new DeliveryService(deliveryServices.size(), init_name, locations.get(located_at)));
            System.out.println("OK:change_completed");
        }
    }

    void displayServices() {
        for (DeliveryService deliveryService : deliveryServices.values()) {
            System.out.println(deliveryService);
        }
        System.out.println("OK:display_completed");
    }

    void makeRestaurant(String init_name, String located_at) {
        if(init_name.equals("")) {
            System.out.println("ERROR:restaurant_name_cannot_be_empty");
        } else if (restaurants.containsKey(init_name)) {
            System.out.println("ERROR:restaurant_name_already_exists");
        } else if (!locations.containsKey(located_at)) {
            System.out.println("ERROR:location_does_not_exist");
        } else {
            restaurants.put(init_name, new Restaurant(init_name, locations.get(located_at)));
            System.out.println("OK:change_completed");
        }
    }

    void displayRestaurants() {
        for (Restaurant restaurant : restaurants.values()) {
            System.out.println(restaurant);
        }
        System.out.println("OK:display_completed");
    }

    void makeDrone(String service_name, Integer init_tag, Integer init_capacity, Integer init_fuel) { }

    void displayDrones(String service_name) { }

    void displayAllDrones() { }

    void flyDrone(String service_name, Integer drone_tag, String destination_name) { }

    void loadIngredient(String service_name, Integer drone_tag, String barcode, Integer quantity, Integer unit_price) { }

    void loadFuel(String service_name, Integer drone_tag, Integer petrol) { }

    void purchaseIngredient(String restaurant_name, String service_name, Integer drone_tag, String barcode, Integer quantity) { }

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].indexOf("//") == 0) {
                    // deliberate empty body to recognize and skip over comments

                } else if (tokens[0].equals("make_ingredient")) {
                    makeIngredient(tokens[1], tokens[2], Integer.parseInt(tokens[3]));

                } else if (tokens[0].equals("display_ingredients")) {
                    displayIngredients();

                } else if (tokens[0].equals("make_location")) {
                    makeLocation(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));

                } else if (tokens[0].equals("display_locations")) {
                    displayLocations();

                } else if (tokens[0].equals("check_distance")) {
                    checkDistance(tokens[1], tokens[2]);

                } else if (tokens[0].equals("make_service")) {
                    makeDeliveryService(tokens[1], Integer.parseInt(tokens[2]), tokens[3]);

                } else if (tokens[0].equals("display_services")) {
                    displayServices();

                } else if (tokens[0].equals("make_restaurant")) {
                    makeRestaurant(tokens[1], tokens[2]);

                } else if (tokens[0].equals("display_restaurants")) {
                    displayRestaurants();

                } else if (tokens[0].equals("make_drone")) {
                    makeDrone(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));

                } else if (tokens[0].equals("display_drones")) {
                    displayDrones(tokens[1]);

                } else if (tokens[0].equals("display_all_drones")) {
                    displayAllDrones();

                } else if (tokens[0].equals("fly_drone")) {
                    flyDrone(tokens[1], Integer.parseInt(tokens[2]), tokens[3]);

                } else if (tokens[0].equals("load_ingredient")) {
                    loadIngredient(tokens[1], Integer.parseInt(tokens[2]), tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));

                } else if (tokens[0].equals("load_fuel")) {
                    loadFuel(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

                } else if (tokens[0].equals("purchase_ingredient")) {
                    purchaseIngredient(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], Integer.parseInt(tokens[5]));

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }

    void displayMessage(String status, String text_output) {
        System.out.println(status.toUpperCase() + ":" + text_output.toLowerCase());
    }
}
