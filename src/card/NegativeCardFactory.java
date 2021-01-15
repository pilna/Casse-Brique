package card;

import effect.Life;
import effect.Score;
import game.Game;

public class NegativeCardFactory extends AbstractCardFactory {

    public NegativeCardFactory(Game game) {
        super(game);
    }

    @Override
    public Card getCard() {
        Card outputCard;
        int lowerBorn = 0;
        int higherBorn = 3;
        int random = (int)(Math.random() * (higherBorn + 1 - lowerBorn)) + lowerBorn;

        switch (random) {
            case 0:
                outputCard = new Card(new Life(this.game,-1));
                break;
            case 1:
                outputCard = new Card(new Score(this.game,-5));
                break;
            default:
                outputCard = new Card(new Score(this.game,-10));
                break;
        }

        return outputCard;
    }
}
