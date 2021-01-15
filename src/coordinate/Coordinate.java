package coordinate;

public class Coordinate {
    private Integer x;
    private Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate add(Coordinate target) {
        Coordinate newCoordinate;
        newCoordinate = new Coordinate(this.getX() + target.getX(), this.getY() + target.getY());
        return newCoordinate;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public boolean equals(Coordinate target) {
        return this.getX().equals(target.getX()) && this.getY().equals(target.getY());
    }

    public String toString() {
        return "{ x: " + this.getX() + ", y: " + this.getY() + " }";
    }
}
