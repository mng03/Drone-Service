package src;

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
}
