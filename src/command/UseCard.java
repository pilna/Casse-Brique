package command;

import game.Game;

public class UseCard extends Command {

    public UseCard(Game game) {
        super(game);
    }

    @Override
    public boolean canBeApply() {
        return this.game.getPlayer().getLifePoint() == 2;
    }

    @Override
    public void apply() {
        this.game.getPlayer().useCard();
    }

    @Override
    public String toString() {
        return "use a random card, it can be a positive or negative card";
    }
}
