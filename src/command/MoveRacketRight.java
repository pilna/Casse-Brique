package command;

import game.Game;

public class MoveRacketRight extends Command {

    public MoveRacketRight(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return this.game.getPlayer().getRacket().canMoveToRight();
    }

    @Override
    public void apply() {
        this.game.getPlayer().getRacket().moveRight();
    }

    @Override
    public String toString() {
        return "move the racket to the right";
    }
}
