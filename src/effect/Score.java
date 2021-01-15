package effect;

import game.Game;

public class Score extends Effect {
    private Integer gain;

    public Score(Game game, Integer gain) {
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
        this.game.getPlayer().addScore(this.gain);
    }

    @Override
    public String toString() {
        String output = this.gain >= 0 ? "add %d score point to the player": "remove %d score point to the player";
        return String.format(output, this.gain);
    }
}
