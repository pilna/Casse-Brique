package Brick;

import color.Color;
import coordinate.Coordinate;
import effect.BallDeviation;
import effect.Life;
import effect.Score;
import game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BrickFactory {
    private Game game;

    public BrickFactory(Game game) {
        this.game = game;
    }

    public Brick getBrick(BrickType type, Coordinate coordinate) {
        Brick outputBrick;
        Integer direction;
        Coordinate deplacementVector;

        switch (type) {
            case BONUS:
                outputBrick = new Brick(coordinate, 1, Color.ANSI_RED.getColor());
                outputBrick.addEffect(new Score(this.game, 5));
                outputBrick.addEffect(new Life(this.game, 1));
                break;
            case RESISTANT:
                outputBrick = new Brick(coordinate, 3, Color.ANSI_BLUE.getColor());
                outputBrick.addEffect(new Score(this.game,5));
                direction = new Random().nextBoolean() ? 1 : -1;
                deplacementVector = new Coordinate(direction, 0);
                outputBrick.addEffect(new BallDeviation(game, deplacementVector));
                break;
            case DEADLY:
                outputBrick = new Brick(coordinate, 1, Color.ANSI_CYAN.getColor());
                outputBrick.addEffect(new Life(this.game, -1));
                break;
            case DISRUPTIVE:
                outputBrick = new Brick(coordinate, 1, Color.ANSI_GREEN.getColor());
                outputBrick.addEffect(new Score(this.game, 20));
                int delta = new Random().nextInt(2);
                direction = new Random().nextBoolean() ? delta : -delta;
                deplacementVector = new Coordinate(direction, 0);
                outputBrick.addEffect(new BallDeviation(this.game, deplacementVector));
                break;
            default:
                outputBrick = new Brick(coordinate, 1, Color.ANSI_PURPLE.getColor());
                outputBrick.addEffect(new Score(this.game,5));
                direction = new Random().nextBoolean() ? 1 : -1;
                deplacementVector = new Coordinate(direction, 0);
                outputBrick.addEffect(new BallDeviation(game, deplacementVector));
        }

        return outputBrick;
    }

    public Brick getRandomBrick(Coordinate coordinate) {
        BrickType[] types = BrickType.values();
        BrickType choice = types[new Random().nextInt(types.length)];
        return this.getBrick(choice, coordinate);
    }

    public Brick getRandomBrick(Coordinate coordinate, List<BrickType> excluded) {
        BrickType[] types = BrickType.values();
        ArrayList<BrickType> validTypes = new ArrayList<BrickType>();

        for(BrickType type : types) {
            if(!excluded.contains(type)) {
                validTypes.add(type);
            }
        }

        BrickType choice = validTypes.get(new Random().nextInt(validTypes.size()));
        return this.getBrick(choice, coordinate);
    }
}
