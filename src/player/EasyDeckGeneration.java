package player;

import card.Card;
import card.PositiveCardFactory;
import game.Game;

import java.util.ArrayList;

public class EasyDeckGeneration implements GenerationDeck {
    @Override
    public ArrayList<Card> generate(Integer nbCards) {
        ArrayList<Card> cards = new ArrayList<Card>();
        PositiveCardFactory factory = new PositiveCardFactory(Game.getInstance());

        for(int i = 0; i < nbCards; i++) {
            cards.add(factory.getCard());
        }

        return cards;
    }
}
