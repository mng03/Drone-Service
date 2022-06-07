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

    public Location(int x, int y, int SpaceLimit) {
        this.x = x;
        this.y = y;
        this.spaceLimit = spaceLimit;
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

    public void setXCoord(int xCoord) {
        x = xCoord;
    }

    public void setYCoord(int yCoord) {
        y = yCoord;
    }

    public void setSpaceLimit(int sL) {
        spaceLimit = sL;
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
