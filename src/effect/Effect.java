package effect;

import game.Game;

public abstract class Effect {
    protected Game game;

    public Effect(Game game) {
        this.game = game;
    }

    public abstract boolean canBeApply();
    public abstract boolean isBonus();
    public abstract void apply();
    public abstract String toString();
}
