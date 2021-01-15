package ball;

import Brick.Brick;
import coordinate.Coordinate;
import game.Hitable;
import racket.Racket;

public class DescendingState extends BallState {
    public DescendingState(Ball ball) {
        super(ball);
    }

    @Override
    public Coordinate nextPosition() {
        Coordinate deplacementVector = new Coordinate(0, 1);
        return this.ball.getCoordinate().add(deplacementVector);
    }

    @Override
    public void update() {
        // do nothing
    }

    @Override
    public void collide(Hitable objectHit) {
        objectHit.collideBall(this.ball);
    }

    @Override
    public void collideBall(Ball b) {
        // nothing to do
    }

    @Override
    public void collideRacket(Racket b) {
        this.ball.setDeplacement(new RisingState(this.ball));
    }

    @Override
    public void collideBrick(Brick b) {
        // nothing to do
    }
}
