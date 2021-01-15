package game;

import Brick.Brick;
import ball.Ball;
import racket.Racket;

public interface Hitable {
    void collide(Hitable objectHit);
    void collideBall(Ball ball);
    void collideRacket(Racket b);
    void collideBrick(Brick b);
}
