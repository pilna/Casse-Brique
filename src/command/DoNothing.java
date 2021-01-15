package command;

import game.Game;

public class DoNothing extends Command {

    public DoNothing(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return true;
    }

    @Override
    public void apply() {
        // Do nothing
    }

    @Override
    public String toString() {
        return "pass your turn";
    }
}
