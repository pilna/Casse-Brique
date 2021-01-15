package ball;

import Brick.Brick;
import coordinate.Coordinate;
import game.Hitable;
import racket.Racket;

public class RisingState extends BallState {
    public RisingState(Ball ball) {
        super(ball);
    }

    @Override
    public Coordinate nextPosition() {
        Coordinate deplacementVector = new Coordinate(0, -1);
        return this.ball.getCoordinate().add(deplacementVector);
    }

    @Override
    public void update() {
        // if the ball exit the board by the top we change to descending state
        if(this.ball.getNextPosition().getY() < 0) {
            this.ball.setCoordinate(this.ball.getNextPosition());
            this.ball.setDeplacement(new DescendingState(this.ball));
        }
    }

    @Override
    public void collide(Hitable objectHit) {
        objectHit.collideBall(this.ball);
    }

    @Override
    public void collideBall(Ball ball) {
        this.ball.setDeplacement(new DescendingState(this.ball));
    }

    @Override
    public void collideRacket(Racket racket) {
        this.ball.setDeplacement(new DescendingState(this.ball));
    }

    @Override
    public void collideBrick(Brick brick) {
        this.ball.setDeplacement(new DescendingState(this.ball));
    }
}
