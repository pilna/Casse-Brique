package command;

import game.Game;

public abstract class Command {
    protected Game game;

    public Command(Game game) {
        this.game = game;
    }

    abstract public boolean canBeApply();
    abstract public void apply();
    abstract public String toString();
}
