package command;

import ball.RisingState;
import coordinate.Coordinate;
import game.Game;

public class ThrowBall extends Command {

    public ThrowBall(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return !this.game.getBall().isAlive();
    }

    @Override
    public void apply() {
        Coordinate newCoordinate = this.game.getPlayer().getRacket().getCoordinate();
        this.game.getBall().setCoordinate(newCoordinate);
        this.game.getBall().setDeplacement(new RisingState(this.game.getBall()));
    }

    @Override
    public String toString() {
        return "throw the ball";
    }
}
