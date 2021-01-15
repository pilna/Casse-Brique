package ball;

import Brick.Brick;
import coordinate.Coordinate;
import game.Hitable;
import racket.Racket;

public class Ball implements Hitable {
    private Coordinate coordinate;
    // width/height can be replace with just 1 coordinate
    private Integer boardWidth;
    private Integer boardHeight;
    private Integer damage;
    private BallState deplacement;

    public Ball(Coordinate coordinate, Integer damage, Integer boardWidth, Integer boardHeight) {
        this.coordinate = coordinate;
        this.damage = damage;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.deplacement = new RisingState(this);
    }

    public Ball(Coordinate coordinate, Integer boardWidth, Integer boardHeight) {
        this(coordinate, 1, boardWidth, boardHeight);
    }

    public void move() {
        if(this.isAlive()) {
            this.deplacement.update();
            this.setCoordinate(this.deplacement.nextPosition());
        }
    }

    public Coordinate getNextPosition() {
        return this.deplacement.nextPosition();
    }

    public void setDeplacement(BallState deplacement) {
        this.deplacement = deplacement;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate newCoordinate) {
        if(newCoordinate.getX() < 0) {
            newCoordinate.setX(0);
        }
        if(boardWidth <= newCoordinate.getX()) {
            newCoordinate.setX(boardWidth - 1);
        }
        if(newCoordinate.getY() < 0) {
            newCoordinate.setY(0);
        }
        this.coordinate = newCoordinate;
    }

    public void applyDeplacementVector(Coordinate vector) {
        this.setCoordinate(this.coordinate.add(vector));
    }

    public boolean isAlive() {
        return this.getCoordinate().getY() < this.boardHeight;
    }

    public char getSymbol() {
        return 'O';
    }

    public String toString() {
        // add color with surrond
        return String.valueOf(this.getSymbol());
    }

    public Integer getDamage() {
        return this.damage;
    }

    public boolean canBeDisplay() {
        return this.isAlive();
    }

    @Override
    public void collide(Hitable objectHit) {
        this.deplacement.collide(objectHit);
    }

    @Override
    public void collideBall(Ball ball) {
        this.deplacement.collideBall(ball);
    }

    @Override
    public void collideRacket(Racket racket) {
        this.deplacement.collideRacket(racket);
    }

    @Override
    public void collideBrick(Brick brick) {
        this.deplacement.collideBrick(brick);
    }

    public BallState getDeplacement() {
        return this.deplacement;
    }
}
