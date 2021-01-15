package player;

import card.AbstractCardFactory;
import card.Card;
import card.NegativeCardFactory;
import card.PositiveCardFactory;
import game.Game;

import java.util.ArrayList;

public class MediumDeckGeneration implements GenerationDeck {
    @Override
    public ArrayList<Card> generate(Integer nbCards) {
        int nbPositiveCards = nbCards - (nbCards / 2);
        ArrayList<Card> cards = new ArrayList<Card>();
        AbstractCardFactory factory = new PositiveCardFactory(Game.getInstance());

        for(int i = 0; i < nbPositiveCards; i++) {
            cards.add(factory.getCard());
        }

        factory = new NegativeCardFactory(Game.getInstance());

        for(int i = 0; i < nbCards / 2; i++) {
            cards.add(factory.getCard());
        }

        return cards;
    }
}
