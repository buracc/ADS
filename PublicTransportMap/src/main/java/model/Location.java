package model;

public class Location {

    private static final double TRAVEL_TIME = 1.5;
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double travelTime(Location to) {
        return Math.sqrt(Math.pow(to.getX() - x, 2) + Math.pow(to.getY() - y, 2));
    }

    public int getTravelledSquares(Location to) {
        int xDiff = Math.abs(x - to.getX());
        int yDiff = Math.abs(y - to.getY());
        return xDiff + yDiff;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
