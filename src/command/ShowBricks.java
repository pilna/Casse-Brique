package command;

import game.Game;

public class ShowBricks extends Command {

    public ShowBricks(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return this.game.getPlayer().getLifePoint() > 1;
    }

    @Override
    public void apply() {
        this.game.setColoredBricks(true);
    }

    @Override
    public String toString() {
        return "display the type of brick on the screen";
    }
}
