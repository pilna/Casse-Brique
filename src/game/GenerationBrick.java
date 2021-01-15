package game;

import Brick.Brick;

import java.util.ArrayList;

public interface GenerationBrick {
    public ArrayList<Brick> generate(Integer width, Integer height);
}
