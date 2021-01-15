package player;

import card.Card;

import java.util.ArrayList;

public interface GenerationDeck {
    ArrayList<Card> generate(Integer nbCards);
}
