package src;

import java.util.Scanner;

public class InterfaceLoop {

    void makeIngredient(String init_barcode, String init_name, Integer init_weight) {
        try {
            IngredientInfo.makeInfo(init_barcode, init_name, init_weight);
            System.out.println("OK:ingredient_created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayIngredients() {
        for (IngredientInfo ingredientInfo : IngredientInfo.ingredientInfos.values()) {
            System.out.println(ingredientInfo);
        }
        System.out.println("OK:display_completed");
    }

    void makeLocation(String init_name, Integer init_x_coord, Integer init_y_coord, Integer init_space_limit) {
        try {
            Location.makeLocation(init_name, init_x_coord, init_y_coord, init_space_limit);
            System.out.println("OK:location_created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayLocations() {
        for (Location location : Location.locations.values()) {
            System.out.println(location);
        }
        System.out.println("OK:display_completed");
    }

    void checkDistance(String departure_point, String arrival_point) {
        if (!Location.locations.containsKey(departure_point)) {
            System.out.println("ERROR:departure_location_does_not_exist");
        } else if (!Location.locations.containsKey(arrival_point)) {
            System.out.println("ERROR:arrival_location_does_not_exist");
        } else {
            System.out.println("OK:distance = " + Location.locations.get(departure_point).calcDistance(Location.locations.get(arrival_point)));
        }
    }

    void makeDeliveryService(String init_name, Integer init_revenue, String located_at) {
        try {
            DeliveryService.makeService(init_name, init_revenue, located_at);
            System.out.println("OK:delivery_service_created");
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }

    void displayServices() {
        for (DeliveryService deliveryService : DeliveryService.deliveryServices.values()) {
            System.out.println(deliveryService);
        }
        System.out.println("OK:display_completed");
    }

    void makeRestaurant(String init_name, String located_at) {
        try {
            Restaurant.makeRestaurant(init_name, located_at);
            System.out.println("OK:restaurant_created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayRestaurants() {
        for (Restaurant restaurant : Restaurant.restaurants.values()) {
            System.out.println(restaurant);
        }
        System.out.println("OK:display_completed");
    }

    void makeDrone(String service_name, Integer init_tag, Integer init_capacity, Integer init_fuel) {
        try {
            DeliveryService.serviceExists(service_name);
            DeliveryService.deliveryServices.get(service_name).purchaseDrone(init_tag, init_capacity, init_fuel);
            System.out.println("OK:drone_created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayDrones(String service_name) {
        try {
            DeliveryService.serviceExists(service_name);
            for (Drone drone : DeliveryService.deliveryServices.get(service_name).getDrones().values()) {
                System.out.println(drone);
            }
            System.out.println("OK:display_completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayAllDrones() {
        for (DeliveryService deliveryService : DeliveryService.deliveryServices.values()) {
            System.out.println("service name [" + deliveryService.getName() + "] drones:");
            for (Drone drone : deliveryService.getDrones().values()) {
                System.out.println(drone);
            }
        }
        System.out.println("OK:display_completed");
    }

    void flyDrone(String service_name, Integer drone_tag, String destination_name) {
        try {
            DeliveryService.serviceExists(service_name);
            Location.locationExists(destination_name);
            DeliveryService.deliveryServices.get(service_name).flyDrone(drone_tag, Location.locations.get(destination_name));
            System.out.println("OK:change_completed");
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }

    void loadIngredient(String service_name, Integer drone_tag, String barcode, Integer quantity, Integer unit_price) {
        try {
            DeliveryService.serviceExists(service_name);
            IngredientInfo.infoExists(barcode);
            DeliveryService.deliveryServices.get(service_name).loadPackage(drone_tag, IngredientInfo.ingredientInfos.get(barcode), quantity, unit_price);
            System.out.println("OK:change_completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void loadFuel(String service_name, Integer drone_tag, Integer petrol) {
        try {
            DeliveryService.serviceExists(service_name);
            DeliveryService.deliveryServices.get(service_name).loadFuel(drone_tag, petrol);
            System.out.println("OK:change_completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void purchaseIngredient(String restaurant_name, String service_name, Integer drone_tag, String barcode, Integer quantity) {
        try {
            Restaurant.restaurantExists(restaurant_name);
            IngredientInfo.infoExists(barcode);
            DeliveryService.serviceExists(service_name);
            Restaurant.restaurants.get(restaurant_name).purchasePackage(DeliveryService.deliveryServices.get(service_name), drone_tag, barcode, quantity);
            System.out.println("OK:change_completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void makePerson(String init_username, String init_fname, String init_lname, Integer init_year, Integer init_month, Integer init_date, String init_address) {
        try {
            Person.makePerson(init_username, init_fname, init_lname, init_year, init_month, init_date, init_address);
            System.out.println("OK:person_created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayPersons() {
        //TODO: Retest for pilot
        for (Person person : Person.people.values()) {
            System.out.println(person);
        }
        System.out.println("OK:display_completed");
    }

    void hireWorker(String service_name, String user_name) {
        //TODO: Retest for pilot
        try {
            DeliveryService.serviceExists(service_name);
            Person.personExists(user_name);
            DeliveryService.deliveryServices.get(service_name).hire(Person.people.get(user_name));
            System.out.println("OK:new_employee_has_been_hired");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void fireWorker(String service_name, String user_name) {
        //TODO: Retest for pilot
        try {
            DeliveryService.serviceExists(service_name);
            Person.personExists(user_name);
            DeliveryService.deliveryServices.get(service_name).fire(Person.people.get(user_name));
            System.out.println("OK:employee_has_been_fired");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void appointManager(String service_name, String user_name) {
        //TODO: Retest for pilot
        try {
            DeliveryService.serviceExists(service_name);
            Person.personExists(user_name);
            DeliveryService.deliveryServices.get(service_name).makeManager(Person.people.get(user_name));
            System.out.println("OK:employee_has_been_appointed_manager");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void trainPilot(String service_name, String user_name, String init_license, Integer init_experience) {
        //TODO: Test
        try {
            DeliveryService.serviceExists(service_name);
            Person.personExists(user_name);
            DeliveryService.deliveryServices.get(service_name).train(Person.people.get(user_name), init_license, init_experience);
            System.out.println("OK:pilot_has_been_trained");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void appointPilot(String service_name, String user_name, Integer drone_tag) {

    }

    void joinSwarm(String service_name, Integer lead_drone_tag, Integer swarm_drone_tag) {

    }

    void leaveSwarm(String service_name, Integer swarm_drone_tag) {

    }

    void collectRevenue(String service_name) {

    }

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
                System.out.println("\n> " + wholeInputLine);
                if (tokens[0].indexOf("//") == 0) {
                    // deliberate empty body to recognize and skip over comments
                    // these comments ONLY work if they are at the front of the line - NOT at the middle nor end of the line
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
                } else if (tokens[0].equals("make_person")) {
                    makePerson(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                } else if (tokens[0].equals("display_persons")) {
                    displayPersons();
                } else if (tokens[0].equals("hire_worker")) {
                    hireWorker(tokens[1], tokens[2]);
                } else if (tokens[0].equals("fire_worker")) {
                    fireWorker(tokens[1], tokens[2]);
                } else if (tokens[0].equals("appoint_manager")) {
                    appointManager(tokens[1], tokens[2]);
                } else if (tokens[0].equals("train_pilot")) {
                    trainPilot(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("appoint_pilot")) {
                    appointPilot(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("join_swarm")) {
                    joinSwarm(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("leave_swarm")) {
                    leaveSwarm(tokens[1], Integer.parseInt(tokens[2]));
                } else if (tokens[0].equals("collect_revenue")) {
                    collectRevenue(tokens[1]);
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
