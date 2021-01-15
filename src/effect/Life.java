package effect;

import game.Game;

public class Life extends Effect {
    private Integer gain;

    public Life(Game game, Integer gain) {
        super(game);
        this.gain = gain;
    }

    @Override
    public boolean canBeApply() {
        return true;
    }

    @Override
    public boolean isBonus() {
        return this.gain >= 0;
    }

    @Override
    public void apply() {
        this.game.getPlayer().addLifePoint(this.gain);
    }

    @Override
    public String toString() {
        String output = this.gain >= 0 ? "add %d life point to the player": "remove %d life point to the player";
        return String.format(output, this.gain);
    }
}
