package ball;

import coordinate.Coordinate;
import game.Hitable;

public abstract class BallState implements Hitable {
    protected Ball ball;

    public BallState(Ball ball) {
        this.ball = ball;
    }

    abstract public Coordinate nextPosition();
    abstract public void update();
}
