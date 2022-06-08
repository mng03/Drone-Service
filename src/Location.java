package src;

/**
 * Class that defines the general functionality of locations.
 *
 * @author Group 9
 * @version 1.0
 */
public class Location {
    private int x;
    private int y;
    private int spaceLimit;
    private int currCapacity;

    public Location(int x, int y, int spaceLimit, int currCapacity) {
        this.x = x;
        this.y = y;
        this.spaceLimit = spaceLimit;
        this.currCapacity = currCapacity;
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

    public int getCurrCapacity() {
        return currCapacity;
    }

    public void setXCoord(int xCoord) {
        x = xCoord;
    }

    public void setYCoord(int yCoord) {
        y = yCoord;
    }

    public void setSpaceLimit(int sL) {
        spaceLimit = sL;
    }

    public void setCurrCapacity(int cc) {
        currCapacity = cc;
    }

    public void addDrone() {
        setCurrCapacity(currCapacity + 1);
    }

    public void removeDrone() {
        setCurrCapacity(currCapacity - 1);
    }

    /**
     * Returns the distance between two locations.
     * @param destination
     * @return
     */
    public int calcDistance(Location destination) {
        return 1 + (int) Math.floor(Math.sqrt(Math.pow(getXCoord() - destination.getXCoord(), 2)
            + Math.pow(getYCoord() - destination.getYCoord(), 2)));
    }

    public String toString() {
        return "X-Coordinate: " + x + " Y-Coordinate: " + y + "Drone Space Limit: " + spaceLimit;
    }
}
