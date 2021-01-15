package racket;

import Brick.Brick;
import ball.Ball;
import coordinate.Coordinate;
import game.Hitable;

import java.util.ArrayList;

public class Racket implements Hitable {
    private Coordinate coordinate;
    private Integer boardWidth;
    private Integer width;

    public Racket(Coordinate coordinate, Integer boardWidth, Integer width) {
        this.coordinate = coordinate;
        this.boardWidth = boardWidth;
        this.width = width;
    }

    public Racket(Coordinate coordinate, Integer boardWidth) {
        this(coordinate, boardWidth,1);
    }

    public Racket(Integer boardWidth, Integer height, Integer width) {
        this(new Coordinate((boardWidth - width) / 2, height - 1), boardWidth, width);
    }

    public Racket(Integer boardWidth, Integer height) {
        this(new Coordinate((boardWidth - 1) / 2, height - 1), boardWidth, 1);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate newCoordinate) {
        this.coordinate = newCoordinate;
    }

    public Boolean canMoveToRight() {
        Coordinate rightVector = new Coordinate(1, 0);
        // taking into account the width of the racket
        Coordinate deltaX = new Coordinate(this.width - 1, 0);
        return this.getCoordinate().add(rightVector).add(deltaX).getX() < boardWidth;
    }

    public Boolean canMoveToLeft() {
        Coordinate leftVector = new Coordinate(-1, 0);
        return this.getCoordinate().add(leftVector).getX() >= 0;
    }

    public void moveRight() {
        Coordinate rightVector = new Coordinate(1, 0);
        this.setCoordinate(this.coordinate.add(rightVector));
    }

    public void moveLeft() {
        Coordinate leftVector = new Coordinate(-1, 0);
        this.setCoordinate(this.coordinate.add(leftVector));
    }

    public ArrayList<Coordinate> getAllCoordinate() {
        // refacto ???
        ArrayList<Coordinate> output = new ArrayList<Coordinate>();

        for(int i = 0; i < this.width; i++) {
            output.add(new Coordinate(this.getCoordinate().getX() + i, this.getCoordinate().getY()));
        }

        return output;
    }

    public char getSymbol() {
        return '-';
    }

    public String toString() {
        return String.valueOf(this.getSymbol());
    }

    @Override
    public void collide(Hitable objectHit) {
        objectHit.collideRacket(this);
    }

    @Override
    public void collideBall(Ball b) {
        // do nothing
    }

    @Override
    public void collideRacket(Racket b) {
        // never arrive
    }

    @Override
    public void collideBrick(Brick b) {
        // never arrive
    }
}
