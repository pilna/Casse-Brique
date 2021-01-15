package effect;

import coordinate.Coordinate;
import game.Game;

public class BallDeviation extends Effect {
    private Coordinate deviationVector;

    public BallDeviation(Game game, Coordinate deviationVector) {
        super(game);
        this.deviationVector = deviationVector;
    }

    @Override
    public boolean canBeApply() {
        return true;
    }

    @Override
    public boolean isBonus() {
        return true;
    }

    @Override
    public void apply() {
        this.game.getBall().applyDeplacementVector(this.deviationVector);
    }

    @Override
    public String toString() {
        return null;
    }
}
