package card;

import game.Game;

public abstract class AbstractCardFactory {
    protected Game game;

    public AbstractCardFactory(Game game) {
        this.game = game;
    }

    abstract public Card getCard();
}
