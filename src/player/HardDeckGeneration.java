package player;

import card.AbstractCardFactory;
import card.Card;
import card.NegativeCardFactory;
import card.PositiveCardFactory;
import game.Game;

import java.util.ArrayList;

public class HardDeckGeneration implements GenerationDeck {
    @Override
    public ArrayList<Card> generate(Integer nbCards) {
        int nbNegativeCards = nbCards - (nbCards / 3);
        ArrayList<Card> cards = new ArrayList<Card>();
        AbstractCardFactory factory = new PositiveCardFactory(Game.getInstance());

        for(int i = 0; i < nbCards / 3; i++) {
            cards.add(factory.getCard());
        }

        factory = new NegativeCardFactory(Game.getInstance());

        for(int i = 0; i < nbNegativeCards; i++) {
            cards.add(factory.getCard());
        }

        return cards;
    }
}
