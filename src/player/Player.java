package player;

import java.util.ArrayList;
import java.util.Random;

import card.AbstractCardFactory;
import card.Card;
import card.NegativeCardFactory;
import card.PositiveCardFactory;
import command.*;
import coordinate.Coordinate;
import game.Game;
import racket.Racket;
import utils.Utils;


public class Player {
    private Game game;
    private String name;
    private Integer lifePoint;
    private Racket racket;
    private Integer score;
    private ArrayList<Command> commands;
    private ArrayList<Card> cards;
    private GenerationDeck generationDeck;

    public Player(Game game, String name, Integer lifePoint) {
        this.game = game;
        this.name = name;
        this.score = 0;
        this.racket = new Racket(new Coordinate(this.game.getWidth() / 2, this.game.getHeight() - 1),
                this.game.getWidth());
        this.setLifePoint(lifePoint);
    }

    public void setGenerationDeck(GenerationDeck newGenerationDeck) {
        this.generationDeck = newGenerationDeck;
    }

    public void initGenerationDeck() {
        switch (this.game.getDifficulty()) {
            case EASY:
                this.setGenerationDeck(new EasyDeckGeneration());
                break;
            case MEDIUM:
                this.setGenerationDeck(new MediumDeckGeneration());
                break;
            default:
                this.setGenerationDeck(new HardDeckGeneration());
        }
    }

    public void generateDeck()  {
        this.setCards(this.generationDeck.generate(6));
    }

    public Player(Game game, String name) {
        this(game, name, 3);
    }

    public Player(Game game) {
        this(game, "anonymous", 3);
    }

    public Integer getScore() {
        return this.score;
    }

    public void setLifePoint(Integer newLifePoint) {
        if(newLifePoint < 0) {
            newLifePoint = 0;
        }
        this.lifePoint = newLifePoint;
        this.updateCommands();
    }

    public void addLifePoint(Integer amountLifePoint) {
        this.setLifePoint(this.lifePoint + amountLifePoint);
    }

    public void setScore(Integer newScore) {
        this.score = newScore;
    }

    public void addScore(Integer pointToAdd) {
        this.setScore(this.score + pointToAdd);
    }

    public Racket getRacket() {
        return this.racket;
    }

    public void setCommands(ArrayList<Command> newListOfCommand) {
        this.commands = newListOfCommand;
    }

    public Integer getLifePoint() {
        return this.lifePoint;
    }

    public void updateCommands() {
        ArrayList<Command> listOfCommandAvailable;

        switch (this.lifePoint) {
            case 1:
                listOfCommandAvailable = new ArrayList<Command>();
                listOfCommandAvailable.add(new MoveRacketLeft(this.game));
                listOfCommandAvailable.add(new MoveRacketRight(this.game));
                listOfCommandAvailable.add(new ThrowBall(this.game));
                listOfCommandAvailable.add(new DoNothing(this.game));
                break;
            case 2:
                listOfCommandAvailable = new ArrayList<Command>();
                listOfCommandAvailable.add(new MoveRacketLeft(this.game));
                listOfCommandAvailable.add(new MoveRacketRight(this.game));
                listOfCommandAvailable.add(new ThrowBall(this.game));
                listOfCommandAvailable.add(new DoNothing(this.game));
                listOfCommandAvailable.add(new ShowBricks(this.game));
                listOfCommandAvailable.add(new UseCard(this.game));
                break;
            default:
                listOfCommandAvailable = new ArrayList<Command>();
                listOfCommandAvailable.add(new MoveRacketLeft(this.game));
                listOfCommandAvailable.add(new MoveRacketRight(this.game));
                listOfCommandAvailable.add(new ThrowBall(this.game));
                listOfCommandAvailable.add(new DoNothing(this.game));
                listOfCommandAvailable.add(new ShowBricks(this.game));
        }

        this.setCommands(listOfCommandAvailable);
    }

    public String getName() {
        return this.name;
    }

    public void play() {
        ArrayList<Command> availableCommand = new ArrayList<Command>();

        for(Command command : this.commands) {
            if(command.canBeApply()) {
                availableCommand.add(command);
            }
        }

        System.out.println("choose a command among the following:");
        Integer count = 0;

        for(Command command : availableCommand) {
            count++;
            System.out.println('\t' + String.format("%d :", count) + command.toString());
        }

        Integer choice = Utils.valueBetween(1, count);
        availableCommand.get(choice - 1).apply();
    }

    public boolean isAlive() {
        return this.getLifePoint() == 0;
    }

    public void setCards(ArrayList<Card> cardsList) {
        this.cards = cardsList;
    }

    public void addCard(Card cardToAdd) {
        this.cards.add(cardToAdd);
    }

    public void useCard() {
        System.out.println(this.cards);
        int positionOfCard = new Random().nextInt(this.cards.size());
        System.out.println(positionOfCard);
        Card selected = this.cards.get(positionOfCard);
        System.out.println(selected);
        selected.use();
        this.cards.remove(positionOfCard);
        AbstractCardFactory cardFactory = selected.isBonus() ? new PositiveCardFactory(this.game) : new NegativeCardFactory((this.game));
        this.addCard(cardFactory.getCard());
    }
}
