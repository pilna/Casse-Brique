package command;

import game.Game;

public class MoveRacketLeft extends Command {

    public MoveRacketLeft(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return this.game.getPlayer().getRacket().canMoveToLeft();
    }

    @Override
    public void apply() {
        this.game.getPlayer().getRacket().moveLeft();
    }

    @Override
    public String toString() {
        return "move the racket to the left";
    }
}
