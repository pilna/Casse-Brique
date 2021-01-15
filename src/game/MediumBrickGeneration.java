package game;

import Brick.*;
import coordinate.Coordinate;

import java.util.ArrayList;
import java.util.Collections;

public class MediumBrickGeneration implements GenerationBrick {
    @Override
    public ArrayList<Brick> generate(Integer width, Integer height) {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        BrickFactory factory = new BrickFactory(Game.getInstance());
        ArrayList<Coordinate> availableCoordinate = new ArrayList<Coordinate>();

        for(int y = 0; y < height - 3; y++) {
            for (int x = 0; x < width; x++) {
                availableCoordinate.add(new Coordinate(x, y));
            }
        }

        Collections.shuffle(availableCoordinate);

        int nbDeadly = (int) Math.round(0.1 * (availableCoordinate.size() + 0.));
        int temp = 0;

        for(Coordinate coordinate : availableCoordinate) {
            if(temp < nbDeadly) {
                bricks.add(factory.getBrick(BrickType.DEADLY, coordinate));
                temp++;
            } else {
                bricks.add(factory.getRandomBrick(coordinate, new ArrayList<BrickType>() {{
                    add(BrickType.DEADLY);
                }}));
            }
        }

        return bricks;
    }
}
