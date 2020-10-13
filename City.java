public class City {
    private int index;
    private double x;
    private double y;

    public City(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    /**
     * measure distance between two cities
     * @param another another city
     * @return distance
     */
    public double distanceTo(City another) {
        double deltaX = this.x - another.x;
        double deltaY = this.y - another.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "City{" +
                "index=" + index +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
