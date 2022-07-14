package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.TreeMap;

/**
 * Class that defines the general functionality of locations.
 *
 * @author Group 9
 * @version 1.0
 */
public class Location {
    private String name;
    private int x;
    private int y;
    private int spaceLimit;
    private int currSpots;

    public static TreeMap<String, Location> locations = new TreeMap<String, Location>();
    public static ObservableList<String> locationsGUI = FXCollections.observableArrayList();

    public Location(String name, int x, int y, int spaceLimit) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.spaceLimit = spaceLimit;
        this.currSpots = spaceLimit;
    }
    public String getName() {
        return name;
    }
    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public int getSpaceLimit() {
        return spaceLimit;
    }

    public int getCurrSpots() {
        return currSpots;
    }

    public void setXCoord(int xCoord) {
        x = xCoord;
    }

    public void setYCoord(int yCoord) {
        y = yCoord;
    }

    public void addDrone() {
        currSpots--;
    }

    public void removeDrone() {
        currSpots++;
    }

    public boolean equals(Location loc) {
        return this.x == loc.getXCoord() && this.y == loc.getYCoord() && this.name.equals(loc.getName());
    }

    /**
     * Returns the distance between two locations.
     * @param destination
     * @return
     */
    public int calcDistance(Location destination) {
        if (this.equals(destination)) {
            return 0;
        }
        return 1 + (int) Math.floor(Math.sqrt(Math.pow(getXCoord() - destination.getXCoord(), 2)
            + Math.pow(getYCoord() - destination.getYCoord(), 2)));
    }
    public String toString() {
        return "name: " + name + ", (x,y): (" + x + ", " + y + "), space: [" + currSpots + " / " + spaceLimit + "] remaining";
    }
    public static void locationExists(String located_at) throws Exception {
        if (!locations.containsKey(located_at)) {
            throw new Exception("ERROR:location_does_not_exist");
        } 
    }
    public static void makeLocation(String init_name, Integer init_x_coord, Integer init_y_coord, Integer init_space_limit) throws Exception {
        if(init_name.equals("")) {
            throw new Exception("ERROR:location_name_cannot_be_empty");
        } else if (locations.containsKey(init_name)) {
            throw new Exception("ERROR:location_name_already_exists");
        } else if (init_space_limit < 0) {
            throw new Exception("ERROR:location_cannot_have_a_negative_space_limit");
        }
        locations.put(init_name, new Location(init_name, init_x_coord, init_y_coord, init_space_limit));
        locationsGUI.add(init_name);
        FXCollections.sort(locationsGUI);
    }
}
