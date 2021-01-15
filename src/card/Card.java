package card;

import effect.Effect;

public class Card {
    private Effect effect;

    public Card(Effect effect) {
        this.effect = effect;
    }

    public boolean canBeUse() {
        return this.effect.canBeApply();
    }

    public void use() {
        this.effect.apply();
    }

    public boolean isBonus() {
        return this.effect.isBonus();
    }

    public String toString() {
        return this.effect.toString();
    }
}
