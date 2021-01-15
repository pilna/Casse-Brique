package game;

import Brick.*;
import ball.Ball;
import coordinate.Coordinate;
import player.Player;
import racket.Racket;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    public static Game game = new Game();
    private Difficulty difficulty;
    private Ball ball;
    private Integer width = 20;
    private Integer height = 8;
    private Player player;
    private Integer level = 1;
    private ArrayList<Brick> bricks;
    private GenerationBrick generationBrick;
    private boolean isColoredBricks = false;
    private Integer nbAugmentedBricks = 0;

    private Game() {
        this.ball = new Ball(new Coordinate(10, this.height + 1),  width, height);
        this.player = new Player(this, "name", 3);
    }

    public static Game getInstance() {
        return game;
    }

    public void selectDifficulty() {
        System.out.println("select your difficulty : ");
        Integer index = 0;

        for(Difficulty difficulty : Difficulty.values()) {
            index++;
            System.out.println(String.format("\t%d: " + difficulty, index));
        }

        Integer choice = Utils.valueBetween(1, index);
        this.setDifficulty(Difficulty.values()[choice - 1]);
    }

    public void createBricks() {
        this.bricks = this.generationBrick.generate(this.width, this.height);
    }

    public ArrayList<ArrayList<String>> addBrickToBoard(ArrayList<ArrayList<String>> board, boolean isColored) {
        for(Brick brick : this.bricks) {
            if(brick.canBeDisplay()) {
                Coordinate brickCoordinate = brick.getCoordinate();
                String value = isColored ? brick.toString() : String.valueOf(brick.getSymbol());
                board.get(brickCoordinate.getY()).set(brickCoordinate.getX(), value);
            }
        }

        return board;
    }

    public ArrayList<ArrayList<String>> addBallToBoard(ArrayList<ArrayList<String>> board, boolean isColored) {
        if(this.getBall().canBeDisplay()) {
            Coordinate ballCoordinate = this.getBall().getCoordinate();
            String value = isColored ? this.getBall().toString() : String.valueOf(this.getBall().getSymbol());
            board.get(ballCoordinate.getY()).set(ballCoordinate.getX(), value);
        }
        return board;
    }

    public ArrayList<ArrayList<String>> addRacket(ArrayList<ArrayList<String>> board, boolean isColored) {
        Racket racket = this.getPlayer().getRacket();
        String value = isColored ? racket.toString() : String.valueOf(racket.getSymbol());

        for(Coordinate racketCoordinate : racket.getAllCoordinate()) {
            board.get(racketCoordinate.getY()).set(racketCoordinate.getX(), value);
        }

        return board;
    }

    public ArrayList<ArrayList<String>> getEmptyBoard() {
        ArrayList<ArrayList<String>> emptyBoard = new ArrayList<ArrayList<String>>();

        for(int y = 0; y < this.height; y++) {
            ArrayList<String> row = new ArrayList<String>();
            for(int x = 0; x < this.width; x++) {
                row.add(" ");
            }
            emptyBoard.add(row);
        }

        return emptyBoard;
    }

    public void displayBoard() {
        ArrayList<ArrayList<String>> emptyBoard = this.getEmptyBoard();

        emptyBoard = addBrickToBoard(emptyBoard, this.isColoredBricks);
        emptyBoard = addBallToBoard(emptyBoard, this.isColoredBricks);
        emptyBoard = addRacket(emptyBoard, this.isColoredBricks);

        for(ArrayList<String> row : emptyBoard) {
            for(String element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
        this.setColoredBricks(false);
    }

    public void displayCurrentStats() {
        System.out.println(String.format("lifePoint: %d", this.player.getLifePoint()));
        System.out.println(String.format("current level: %d    current score : %d", this.level, this.player.getScore()));
        System.out.println(String.format("state of the grid : %.2f %s", this.getDestructPercentage(), "% destruct"));
    }

    public void update() {
        for(Brick b: this.bricks) {
            if(!b.isDestroy() && this.getBall().getNextPosition().equals(b.getCoordinate())) {
                this.getBall().collide(b);
                b.collide(this.getBall());
            }
        }

        for(Coordinate coordinate : this.getPlayer().getRacket().getAllCoordinate()) {
            if(coordinate.equals(this.getBall().getNextPosition())) {
                this.getBall().collide(this.getPlayer().getRacket());
                this.getPlayer().getRacket().collide(this.getBall());
            }
        }

        ball.move();
        if(this.getDestructPercentage() >= 80) {
            this.createBricks();
            this.addLevel(1);
            this.getBall().setCoordinate(new Coordinate(0, this.height + 1));
        }

        for(int i = this.nbAugmentedBricks; i < Math.floor(this.getDestructPercentage() / 10); i++) {
            Collections.shuffle(this.bricks);
            for(Brick b : this.bricks) {
                if(!b.isAugmented() && !b.isDestroy()) {
                    b.merge(new BrickFactory(this).getRandomBrick(new Coordinate(0, 0)));
                    this.nbAugmentedBricks++;
                    break;
                }
            };
        }
    }

    public void run() {
        this.selectDifficulty();
        this.createBricks();
        this.getPlayer().initGenerationDeck();
        this.getPlayer().generateDeck();
        while(!this.getPlayer().isAlive()) {
            this.displayCurrentStats();
            this.displayBoard();
            this.getPlayer().play();
            this.update();
        }
    }

    public void setGenerationBrick(GenerationBrick newGenerationBrick) {
        this.generationBrick = newGenerationBrick;
    }

    public void setDifficulty(Difficulty newDifficulty) {
        switch (newDifficulty) {
            case EASY:
                this.setGenerationBrick(new EasyBrickGeneration());
                break;
            case MEDIUM:
                this.setGenerationBrick(new MediumBrickGeneration());
                break;
            default:
                this.setGenerationBrick(new HardBrickGeneration());
        }
        this.difficulty = newDifficulty;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Double getDestructPercentage() {
        int count = 0;

        for(Brick brick : this.bricks) {
            if(!brick.isDestroy()) {
                count++;
            }
        }
        return 100 - (count + 0.) / ((this.height - 3) * this.width) * 100;
    }

    public void setLevel(Integer newLevel) {
        this.level = newLevel;
    }

    public void setColoredBricks(boolean isColored) {
        this.isColoredBricks = isColored;
    }

    public void addLevel(Integer levelToAdd) {
        this.setLevel(this.level + levelToAdd);
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
}
